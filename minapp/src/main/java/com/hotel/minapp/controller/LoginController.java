package com.hotel.minapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.hotel.common.ApiResponse;
import com.hotel.common.BaseException;
import com.hotel.common.CommonResult;
import com.hotel.common.Status;
import com.hotel.common.utils.GsonUtil;
import com.hotel.minapp.config.WxMaConfiguration;
import com.hotel.minapp.exception.CustomException;
import com.hotel.minapp.interceptor.PassToken;
import com.hotel.minapp.query.LoginQuery;
import com.hotel.minapp.query.PhoneQuery;
import com.hotel.minapp.service.ConsumerService;
import com.hotel.minapp.utils.JWTUtil;
import com.hotel.model.WxConsumer;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  @Value("${app.name}")
  private String appName;


  @Autowired
  private ConsumerService consumerService;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  /**
   * <pre>
   * 获取用户信息接口 登录 首次注册
   * </pre>
   */
  @PostMapping(value = "login")
  public ApiResponse login(@Valid @RequestBody LoginQuery loginQuery) {
    Map<String, Object> map = new HashMap<>();
    map.put("has_mobile", false);
    if (StringUtils.isBlank(loginQuery.getCode())) {
      throw new CustomException(Status.ERROR);
    }

    final WxMaService wxService = WxMaConfiguration.getMaService(loginQuery.getAppid());

    try {
      WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(loginQuery.getCode());

      WxMaUserInfo wxMaUserInfo = GsonUtil.GsonToBean(loginQuery.getRawData(), WxMaUserInfo.class);
      wxMaUserInfo.setOpenId(session.getOpenid());

      // 用户信息校验
      String sessionKey = session.getSessionKey();
      stringRedisTemplate.opsForValue().set(loginQuery.getCode(), sessionKey, 60 * 60 * 24 * 7, TimeUnit.SECONDS);
      List<WxConsumer> wxUser = consumerService.getConsumerByOpenId(session.getOpenid());

      if (wxUser.size() <= 0) {
        consumerService.createConsumer(wxMaUserInfo);
      } else {
        WxConsumer wxConsumer = wxUser.get(0);
        if (wxConsumer.getMobile() != null) {
          map.put("has_mobile", true);
          map.put("mobile", wxConsumer.getMobile());
        }
      }

      String sign = JWTUtil.sign(session.getOpenid());
      map.put("token", sign);
      map.put("appName", appName);
      return ApiResponse.ofSuccess(map);

    } catch (WxErrorException e) {
      throw new BaseException(500, e.getMessage());
    }
  }

  @PostMapping(value = "error")
  @PassToken
  public CommonResult error(HttpServletRequest request) {
    request.getAttribute("message");
    return CommonResult.failed();
  }

  /**
   * <pre>
   * 获取用户绑定手机号信息
   * </pre>
   */
  @PostMapping("/getMobile")
  public ApiResponse phone(@Valid @RequestBody PhoneQuery phoneQuery) throws WxErrorException {
    final WxMaService wxService = WxMaConfiguration.getMaService(phoneQuery.getAppid());

//    WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(phoneQuery.getCode());
//    LOGGER.info(session.getSessionKey());
//    LOGGER.info(session.getOpenid());
//    // 用户信息校验
//    String sessionKey = session.getSessionKey();
//    // 用户信息校验
//    if (!wxService.getUserService().checkUserInfo(sessionKey, phoneQuery.getRawData(), phoneQuery.getSignature())) {
//      return ApiResponse.of(400, "user check failed","'");
//    }

    String sessionKey = stringRedisTemplate.opsForValue().get(phoneQuery.getCode());
    // 解密
    WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, phoneQuery.getEncryptedData(), phoneQuery.getIv());
    return ApiResponse.ofSuccess(phoneNoInfo);
  }


}
