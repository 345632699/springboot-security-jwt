package com.hotel.admin.query;

import lombok.Data;

/**
 * @author xu
 */
@Data
public class PackageOrderSelectQuery {
  Integer page;
  Integer limit;
  /**
   * 订单状态
   * 0 待支付
   * 1 未完成
   * 2 已完成
   * 3 已取消
   * 4 已退款
   * -1 全部
   */
  Integer orderStatus;

  /**
   * 0 由新到旧
   * 1 由旧到新
   */
  Integer sort;

  /**
   * 套餐Id
   */
  Integer[] packageId;
}
