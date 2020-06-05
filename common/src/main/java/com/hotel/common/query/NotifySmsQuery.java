package com.hotel.common.query;

import lombok.Data;

/**
 * 发送预订成功短信通知
 *
 * @author xu
 */
@Data
public class NotifySmsQuery {
  /**
   * 短信appid
   */
  private Integer appid;
  /**
   * 短息appkey
   */
  private String appkey;
  /**
   * 模板ID
   */
  private Integer templateId;
  /**
   * 发送手机
   */
  private String[] mobile;
  /**
   * 订单ID
   */
  private Integer orderId;
}
