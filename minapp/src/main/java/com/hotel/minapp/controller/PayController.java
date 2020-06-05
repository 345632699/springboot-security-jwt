package com.hotel.minapp.controller;


import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.hotel.common.ApiResponse;
import com.hotel.common.order.OrderStatus;
import com.hotel.common.order.RoomOrderStatus;
import com.hotel.minapp.interceptor.PassToken;
import com.hotel.minapp.service.PayService;
import com.hotel.model.PayReturnOrder;
import com.hotel.model.PayReturnOrderExample;
import com.hotel.model.RoomOrder;
import com.hotel.model.RoomOrderExample;
import com.hotel.model.mapper.PayReturnOrderMapper;
import com.hotel.model.mapper.RoomOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/pay")
@Slf4j
public class PayController {
  @Autowired
  private PayService payService;

  @Autowired
  private WxPayService wxService;

  @Autowired
  private RoomOrderMapper roomOrderMapper;

  @Autowired
  private PayReturnOrderMapper payReturnOrderMapper;

  @PostMapping(value = "getPayConfig")
  public ApiResponse getPayConfig(@RequestBody RoomOrder roomOrder, HttpServletRequest request) throws WxPayException {
    Object payConfig = payService.getPayConfig(roomOrder.getId(), request);
    return ApiResponse.ofSuccess(payConfig);
  }

  @PassToken
  @PostMapping("/notify/order")
  public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
    log.info("xmlData    : {}", xmlData);

    final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
    log.info("notifyResult    : {}", notifyResult);

    // TODO 根据自己业务场景需要构造返回对象
    try {
      if (notifyResult.getReturnCode().equals("SUCCESS")) {
        String outTradeNo = notifyResult.getOutTradeNo();
        String transactionId = notifyResult.getTransactionId();
        if (notifyResult.getResultCode().equals("SUCCESS")) {
          boolean res = payService.changOrderStatus(outTradeNo, transactionId);
          if (res) {
            return WxPayNotifyResponse.success("成功");
          } else {
            return WxPayNotifyResponse.fail("error");
          }
        }
      }
    } catch (Exception e) {
      log.error("error    : {}", e);
      return WxPayNotifyResponse.fail("error");
    }
    return WxPayNotifyResponse.fail("error");
  }
  @PostMapping("/notify/refund")
  public String parseRefundNotifyResult(@RequestBody String xmlData) throws WxPayException {
    final WxPayRefundNotifyResult result = this.wxService.parseRefundNotifyResult(xmlData);
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 打印请求相关参数
    log.info("========================================== Start ==========================================");
    // 打印请求 url
    log.info("URL            : {}", request.getRequestURL().toString());
    // 打印 Http method
    log.info("HTTP Method    : {}", request.getMethod());
    log.info("HTTP Method    : {}", request.getMethod());
    log.info("xmlData    : {}", xmlData);
    // TODO 根据自己业务场景需要构造返回对象

    WxPayRefundNotifyResult.ReqInfo reqInfo = result.getReqInfo();
    String outTradeNo = reqInfo.getOutTradeNo();
    String outRefundNo = reqInfo.getOutRefundNo();

    RoomOrderExample example = new RoomOrderExample();
    RoomOrderExample.Criteria criteria = example.createCriteria();
    criteria.andOrderSnEqualTo(outTradeNo);
    List<RoomOrder> orders = roomOrderMapper.selectByExample(example);
    RoomOrder order = orders.get(0);
    if (reqInfo.getRefundStatus().equals("SUCCESS")) {
      // 0 未入住 1 已入住 2 待退房 3 已完成 4 已取消
      if (order.getStatus().equals(RoomOrderStatus.HAS_CANCELED.getCode())) {
        // 0 未支付 1 已支付 2 退款中 3 已退款
        order.setPayStatus(3);
      } else if (order.getStatus() > 0){
        order.setStatus(3);
      }
    } else if (reqInfo.getRefundStatus().equals("CHANGE")) {
      order.setStatus(-1);
    } else {
      order.setStatus(-1);
    }
    roomOrderMapper.updateByPrimaryKeySelective(order);

    PayReturnOrderExample repayOrderExample = new PayReturnOrderExample();
    PayReturnOrderExample.Criteria repayOrderExampleCriteria = repayOrderExample.createCriteria();
    repayOrderExampleCriteria.andReturnNoEqualTo(outRefundNo);
    List<PayReturnOrder> repayOrders = payReturnOrderMapper.selectByExample(repayOrderExample);
    PayReturnOrder repayOrder = repayOrders.get(0);
    if (reqInfo.getRefundStatus().equals("SUCCESS")) {
      repayOrder.setStatus((byte) 1);
    } else if (reqInfo.getRefundStatus().equals("CHANGE")) {
      repayOrder.setStatus((byte) 2);
    } else {
      repayOrder.setStatus((byte) 3);
    }
    repayOrder.setStatus((byte) 1);
    payReturnOrderMapper.updateByPrimaryKeySelective(repayOrder);

    return WxPayNotifyResponse.success("成功");
  }
}
