package com.hotel.admin.api.v2.controller;

import com.hotel.admin.query.PackageTimeSettingQuery;
import com.hotel.admin.service.SettingService;
import com.hotel.admin.vo.TimeSettingVo;
import com.hotel.common.ApiResponse;
import com.hotel.common.utils.BeanCopierUtil;
import com.hotel.model.PackageTimeSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author xu
 */
@RestController
@RequestMapping(value = "/api/v2/setting")
public class SettingV2Controller {
  @Autowired
  private SettingService settingService;

  /**
   * 更新或者添加时间设置
   * @param timeSettingQuery
   * @return
   */
  @PostMapping(value = "/time/createOrUpdate")
  public ApiResponse createOrUpdate(@RequestBody PackageTimeSettingQuery timeSettingQuery) {
    settingService.createOrUpdateSetting(timeSettingQuery);
    return ApiResponse.ofSuccess();
  }

  @PostMapping(value = "/info")
  public ApiResponse info() {
    PackageTimeSetting info = settingService.getInfo();
    TimeSettingVo timeSettingVo = new TimeSettingVo();
    if (info != null) {
      int[] ints = Arrays.stream(info.getWeekdays().split(",")).mapToInt(Integer::parseInt).toArray();
      BeanCopierUtil.copy(info, timeSettingVo);
      timeSettingVo.setWeekdays(ints);
    }
    return ApiResponse.ofSuccess(timeSettingVo);
  }
}
