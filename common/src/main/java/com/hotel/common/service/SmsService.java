package com.hotel.common.service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.hotel.common.query.NotifySmsQuery;
import com.hotel.common.utils.DateUtil;
import com.hotel.model.*;
import com.hotel.model.mapper.PackageOrderMapper;
import com.hotel.model.mapper.SmsCodeMapper;
import com.hotel.model.mapper.SysSettingMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class SmsService {
    @Autowired
    SmsCodeMapper smsCodeMapper;

    @Autowired
    private SysSettingMapper settingMapper;

    @Autowired
    private PackageOrderMapper orderMapper;

    private interface SEND_STATUS{
        // 发送成功
        Integer SEND_SUCCESS = 1;
        // 发送失败
        Integer SEND_FAIL = 2;
    }

    public Integer insertRecord(SmsCode sms) {
        return smsCodeMapper.insertSelective(sms);
    }

    public Integer update(SmsCode sms) {
        return smsCodeMapper.updateByPrimaryKeySelective(sms);
    }

    public SmsCode findByPhone(String mobile) {
        SmsCodeExample smsCodeExample = new SmsCodeExample();
        smsCodeExample.setOrderByClause("id DESC");
        SmsCodeExample.Criteria criteria = smsCodeExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<SmsCode> smsCodes = smsCodeMapper.selectByExample(smsCodeExample);
        if (smsCodes.size() > 0) {
            return smsCodes.get(0);
        } else {
            return null;
        }
    }

    public boolean sendNotifySms(NotifySmsQuery notifySmsQuery) throws Exception {
        SmsCode sms = new SmsCode();

        List<SysSetting> sysSettings = settingMapper.selectByExample(new SysSettingExample());
        if (sysSettings.size() > 0) {
            SysSetting sysSetting = sysSettings.get(0);
            String[] mobileArr = {sysSetting.getNotifyMobile()};
            notifySmsQuery.setMobile(mobileArr);
            sms.setMobile(sysSetting.getNotifyMobile());
        } else {
            throw new Exception("尚未配置通知电话号码");
        }

        // 需要发送短信的手机号码
        sms.setTemplateId(String.valueOf(notifySmsQuery.getTemplateId()));
        // templateId7839对应的内容是"您的验证码是: {1}"
        // 签名
        // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请。
        String smsSign = "雾一数据";
        try {
            sms.setCode("无验证码");
            sms.setCreatedAt(new Date());
            sms.setUpdatedAt(new Date());
            Date experiedAt = new Date();
            experiedAt.setTime(experiedAt.getTime() + 5 * 60 * 1000);
            sms.setExpirationTime(experiedAt);

            insertRecord(sms);
            // 数组具体的元素个数和模板中变量个数必须一致，例如示例中 templateId:5678 对应一个变量，参数数组中元素个数也必须是一个
            PackageOrder packageOrder = orderMapper.selectByPrimaryKey(notifySmsQuery.getOrderId());

            String[] params = {packageOrder.getUserName(), packageOrder.getMobile(), DateUtil.convDateToString(packageOrder.getStartTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)};
            SmsSingleSender ssender = new SmsSingleSender(notifySmsQuery.getAppid(), notifySmsQuery.getAppkey());
            SmsSingleSenderResult result = ssender.sendWithParam("86", sms.getMobile(),
              // 签名参数未提供或者为空时，会使用默认签名发送短信
              notifySmsQuery.getTemplateId(), params, smsSign, "", "");
            System.out.println(result);
            JSONObject jsonObject = new JSONObject(result.toString());
            int recode = jsonObject.getInt("result");
            switch (recode) {
                case 0:
                    sms.setStatus(1);
                    sms.setContent("短信验证码发送成功");
                    break;
                case 1016:
                    sms.setStatus(2);
                    sms.setContent("手机号格式错误");
                    break;
                case 1023:
                    sms.setStatus(2);
                    sms.setContent("请在60s后重试");
                    break;
                case 1024:
                    sms.setStatus(2);
                    sms.setContent("请在1小时后重试");
                    break;
                default:
                    sms.setStatus(2);
                    sms.setContent("错误码" + recode + ",请带上您的错误码反馈给客服");
            }
            update(sms);
        } catch (HTTPException | JSONException | IOException | com.github.qcloudsms.httpclient.HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        }

        if (sms.getStatus().equals(SEND_STATUS.SEND_SUCCESS)) {
            return true;
        } else {
            return false;
        }
    }
}
