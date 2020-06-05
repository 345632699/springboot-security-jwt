package com.hotel.admin.service.impl;

import com.hotel.admin.exception.CustomException;
import com.hotel.admin.query.PackageTimeSettingQuery;
import com.hotel.admin.service.BaseService;
import com.hotel.admin.service.SettingService;
import com.hotel.common.Status;
import com.hotel.common.utils.BeanCopierUtil;
import com.hotel.model.*;
import com.hotel.model.mapper.PackageTimeSettingMapper;
import com.hotel.model.mapper.RoomSettingMapper;
import com.hotel.model.mapper.RoomSettingMappingMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {

  @Autowired
  private RoomSettingMapper roomSettingMapper;

  @Autowired
  private RoomSettingMappingMapper settingMappingMapper;

  @Autowired
  private PackageTimeSettingMapper timeSettingMapper;

  @Override
  public void createOrUpdateSetting(PackageTimeSettingQuery timeSettingQuery) {
    // 插入时间设置
    PackageTimeSetting packageTimeSetting = new PackageTimeSetting();
    String weekdays = "0,1,2,3,4,5,6,7";
    if (timeSettingQuery.getWeekdays() != null) {
      weekdays = ArrayUtils.toString(timeSettingQuery.getWeekdays(), ",");
      String substring = weekdays.substring(1);
      weekdays = substring.replace("}", "");
    }
    BeanCopierUtil.copy(timeSettingQuery, packageTimeSetting);
    packageTimeSetting.setWeekdays(weekdays);
    if (timeSettingQuery.getId() == null) {
      timeSettingMapper.insertSelective(packageTimeSetting);
    } else {
      timeSettingMapper.updateByPrimaryKeySelective(packageTimeSetting);
    }
  }

  @Override
  public RoomSetting create(RoomSetting roomSetting) {
    roomSetting.setCreatedAt(new Date());
    roomSetting.setUpdatedAt(new Date());
    roomSettingMapper.insertSelective(roomSetting);
    return roomSetting;
  }

  @Override
  public void update(RoomSetting roomSetting) {
    roomSettingMapper.updateByPrimaryKey(roomSetting);
  }

  @Override
  public void delete(Integer id) {
    RoomSettingMappingExample roomSettingMappingExample = new RoomSettingMappingExample();
    RoomSettingMappingExample.Criteria criteria = roomSettingMappingExample.createCriteria();
    criteria.andRoomSettingIdEqualTo(id);
    List<RoomSettingMapping> roomSettingMappings = settingMappingMapper.selectByExample(roomSettingMappingExample);
    if (roomSettingMappings.size() > 0) {
      throw new CustomException(Status.SETTING_EXIST_RECOMMEND);
    }
    roomSettingMapper.deleteByPrimaryKey(id);
  }

  @Override
  public List<RoomSetting> allList() {
    return roomSettingMapper.selectByExample(new RoomSettingExample());
  }

  @Override
  public PackageTimeSetting getInfo() {
    List<PackageTimeSetting> packageTimeSettings = timeSettingMapper.selectByExample(new PackageTimeSettingExample());
    if (packageTimeSettings.size() > 0) {
      return packageTimeSettings.get(0);
    }
    return null;
  }
}
