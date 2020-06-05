package com.hotel.admin.job;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hotel.admin.controller.SmsController;
import com.hotel.admin.dto.RoomDto;
import com.hotel.admin.service.OrderService;
import com.hotel.admin.service.RoomService;
import com.hotel.common.ApiResponse;
import com.hotel.common.service.SmsService;
import com.hotel.common.utils.DateUtil;
import com.hotel.model.*;
import com.hotel.model.mapper.RoomOrderMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xu
 */
@Component
public class RemindTask {

	@Autowired
	private OrderService orderService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private RoomOrderMapper roomOrderMapper;

	private final String TEMPLATE_ID = "500467";

	@Value("${sms.appid}")
	private String appid;

	@Value("${sms.appkey}")
	private String appkey;

	/**
	 * 每天 10点 更新
	 */
	@Scheduled(cron = "0 00 10 * * ?")
	private void sendRemindSms() {
		// 遍历订单 发送信息
		getOrderLis();
	}

	/**
	 * 每天 12点 更新已入住订单为 待退房
	 */
	@Scheduled(cron = "0 32 17 * * ?")
	private void changeRoomOrderStatus() {
		updateCheckoutStatus();
	}

	/**
	 * 每天 12点 更新待入住订单为 已入住
	 */
	@Scheduled(cron = "0 00 15 * * ?")
	private void changeCheckInOrder() {
		updateCheckInStatus();
	}

	private void updateCheckInStatus() {
		RoomOrderExample roomOrderExample = new RoomOrderExample();
		RoomOrderExample.Criteria criteria = roomOrderExample.createCriteria();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date dayStart = calendar.getTime();

		criteria.andCheckInTimeGreaterThan(dayStart);
		criteria.andCheckInTimeLessThan(DateUtil.addDays(dayStart, 1));
		criteria.andPayStatusEqualTo(1);
		criteria.andStatusEqualTo(0);
		List<RoomOrder> roomOrders = roomOrderMapper.selectByExample(roomOrderExample);
		for (RoomOrder roomOrder : roomOrders) {
			roomOrder.setStatus(1);
			roomOrderMapper.updateByPrimaryKeySelective(roomOrder);
			System.out.println(roomOrder.toString());
		}
	}

	private void updateCheckoutStatus() {
		RoomOrderExample roomOrderExample = new RoomOrderExample();
		RoomOrderExample.Criteria criteria = roomOrderExample.createCriteria();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date dayStart = calendar.getTime();

		criteria.andCheckOutTimeGreaterThanOrEqualTo(dayStart);
		criteria.andCheckOutTimeLessThan(DateUtil.addDays(dayStart, 1));
		criteria.andPayStatusEqualTo(1);
		criteria.andStatusGreaterThan(0);
		criteria.andStatusLessThanOrEqualTo(2);
		List<RoomOrder> roomOrders = roomOrderMapper.selectByExample(roomOrderExample);
		roomOrders.forEach(item -> {
			item.setStatus(2);
			roomOrderMapper.updateByPrimaryKeySelective(item);
		});

	}

	/**
	 * 图书亮度，低于0不过期
	 */
	private void getOrderLis() {
		List<RoomOrder> remindList = orderService.getRemindList();
		remindList.forEach(this::sendSms);
	}

	private void sendSms(RoomOrder roomOrder) {
		Room roomDto = roomService.getById(roomOrder.getRoomId());

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
			sms.setCode(roomDto.getRoomPassword());
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
				roomDto.getAddress()
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
					sms.setContent("短信验证码发送成功");
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
