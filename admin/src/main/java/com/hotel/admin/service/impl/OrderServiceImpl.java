package com.hotel.admin.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hotel.admin.dao.RoomOrderDao;
import com.hotel.admin.dto.RoomOrderDto;
import com.hotel.admin.exception.CustomException;
import com.hotel.admin.query.*;
import com.hotel.admin.service.OrderService;
import com.hotel.common.PageModel;
import com.hotel.common.order.OrderStatus;
import com.hotel.common.service.SmsService;
import com.hotel.common.utils.BeanCopierUtil;
import com.hotel.common.utils.GsonUtil;
import com.hotel.model.*;
import com.hotel.model.Package;
import com.hotel.model.mapper.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RoomMapper roomMapper;

	@Autowired
	private RoomOrderMapper roomOrderMapper;

	@Autowired
	private PayOrderMapper payOrderMapper;

	@Autowired
	private PayReturnOrderMapper payReturnOrderMapper;

	@Autowired
	WxPayService wxPayService;

	@Autowired
	private RoomOrderDao roomOrderDao;

	@Autowired
	private PackageOrderMapper packageOrderMapper;

	@Autowired
	private PackageMapper packageMapper;

	@Autowired
	private SmsService smsService;

	@Value("${wx.pay.appId}")
	private String appId;

	@Value("${wx.pay.refundUrl}")
	private String refundUrl;

	@Value("${wx.pay.mchId}")
	private String mchId;

	private final String TEMPLATE_ID = "500470";

	@Override
	public PageModel<RoomOrderDto> getOrderListByPage(OrderQuery orderQuery) {

		List<Integer> roomIds = Collections.EMPTY_LIST;
		PageModel<RoomOrderDto> orders = new PageModel<>();
		RoomOrderExample roomOrderExample = new RoomOrderExample();
		RoomOrderExample.Criteria criteria = roomOrderExample.createCriteria();

		if (orderQuery.getApartmentId() >= 0) {
			Integer apartmentId = orderQuery.getApartmentId();
			RoomExample roomExample = new RoomExample();
			RoomExample.Criteria roomExampleCriteria = roomExample.createCriteria();
			roomExampleCriteria.andApartmentIdEqualTo(apartmentId);
			List<Room> rooms = roomMapper.selectByExample(roomExample);
			if (rooms.size() > 0) {
				roomIds = rooms.stream().map(Room::getId).collect(Collectors.toList());
			}

			if (roomIds.size() > 0) {
				criteria.andRoomIdIn(roomIds);
			} else {
				criteria.andRoomIdEqualTo(-1);
			}
		}

		// 排序
		/**
		 * 0 退房时间由就到新
		 * 1 入住时间由旧到新
		 * 2 退房时间由新到旧
		 * 3 入住时间由新到旧
		 */
		if (orderQuery.getSort() != null) {
			switch (orderQuery.getSort()) {
				case 0:
					roomOrderExample.setOrderByClause("check_out_time asc");
					break;
				case 1:
					roomOrderExample.setOrderByClause("check_in_time asc");
					break;
				case 2:
					roomOrderExample.setOrderByClause("check_out_time desc");
					break;
				case 3:
					roomOrderExample.setOrderByClause("check_in_time desc");
					break;
			}
		}

		if (orderQuery.getPayStatus() >= 0) {
			criteria.andPayStatusEqualTo(orderQuery.getPayStatus());
		}

		if (orderQuery.getOrderStatus() >= 0) {
			criteria.andStatusEqualTo(orderQuery.getOrderStatus());
		}

		int totalCount = roomOrderMapper.countByExample(roomOrderExample);

		roomOrderExample.setLimit(orderQuery.getLimit());
		roomOrderExample.setOffset((orderQuery.getPage() - 1) * orderQuery.getLimit());

		List<RoomOrder> roomOrders = roomOrderMapper.selectByExample(roomOrderExample);
		List<RoomOrderDto> data = new ArrayList<>();

		if (totalCount > 0) {
			data = roomOrders.stream().map(item -> {
				RoomOrderDto roomOrderDto = new RoomOrderDto();
				BeanCopierUtil.copy(item, roomOrderDto);
				roomOrderDto.setTotalPayPrice(item.getRealPayDepositPrice().add(item.getRealPayRentPrice()));
				return roomOrderDto;
			}).collect(Collectors.toList());
		}

		orders.setData(data);
		orders.setTotalCount(totalCount);
		orders.setCurrentPage(orderQuery.getPage());
		orders.setLimit(orderQuery.getLimit());
		return orders;
	}

	@Override
	public void checkout(CheckOutOrCancelQuery checkOutOrCancelQuery) throws WxPayException {
		Integer roomOrderId = checkOutOrCancelQuery.getRoomOrderId();
		RoomOrder roomOrder = roomOrderMapper.selectByPrimaryKey(roomOrderId);
		// 更改订单状态
		roomOrder.setStatus(3);
		roomOrderMapper.updateByPrimaryKey(roomOrder);

		// 更新密码
		Room room = roomMapper.selectByPrimaryKey(roomOrder.getRoomId());
		room.setRoomPassword(checkOutOrCancelQuery.getPassword());
		roomMapper.updateByPrimaryKey(room);
		if (checkOutOrCancelQuery.getReturnPrice().compareTo(BigDecimal.ZERO) > 0) {
			// 退押金
			wxReFund(roomOrder, checkOutOrCancelQuery.getReturnPrice(), checkOutOrCancelQuery);
		}

	}

	@Override
	public void cancel(CheckOutOrCancelQuery cancelQuery) throws Exception {
		RoomOrder roomOrder = roomOrderMapper.selectByPrimaryKey(cancelQuery.getRoomOrderId());
		roomOrder.setStatus(4);
		roomOrder.setPayStatus(2);
		roomOrderMapper.updateByPrimaryKey(roomOrder);
		Room room = roomMapper.selectByPrimaryKey(roomOrder.getRoomId());
		if (room == null) {
			throw new CustomException(500, "房间不存在");
		}
		room.setRoomPassword(cancelQuery.getPassword());
		roomMapper.updateByPrimaryKey(room);

		if (cancelQuery.getReturnPrice().compareTo(BigDecimal.ZERO) > 0) {
			// 退押金
			wxReFund(roomOrder, cancelQuery.getReturnPrice(), cancelQuery);
		}
	}

	private void wxReFund(RoomOrder roomOrder, BigDecimal returnPrice, CheckOutOrCancelQuery checkOutOrCancelQuery) throws WxPayException {
		PayReturnOrder payReturnOrder = new PayReturnOrder();
		payReturnOrder.setPrice(returnPrice.multiply(BigDecimal.valueOf(100)).intValue());
		payReturnOrder.setOpenId(roomOrder.getOpenId());
		payReturnOrder.setOrderSn(roomOrder.getOrderSn());

		if (checkOutOrCancelQuery.getType().equals(1)) {
			payReturnOrder.setMemo("押金退款");
		}

		if (checkOutOrCancelQuery.getType().equals(2)) {
			payReturnOrder.setMemo("取消订单退款");
		}

		PayOrderExample payOrderExample = new PayOrderExample();
		PayOrderExample.Criteria criteria = payOrderExample.createCriteria();
		criteria.andOrderSnEqualTo(roomOrder.getOrderSn());
		criteria.andPayStatusEqualTo((byte) 1);
		List<PayOrder> payOrders = payOrderMapper.selectByExample(payOrderExample);
		PayOrder payOrder = payOrders.get(0);
		payReturnOrder.setPayId(payOrder.getPayId());
		payReturnOrder.setPayNo(payOrder.getWxOrderSn());
		payReturnOrder.setPayName(payOrder.getPayName());
		String returnNo = "D_" + System.currentTimeMillis();
		payReturnOrder.setReturnNo(returnNo);
		payReturnOrder.setCreatedAt(new Date());
		payReturnOrder.setUpdatedAt(new Date());

		payReturnOrderMapper.insertSelective(payReturnOrder);

		WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
		wxPayRefundRequest.setNotifyUrl(refundUrl);
		wxPayRefundRequest.setOutTradeNo(payReturnOrder.getOrderSn());
		wxPayRefundRequest.setTransactionId(payOrder.getWxOrderSn());
		wxPayRefundRequest.setTotalFee(payReturnOrder.getPrice());
		wxPayRefundRequest.setRefundFee(payReturnOrder.getPrice());
		wxPayRefundRequest.setAppid(appId);
		wxPayRefundRequest.setMchId(mchId);
		wxPayRefundRequest.setOutRefundNo(payReturnOrder.getReturnNo());

		WxPayRefundResult refund = wxPayService.refund(wxPayRefundRequest);
		if (refund.getReturnCode().equals("SUCCESS")) {

		} else {
			throw new WxPayException("退款失败");
		}
	}

	@Override
	public List<RoomOrder> getRemindList() {
		List<RoomOrder> remindList = roomOrderDao.getRemindList();
		return remindList;
	}

	@Override
	public PageModel<PackageOrder> getPackageOrderList(PackageOrderSelectQuery selectQuery) {
		PageModel<PackageOrder> packageOrderPageModel = new PageModel<>();
		packageOrderPageModel.setCurrentPage(selectQuery.getPage());
		packageOrderPageModel.setLimit(selectQuery.getLimit());

		PackageOrderExample packageOrderExample = new PackageOrderExample();
		PackageOrderExample.Criteria criteria = packageOrderExample.createCriteria();
		// 设置套餐Id -1 全部
		if (selectQuery.getPackageId().length > 0) {
			List<Integer> ids = Arrays.stream(selectQuery.getPackageId()).collect(Collectors.toList());
			criteria.andPackageIdIn(ids);
		}

		// 设置 订单状态 -1 全部
		if (selectQuery.getOrderStatus() > 0) {
			criteria.andStatusEqualTo(selectQuery.getOrderStatus());
		}

		// 设置排序
		if (selectQuery.getSort() == 1) {
			packageOrderExample.setOrderByClause("start_time asc");
		} else {
			packageOrderExample.setOrderByClause("start_time desc");
		}

		int count = packageOrderMapper.countByExample(packageOrderExample);
		packageOrderPageModel.setTotalCount(count);

		packageOrderExample.setLimit(selectQuery.getLimit());
		packageOrderExample.setOffset((selectQuery.getPage() - 1) * selectQuery.getLimit());

		List<PackageOrder> packageOrders = packageOrderMapper.selectByExample(packageOrderExample);
		packageOrderPageModel.setData(packageOrders);
		return packageOrderPageModel;
	}

	@Override
	public void cancelPackageId(CancelOrderQuery cancelOrderQuery) {
		Integer orderId = cancelOrderQuery.getId();

		PackageOrder packageOrder = packageOrderMapper.selectByPrimaryKey(orderId);
		packageOrder.setStatus(OrderStatus.HAS_CANCELED.getCode());
		packageOrderMapper.updateByPrimaryKeySelective(packageOrder);

		Package aPackage = packageMapper.selectByPrimaryKey(packageOrder.getPackageId());
		ConfigVo configVo = GsonUtil.GsonToBean(aPackage.getConfig(), ConfigVo.class);
		Boolean needPay = configVo.getNeedPay();
		// 默认退全款
		if (needPay && packageOrder.getStatus().equals(OrderStatus.UNDONE.getCode())) {
			// 退款逻辑
		}

		// 指定退款金额 部分退款
		if (cancelOrderQuery.getNeedRefund()) {
			// 退款逻辑
		}
	}

	@Override
	public void confirmOrder(Integer id) {
		PackageOrder packageOrder = packageOrderMapper.selectByPrimaryKey(id);
		packageOrder.setStatus(OrderStatus.HAS_DONE.getCode());
		packageOrderMapper.updateByPrimaryKeySelective(packageOrder);
	}

	@Override
	public void sendPasswordMsg(Integer id, String appid, String appkey) {
		RoomOrder roomOrder = roomOrderMapper.selectByPrimaryKey(id);
		Integer roomId = roomOrder.getRoomId();
		Room room = roomMapper.selectByPrimaryKey(roomId);

		SmsCode sms = new SmsCode();
		// 需要发送短信的手机号码
		String[] phoneNumbers = {roomOrder.getMobile()};

		sms.setMobile(roomOrder.getMobile());

		// 短信模板 ID，需要在短信应用中申请
		sms.setTemplateId(TEMPLATE_ID);
		// templateId7839对应的内容是"您的验证码是: {1}"
		// 签名
		// NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请。
		String smsSign = "雾一数据";
		try {
			sms.setCode(room.getRoomPassword());
			sms.setCreatedAt(new Date());
			sms.setUpdatedAt(new Date());
			Date experiedAt = new Date();
			experiedAt.setTime(experiedAt.getTime() + 5 * 60 * 1000);
			sms.setExpirationTime(experiedAt);

			smsService.insertRecord(sms);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 数组具体的元素个数和模板中变量个数必须一致，例如示例中 templateId:5678 对应一个变量，参数数组中元素个数也必须是一个
			String[] params = {
				sdf.format(roomOrder.getCheckInTime()),
				room.getRoomPassword()
			};
			SmsSingleSender ssender = new SmsSingleSender(Integer.parseInt(appid), appkey);
			SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
				Integer.parseInt(TEMPLATE_ID), params, smsSign, "", "");

			System.out.println(result);

			JSONObject jsonObject = new JSONObject(result.toString());
			int recode = jsonObject.getInt("result");
			switch (recode) {
				case 0:
					sms.setStatus(1);
					sms.setContent("短信发送成功");
					break;
				case 1016:
					sms.setStatus(2);
					sms.setContent("手机号格式错误");
					break;
				case 1023:
					sms.setStatus(2);
					sms.setContent("请在60s后重试");
					break;
				case 1024:
					sms.setStatus(2);
					sms.setContent("请在1小时后重试");
					break;
				default:
					sms.setStatus(2);
					sms.setContent("错误码" + recode + ",请带上您的错误码反馈给客服");
			}
			smsService.update(sms);
		} catch (JSONException | IOException | HTTPException e) {
			// JSON 解析错误
			e.printStackTrace();
		}
	}
}
