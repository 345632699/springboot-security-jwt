package com.hotel.minapp.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.hotel.minapp.query.ConsumerQuery;
import com.hotel.minapp.query.LoginQuery;
import com.hotel.minapp.vo.ConsumerVo;
import com.hotel.model.WxConsumer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ConsumerService {
    /**
     * 根据openId获取用户详情
     * @param openId
     * @return
     */
    List<WxConsumer> getConsumerByOpenId(String openId);

    /**
     * 微信用户信息创建
     * @param wxMaUserInfo
     */
    void createConsumer(WxMaUserInfo wxMaUserInfo);

    /**
     * 绑定手机号
     * @param consumerQuery
     * @param request
     */
    void bindPhone(ConsumerQuery consumerQuery, HttpServletRequest request);

    /**
     * 获取用户详情
     * @param openIdOrUnionId
     * @return
     */
    ConsumerVo userInfoByOpenIdOrUnionId(String openIdOrUnionId);
}
