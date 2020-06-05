package com.hotel.admin.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.hotel.admin.dto.SecUserDto;
import com.hotel.admin.exception.CustomException;
import com.hotel.admin.service.SecUserService;
import com.hotel.common.ApiResponse;
import com.hotel.common.Status;
import com.hotel.common.service.SmsService;
import com.hotel.common.utils.StringUtil;
import com.hotel.model.SmsCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    SecUserService secUserService;

    private interface SEND_STATUS{
        // 发送成功
        Integer SEND_SUCCESS = 1;
        // 发送失败
        Integer SEND_FAIL = 2;
    }


    @Value("${sms.appid}")
    private String appid;

    @Value("${sms.appkey}")
    private String appkey;

    @Value("${sms.templateId}")
    private String templateId;


    @PostMapping("/send")
    public ApiResponse sendSms(@RequestBody SmsCode sms) {
        SecUserDto user = secUserService.findByMobile(sms.getMobile());
        if (user == null) {
            throw new CustomException(Status.USER_NOT_EXIST);
        }

        // 需要发送短信的手机号码
        String[] phoneNumbers = {sms.getMobile()};

        // 短信模板 ID，需要在短信应用中申请
        sms.setTemplateId(String.valueOf(templateId));
        // templateId7839对应的内容是"您的验证码是: {1}"
        // 签名
        // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请。
        String smsSign = "雾一数据";
        try {
            Integer code = (int) (Math.random() * 8999) + 1000 + 1;

            sms.setCode(code.toString());
            sms.setCreatedAt(new Date());
            sms.setUpdatedAt(new Date());
            Date experiedAt = new Date();
            experiedAt.setTime(experiedAt.getTime() + 5 * 60 * 1000);
            sms.setExpirationTime(experiedAt);

            smsService.insertRecord(sms);
            // 数组具体的元素个数和模板中变量个数必须一致，例如示例中 templateId:5678 对应一个变量，参数数组中元素个数也必须是一个
            String[] params = {String.valueOf(code), "5"};
            SmsSingleSender ssender = new SmsSingleSender(Integer.valueOf(appid), appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    Integer.valueOf(templateId), params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
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
            smsService.update(sms);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
        if (sms.getStatus().equals(SEND_STATUS.SEND_SUCCESS)) {
            return ApiResponse.of(200, sms.getContent(), "");
        } else {
            return ApiResponse.of(400, sms.getContent(), "");
        }
    }


    public ApiResponse verify(String code, String phone) {
//        if (StringUtils.isAnyBlank(String.valueOf(sms.getCode()), sms.getPhoneNumber())) {
//            return ResultJson.failure(ResultCode.BAD_REQUEST);
//        }
        SmsCode record = smsService.findByPhone(phone);
        if (record == null && record.getStatus().equals(SEND_STATUS.SEND_FAIL)) {
            return ApiResponse.of(400, "验证码已失效", "");
        }
        if (StringUtil.isBlank(record.getMobile())) {
            return ApiResponse.of(400, "验证码错误", "");
        }
        if (record.getExpirationTime().compareTo(new Date()) < 0) {
            return ApiResponse.of(400, "验证码已过期", "");
        }
        if (record.getCode().equals(1)) {
            return ApiResponse.of(400, "验证码已失效", "");
        }
        if (!record.getCode().equals(code)) {
            return ApiResponse.of(400, "验证码错误", "");
        }
        return ApiResponse.of(200, "验证成功", "");
    }
}
