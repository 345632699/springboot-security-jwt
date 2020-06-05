package com.hotel.minapp.controller;

import com.hotel.common.ApiResponse;
import com.hotel.minapp.interceptor.UserLoginToken;
import com.hotel.minapp.query.ConsumerQuery;
import com.hotel.minapp.service.ConsumerService;
import com.hotel.minapp.utils.JWTUtil;
import com.hotel.minapp.vo.ConsumerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private SmsController smsController;

    @PostMapping(value = "bindMobile")
    @UserLoginToken
    public ApiResponse bindPhone(@RequestBody ConsumerQuery consumerQuery, HttpServletRequest request) {
        ApiResponse response = smsController.verify(consumerQuery.getCode(), consumerQuery.getMobile());
        if (response.getCode().equals(200)) {
            // 更新用户基本信息
            consumerService.bindPhone(consumerQuery, request);
            return ApiResponse.ofSuccess("修改成功");
        } else {
            return response;
        }
    }

    @PostMapping(value = "info")
    @UserLoginToken
    public ApiResponse userInfo(HttpServletRequest request) {
        String openId = JWTUtil.getOpenId(request);
        ConsumerVo consumerVo = consumerService.userInfoByOpenIdOrUnionId(openId);

        return ApiResponse.ofSuccess(consumerVo);
    }

}
