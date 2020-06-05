package com.hotel.minapp.api.v2.controller;


import com.hotel.common.ApiResponse;
import com.hotel.common.PageModel;
import com.hotel.common.query.NotifySmsQuery;
import com.hotel.common.service.SmsService;
import com.hotel.minapp.interceptor.PassToken;
import com.hotel.minapp.query.PackageOrderQuery;
import com.hotel.minapp.query.PageQuery;
import com.hotel.minapp.query.TimeQuery;
import com.hotel.minapp.service.PackageService;
import com.hotel.minapp.service.WxTempMsgService;
import com.hotel.minapp.utils.JWTUtil;
import com.hotel.minapp.vo.DayVo;
import com.hotel.minapp.vo.PackageVo;
import com.hotel.model.Package;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 套餐控制器
 *
 * @author xu
 */
@RestController
@RequestMapping(value = "/v2/package")
public class PackageOrderController {

  @Autowired
  private PackageService packageService;

  @Autowired
  private WxTempMsgService wxTempMsgService;

  @Autowired
  private SmsService smsService;

  @Value("${sms.appid}")
  private Integer appid;

  @Value("${sms.appkey}")
  private String appkey;

  @Value("${sms.notifyTemplateId}")
  private Integer templateId;

  @Value("${sms.notifyMobile}")
  private String mobile;

  /**
   * 获取某一天的可预约时间列表
   *
   * @param timeQuery
   * @return
   * @throws ParseException
   */
  @PostMapping(value = "getTimeList")
  public ApiResponse getEnabledTime(@RequestBody TimeQuery timeQuery) throws ParseException {
    List<String> enabledTimeList = packageService.getEnabledTimeList(timeQuery.getCheckStartTime());
    Map map = new HashMap();
    map.put("timeList", enabledTimeList);
    return ApiResponse.ofSuccess(map);
  }

  /**
   * 获取可预约的日期列表
   */
  @PostMapping(value = "getDayList")
  public ApiResponse getDayList(@RequestBody Package aPackage) {
    List<DayVo> dayList = packageService.getDayList(aPackage.getId());
    return ApiResponse.ofSuccess(dayList);
  }

  /**
   * 套餐列表
   *
   * @param pageQuery
   * @return
   */
  @PostMapping(value = "list")
  @PassToken
  public ApiResponse getList(@RequestBody PageQuery pageQuery) {
    PageModel<Package> list = packageService.getList(pageQuery);
    return ApiResponse.ofSuccess(list);
  }

  /**
   * 套餐详情
   *
   * @param packageQuery
   * @return
   */
  @PostMapping(value = "detail")
  public ApiResponse detail(@RequestBody Package packageQuery) {
    Integer id = packageQuery.getId();
    PackageVo detail = packageService.getDetail(id);
    return ApiResponse.ofSuccess(detail);
  }

  /**
   * 创建套餐预约订单
   *
   * @param orderQuery
   * @param request
   * @return
   */
  @PostMapping(value = "order/create")
  public ApiResponse create(@RequestBody PackageOrderQuery orderQuery, HttpServletRequest request) throws Exception {
    String openId = JWTUtil.getOpenId(request);
    Integer packageOrderId = packageService.createPackageOrder(orderQuery, openId);
    if (packageOrderId > 0) {
      try {
        // 发送模板消息
        wxTempMsgService.sendOrderSuccessMessage(packageOrderId);
        // 发送短信
        NotifySmsQuery notifySmsQuery = new NotifySmsQuery();
        notifySmsQuery.setAppid(appid);
        notifySmsQuery.setAppkey(appkey);
        notifySmsQuery.setTemplateId(templateId);
        notifySmsQuery.setOrderId(packageOrderId);
        smsService.sendNotifySms(notifySmsQuery);

      } catch (WxErrorException e) {
        e.printStackTrace();
      }
    }
    Map<String, Integer> result = new HashMap<>();
    result.put("id", packageOrderId);
    return ApiResponse.ofSuccess(result);
  }
}
