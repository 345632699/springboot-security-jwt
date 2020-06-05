package com.hotel.admin.dao;

import com.hotel.model.RoomOrder;

import java.util.List;

/**
 * @author xu
 */
public interface RoomOrderDao {
    /**
     * 获取需要提醒的订单列表
     * @return
     */
    List<RoomOrder> getRemindList();
}