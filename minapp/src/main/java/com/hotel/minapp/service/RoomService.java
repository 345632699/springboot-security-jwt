package com.hotel.minapp.service;

import com.hotel.common.PageModel;
import com.hotel.minapp.dto.ReserveList;
import com.hotel.minapp.dto.RoomDto;
import com.hotel.minapp.query.CheckInQuery;
import com.hotel.minapp.query.CheckOutQuery;
import com.hotel.minapp.query.RoomQuery;

public interface RoomService {
    /**
     * 获取房间列表
     * @param roomQuery
     * @return
     */
    PageModel<RoomDto> getRoomList(RoomQuery roomQuery);

    /**
     * 房间详情
     * @param id
     * @return
     */
    RoomDto roomInfo(Integer id);

    /**
     * 不能预订的列表
     * @param checkInQuery
     * @param month
     * @return
     */
    ReserveList canNotReserveList(CheckInQuery checkInQuery, Integer month);
}
