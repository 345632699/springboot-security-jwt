package com.hotel.minapp.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.hotel.common.Status;
import com.hotel.common.order.RoomOrderStatus;
import com.hotel.minapp.exception.CustomException;
import com.hotel.minapp.service.PayService;
import com.hotel.model.PayOrder;
import com.hotel.model.PayOrderExample;
import com.hotel.model.RoomOrder;
import com.hotel.model.RoomOrderExample;
import com.hotel.model.mapper.PayOrderMapper;
import com.hotel.model.mapper.RoomOrderMapper;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private RoomOrderMapper roomOrderMapper;

    @Autowired
    private WxPayService wxService;

    @Autowired
    private PayOrderMapper payOrderMapper;



    @Override
    public Object getPayConfig(Integer orderId, HttpServletRequest request) throws WxPayException {
        String userIpAddr = request.getRemoteAddr();
        RoomOrder order = roomOrderMapper.selectByPrimaryKey(orderId);

        if (order.getStatus().equals(RoomOrderStatus.HAS_CANCELED.getCode())) {
            throw new CustomException(Status.ORDER_EXPIRED);
        }

        RoomOrderExample roomOrderExample = new RoomOrderExample();
        RoomOrderExample.Criteria criteria = roomOrderExample.createCriteria();
        criteria.andCheckInTimeGreaterThanOrEqualTo(order.getCheckInTime());
        criteria.andCheckInTimeLessThan(order.getCheckOutTime());
        criteria.andStatusLessThan(3);
        criteria.andPayStatusEqualTo(1);
        criteria.andRoomIdEqualTo(order.getRoomId());
        int count = roomOrderMapper.countByExample(roomOrderExample);
        if (count > 0) {
            throw new CustomException(Status.CAN_NOT_ORDER_REPEAT);
        }

        BigDecimal price = order.getRealPayDepositPrice().add(order.getRealPayRentPrice()).multiply(BigDecimal.valueOf(100));

        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setSpbillCreateIp(userIpAddr);
        wxPayUnifiedOrderRequest.setBody("订单支付");
        wxPayUnifiedOrderRequest.setSignType("MD5");
        wxPayUnifiedOrderRequest.setOutTradeNo(order.getOrderSn());
        wxPayUnifiedOrderRequest.setOpenid(order.getOpenId());
        wxPayUnifiedOrderRequest.setTotalFee(price.intValue());

        Object prepayOrder = this.wxService.createOrder(wxPayUnifiedOrderRequest);

        System.out.println("===========微信支付配置生成============");
        System.out.println(prepayOrder.toString());
        BeanMap beanMap = new BeanMap(prepayOrder);
        String prepayId = beanMap.get("packageValue").toString();

        System.out.println(prepayId);
        System.out.println("===========微信支付配置============");

        PayOrder payOrder = new PayOrder();
        payOrder.setOrderSn(order.getOrderSn());
        payOrder.setOpenId(order.getOpenId());
        payOrder.setCreatedAt(new Date());
        payOrder.setPayId((byte) 1);
        payOrder.setIp(userIpAddr);
        payOrder.setPrepayId(prepayId);
        payOrder.setPayName("微信支付");
        payOrder.setPrice(price.intValue());
        payOrder.setBody("订单支付");
        int i = payOrderMapper.insertSelective(payOrder);
        if (i > 0) {
            return prepayOrder;
        } else {
            return null;
        }
    }

    @Override
    public boolean changOrderStatus(String outTradeNo, String transactionId) {
        PayOrderExample payOrderExample = new PayOrderExample();
        PayOrderExample.Criteria payOrderExampleCriteria = payOrderExample.createCriteria();
        payOrderExampleCriteria.andOrderSnEqualTo(outTradeNo);
        List<PayOrder> payOrders = payOrderMapper.selectByExample(payOrderExample);
        if (payOrders.size() > 0) {
            PayOrder payOrder = payOrders.get(0);
            if (payOrder.getPayAt() != null && payOrder.getPayStatus().intValue() == 1) {
                return true;
            }
            payOrder.setPayStatus((byte) 1);
            payOrder.setPayAt(new Date());
            payOrder.setWxOrderSn(transactionId);
            payOrderMapper.updateByPrimaryKeySelective(payOrder);

            RoomOrderExample roomOrderExample = new RoomOrderExample();
            RoomOrderExample.Criteria criteria = roomOrderExample.createCriteria();
            criteria.andOrderSnEqualTo(outTradeNo);
            List<RoomOrder> roomOrders = roomOrderMapper.selectByExample(roomOrderExample);
            RoomOrder roomOrder = roomOrders.get(0);
            roomOrder.setPayStatus(1);
            roomOrderMapper.updateByPrimaryKey(roomOrder);
            return true;
        } else {
            return false;
        }
    }
}
