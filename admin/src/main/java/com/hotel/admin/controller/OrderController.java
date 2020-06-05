package com.hotel.admin.controller;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hotel.admin.dto.RoomOrderDto;
import com.hotel.admin.query.CheckOutOrCancelQuery;
import com.hotel.admin.query.OrderQuery;
import com.hotel.admin.service.OrderService;
import com.hotel.common.ApiResponse;
import com.hotel.common.PageModel;
import com.hotel.model.RoomOrder;
import com.hotel.model.SmsCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xu
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @Value("${sms.appid}")
  private String appid;

  @Value("${sms.appkey}")
  private String appkey;

  @PostMapping(value = "list")
  public ApiResponse orderList(@RequestBody OrderQuery orderQuery){
    PageModel<RoomOrderDto> orderListByPage = orderService.getOrderListByPage(orderQuery);
    return ApiResponse.ofSuccess(orderListByPage);
  }

  @PostMapping(value = "checkout")
  public ApiResponse checkoutRoom(@RequestBody CheckOutOrCancelQuery checkOutOrCancelQuery) throws WxPayException {
    // 退房退款
    checkOutOrCancelQuery.setType(1);
    orderService.checkout(checkOutOrCancelQuery);
    return ApiResponse.ofSuccess();
  }

  @PostMapping(value = "cancelOrder")
  public ApiResponse cancel(@RequestBody CheckOutOrCancelQuery cancelQuery) throws Exception {
    // 取消订单退款
    cancelQuery.setType(2);
    orderService.cancel(cancelQuery);
    return ApiResponse.ofSuccess();
  }

  @PostMapping(value = "sendPassword")
  public ApiResponse sendPassword(@RequestBody RoomOrder roomOrder) {
    orderService.sendPasswordMsg(roomOrder.getId(), appid, appkey);
    return ApiResponse.ofSuccess();
  }

}
