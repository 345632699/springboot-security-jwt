package com.hotel.admin.api.v2.controller;

import com.hotel.admin.query.CancelOrderQuery;
import com.hotel.admin.query.PackageOrderSelectQuery;
import com.hotel.admin.query.PublishQuery;
import com.hotel.admin.service.OrderService;
import com.hotel.common.ApiResponse;
import com.hotel.common.PageModel;
import com.hotel.model.PackageOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * v2版 订单接口
 *
 * @author xu
 */
@RestController
@RequestMapping(value = "/api/v2/order")
public class OrderV2Controller {

  @Autowired
  private OrderService orderService;

  /**
   * 获取订单列表
   * @param selectQuery
   * @return
   */
  @PostMapping(value = "/list")
  public ApiResponse getPackageOrderList(@RequestBody PackageOrderSelectQuery selectQuery) {
    PageModel<PackageOrder> packageOrderList = orderService.getPackageOrderList(selectQuery);
    return ApiResponse.ofSuccess(packageOrderList);
  }

  /**
   * 取消订单
   * @param cancelOrderQuery
   * @return
   */
  @PostMapping(value = "/cancel")
  public ApiResponse cancel(@RequestBody CancelOrderQuery cancelOrderQuery) {
    orderService.cancelPackageId(cancelOrderQuery);
    return ApiResponse.ofSuccess();
  }

  /**
   * 核销完成订单
   * @param packageOrder
   * @return
   */
  @PostMapping(value = "confirmOrder")
  public ApiResponse confirmOrder(@RequestBody PackageOrder packageOrder) {
    orderService.confirmOrder(packageOrder.getId());
    return ApiResponse.ofSuccess();
  }

}
