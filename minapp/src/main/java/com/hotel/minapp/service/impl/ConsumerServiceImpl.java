package com.hotel.minapp.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.hotel.common.Status;
import com.hotel.common.utils.BeanCopierUtil;
import com.hotel.minapp.exception.CustomException;
import com.hotel.minapp.query.ConsumerQuery;
import com.hotel.minapp.query.LoginQuery;
import com.hotel.minapp.service.ConsumerService;
import com.hotel.minapp.utils.JWTUtil;
import com.hotel.minapp.vo.ConsumerVo;
import com.hotel.model.WxConsumer;
import com.hotel.model.WxConsumerExample;
import com.hotel.model.mapper.WxConsumerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private WxConsumerMapper consumerMapper;

    @Override
    public List<WxConsumer> getConsumerByOpenId(String openId) {
        WxConsumerExample wxConsumerExample = new WxConsumerExample();
        WxConsumerExample.Criteria criteria = wxConsumerExample.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<WxConsumer> wxUsers = consumerMapper.selectByExample(wxConsumerExample);
        return wxUsers;
    }

    @Override
    public void createConsumer(WxMaUserInfo wxMaUserInfo) {
        WxConsumer wxUser = new WxConsumer();
        wxUser.setAvatarUrl(wxMaUserInfo.getAvatarUrl());
        wxUser.setCity(wxMaUserInfo.getCity());
        wxUser.setGender(Integer.valueOf(wxMaUserInfo.getGender()));
        wxUser.setCountry(wxMaUserInfo.getCountry());
        wxUser.setNickName(wxMaUserInfo.getNickName());
        wxUser.setProvince(wxMaUserInfo.getProvince());
        wxUser.setUnionId(wxMaUserInfo.getUnionId());
        wxUser.setOpenId(wxMaUserInfo.getOpenId());
        wxUser.setCreatedAt(new Date());
        consumerMapper.insertSelective(wxUser);
    }

    @Override
    public void bindPhone(ConsumerQuery consumerQuery, HttpServletRequest request) {

        List<WxConsumer> consumers = getConsumerByOpenId(JWTUtil.getOpenId(request));
        if (consumers.size() > 0) {
            WxConsumer wxConsumer = consumers.get(0);
            wxConsumer.setName(consumerQuery.getName());
            wxConsumer.setMobile(consumerQuery.getMobile());
            consumerMapper.updateByPrimaryKey(wxConsumer);
        } else {
            throw new CustomException(Status.USER_NOT_EXIST);
        }
    }

    @Override
    public ConsumerVo userInfoByOpenIdOrUnionId(String openIdOrUnionId) {
        WxConsumerExample wxConsumerExample = new WxConsumerExample();
        WxConsumerExample.Criteria criteria = wxConsumerExample.createCriteria();
        criteria.andOpenIdEqualTo(openIdOrUnionId);

        WxConsumerExample.Criteria criteria1 = wxConsumerExample.createCriteria();
        criteria1.andUnionIdEqualTo(openIdOrUnionId);
        wxConsumerExample.or(criteria1);
        wxConsumerExample.or(criteria);
        List<WxConsumer> wxConsumers = consumerMapper.selectByExample(wxConsumerExample);
        if (wxConsumers.size() > 0) {
            ConsumerVo consumerVo = new ConsumerVo();
            BeanCopierUtil.copy(wxConsumers.get(0),consumerVo );
            consumerVo.setHas_mobile(false);
            if (consumerVo.getMobile() != null) {
                consumerVo.setHas_mobile(true);
            }
            return consumerVo;
        } else {
            return null;
        }
    }
}
