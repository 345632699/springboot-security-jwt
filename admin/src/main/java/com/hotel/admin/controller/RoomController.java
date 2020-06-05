package com.hotel.admin.controller;

import com.hotel.admin.dto.RoomDto;
import com.hotel.admin.query.RoomAddQuery;
import com.hotel.admin.query.RoomQuery;
import com.hotel.admin.service.RoomService;
import com.hotel.common.ApiResponse;
import com.hotel.common.PageModel;
import com.hotel.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xu
 */
@RestController
@RequestMapping(value = "/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "list")
    public ApiResponse roomList(@Valid @RequestBody RoomQuery roomQuery) {
        PageModel<Room> rooms = roomService.getRoomList(roomQuery);
        return ApiResponse.ofSuccess(rooms);
    }

    @GetMapping(value = "info/{id}")
    public ApiResponse roomDetail(@PathVariable Integer id) {
        RoomDto roomDto = roomService.roomDetail(id);
        return ApiResponse.ofSuccess(roomDto);
    }

    @PostMapping(value = "create")
    public ApiResponse create(@Valid @RequestBody RoomAddQuery roomAddQuery) {
        roomService.createRoom(roomAddQuery);
        return ApiResponse.ofSuccess(roomAddQuery);
    }

    @PostMapping(value = "update")
    public ApiResponse update(@RequestBody RoomAddQuery roomAddQuery) {
        roomService.updateRoom(roomAddQuery);
        return ApiResponse.ofSuccess();
    }

    @PostMapping(value = "delete")
    public ApiResponse delete(@RequestBody Room room) {
        roomService.delete(room.getId());
        return ApiResponse.ofSuccess();
    }

}
