package com.hotel.admin.controller;

import com.hotel.admin.service.SettingService;
import com.hotel.common.ApiResponse;
import com.hotel.model.RoomSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @PostMapping(value = "create")
    public ApiResponse create(@RequestBody RoomSetting roomSetting) {
        RoomSetting setting = settingService.create(roomSetting);
        return ApiResponse.ofSuccess(setting);
    }

    @PostMapping(value = "update")
    public ApiResponse update(@RequestBody RoomSetting roomSetting) {
        settingService.update(roomSetting);
        return ApiResponse.ofSuccess();
    }

    @PostMapping(value = "delete/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        settingService.delete(id);
        return ApiResponse.ofSuccess();
    }

    @GetMapping(value = "all")
    public ApiResponse allSettingList() {
        List<RoomSetting> roomSettings = settingService.allList();
        return ApiResponse.ofSuccess(roomSettings);
    }

}
