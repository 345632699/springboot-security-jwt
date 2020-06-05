package com.hotel.admin.query;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 取消订单传参
 * @author xu
 */
@Data
public class CancelOrderQuery {
  /**
   * 订单ID
   */
  Integer id;
  /**
   * 退款金额
   */
  BigDecimal refundPrice;

  /**
   * 是否需要退款
   */
  Boolean needRefund;
}
