package com.hotel.minapp.service;

import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信末班消息服务
 * @author xu
 */
public interface WxTempMsgService {
  /**
   * @param orderId
   * @throws WxErrorException
   */
  void sendOrderSuccessMessage(Integer orderId) throws WxErrorException;
}
