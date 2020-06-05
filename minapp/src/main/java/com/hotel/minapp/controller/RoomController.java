package com.hotel.minapp.controller;

import com.hotel.common.ApiResponse;
import com.hotel.minapp.dto.ReserveList;
import com.hotel.minapp.dto.RoomDto;
import com.hotel.minapp.query.CheckInQuery;
import com.hotel.minapp.query.RoomQuery;
import com.hotel.minapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xu
 */
@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Value("${check.month}")
    private Integer month;

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "list")
    public ApiResponse list(@RequestBody RoomQuery roomQuery){
        return ApiResponse.ofSuccess(roomService.getRoomList(roomQuery));
    }

    @GetMapping(value = "info")
    public ApiResponse info(@RequestParam Integer id) {
        RoomDto roomDto = roomService.roomInfo(id);
        return ApiResponse.ofSuccess(roomDto);
    }

    @PostMapping(value = "canNotReserveList")
    public ApiResponse canNotReserveList(@Valid @RequestBody CheckInQuery checkInQuery){
        ReserveList reserveList = roomService.canNotReserveList(checkInQuery, month);
        return ApiResponse.ofSuccess(reserveList);
    }

}
