package com.hotel.admin.service;

import com.hotel.admin.dto.RoomDto;
import com.hotel.admin.query.RoomAddQuery;
import com.hotel.admin.query.RoomQuery;
import com.hotel.common.PageModel;
import com.hotel.model.Room;
import com.hotel.model.RoomSpecsItem;

import java.util.List;

public interface RoomService {
    List<RoomSpecsItem> getRoomSpecs(Integer roomId);

    /**
     * 房间分页列表
     * @param roomQuery
     * @return
     */
    PageModel<Room> getRoomList(RoomQuery roomQuery);
    RoomDto roomDetail(Integer id);
    void createRoom(RoomAddQuery roomAddQuery);
    void updateRoom(RoomAddQuery roomAddQuery);

    /**
     * 房间删除
     * @param roomId
     */
    void delete(Integer roomId);

    /**
     * 根据主键Id获取
     * @param id
     * @return
     */
    Room getById(Integer id);
}
