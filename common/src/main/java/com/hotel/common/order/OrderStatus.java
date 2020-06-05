package com.hotel.common.order;

import lombok.Getter;

/**
 * 订单状态
 * @author xu
 */
@Getter
public enum OrderStatus {

  /**
   * 待支付
   */
  WAIT_TO_PAY(0, "待支付"),
  /**
   * 未完成
   */
  UNDONE(1, "未完成"),
  /**
   * 已完成
   */
  HAS_DONE(2, "已完成"),
  /**
   * 已取消
   */
  HAS_CANCELED(3, "已取消"),
  /**
   * 已退款
   */
  HAS_REFUND(4, "已退款");
  /**
   * 状态码
   */
  private Integer code;

  /**
   * 返回信息
   */
  private String message;

  OrderStatus(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public static OrderStatus fromCode(Integer code) {
    OrderStatus[] statuses = OrderStatus.values();
    for (OrderStatus status : statuses) {
      if (status.getCode()
          .equals(code)) {
        return status;
      }
    }
    return WAIT_TO_PAY;
  }
}