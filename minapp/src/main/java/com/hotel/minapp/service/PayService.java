package com.hotel.minapp.service;

import com.github.binarywang.wxpay.exception.WxPayException;

import javax.servlet.http.HttpServletRequest;

public interface PayService {
  /**
   * 根绝ID获取微信支付配置
   * @param orderId 订单ID
   * @param request
   * @return
   * @throws WxPayException
   */
  Object getPayConfig(Integer orderId, HttpServletRequest request) throws WxPayException;

  /**
   * 支付回调
   * 更改订单状态
   * @param outTradeNo 支付订单号
   * @param transactionId
   * @return
   */
  boolean changOrderStatus(String outTradeNo, String transactionId);
}
