package com.hotel.admin.service;

import com.hotel.admin.query.PackageTimeSettingQuery;
import com.hotel.model.PackageTimeSetting;
import com.hotel.model.RoomSetting;

import java.util.List;

public interface SettingService {
  RoomSetting create(RoomSetting roomSetting);

  void update(RoomSetting roomSetting);

  void delete(Integer id);

  List<RoomSetting> allList();

  void createOrUpdateSetting(PackageTimeSettingQuery timeSettingQuery);

  PackageTimeSetting getInfo();

}
