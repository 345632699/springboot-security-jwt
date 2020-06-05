package com.hotel.minapp.api.v2.controller;

import com.hotel.common.ApiResponse;
import com.hotel.minapp.interceptor.UserLoginToken;
import com.hotel.minapp.query.FormQuery;
import com.hotel.minapp.service.WxFormService;
import com.hotel.minapp.utils.JWTUtil;
import com.hotel.model.WxFormId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 微信小程序 魔板消息 formID 收集
 * @author xu
 */
@RestController
@RequestMapping(value = "/v2/wxFrom")
public class WxFormController {

  @Autowired
  private WxFormService wxFormService;

  @PostMapping(value = "create")
  @UserLoginToken
  public ApiResponse create(@RequestBody FormQuery formQuery, HttpServletRequest request) {
    String openId = JWTUtil.getOpenId(request);
    wxFormService.create(formQuery, openId);
    return ApiResponse.ofSuccess();
  }
}
