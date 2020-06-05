package com.hotel.minapp.controller;

import com.hotel.common.ApiResponse;
import com.hotel.minapp.dto.RoomOrderDto;
import com.hotel.minapp.query.RoomOrderQuery;
import com.hotel.minapp.scenes.delayTask.CancelOrderSender;
import com.hotel.minapp.service.OrderService;
import com.hotel.minapp.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {

  @Autowired
  OrderService orderService;

  @Value("${cancelWaitTime}")
  private Integer cancelWaitTime;

  @PostMapping(value = "create")
  public ApiResponse createOrder(@Valid @RequestBody RoomOrderQuery roomOrderQuery, HttpServletRequest request) {
    String openId = JWTUtil.getOpenId(request);
    Integer orderId = orderService.createOrder(roomOrderQuery, openId);
    //1分钟消息被消费 //单位毫秒
    orderSender.sendMessage(orderId,cancelWaitTime * 60 * 1000);
    Map map = new HashMap();
    map.put("id", orderId);
    return ApiResponse.ofSuccess(map);
  }

  @GetMapping(value = "info")
  public ApiResponse info(@RequestParam Integer id) {
    RoomOrderDto roomOrderDto = orderService.getInfoById(id);
    return ApiResponse.ofSuccess(roomOrderDto);
  }

  @Autowired
  private CancelOrderSender orderSender;
  @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
  @ResponseBody
  public ApiResponse cancelOrder(){
    //1分钟消息被消费 //单位毫秒
    orderSender.sendMessage(1,1 * 60 * 1000);
    return ApiResponse.ofSuccess(null);
  }

}
