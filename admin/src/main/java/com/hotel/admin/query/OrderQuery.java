package com.hotel.admin.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderQuery {

  @NotNull(message = "页码不能为空")
  Integer page;
  @NotNull(message = "分页条数不能为空")
  Integer limit;
  /**
   * -1 全部
   * 0 未支付
   * 1 已支付
   * 2 退款中
   * 3 已退款
   */
  Integer payStatus;
  /**
   * -1 全部
   * 0 未入住
   * 1 已入住
   * 2 待退房
   * 3 已完成
   * 4 已取消
   */
  Integer orderStatus;
  /**
   * -1 全部
   */
  Integer apartmentId;
  /**
   * 0 退房时间由就到新
   * 1 入住时间由旧到新
   * 2 退房时间由新到旧
   * 3 入住时间由新到旧
   */
  Integer sort;

}
