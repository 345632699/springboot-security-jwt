package com.hotel.minapp.service;

import com.hotel.minapp.dto.RoomOrderDto;
import com.hotel.minapp.query.RoomOrderQuery;
import com.hotel.model.RoomOrder;

import java.util.List;

/**
 * @author xu
 */
public interface OrderService {
    /**
     * 创建订单
     * @param roomOrderQuery
     * @param openId
     * @return
     */
    Integer createOrder(RoomOrderQuery roomOrderQuery, String openId);

    /**
     * 根据主键ID 获取订单详情
     * @param id
     * @return
     */
    RoomOrderDto getInfoById(int id);

    /**
     * 取消订单
     * @param orderId 订单ID
     */
    void cancelOrder(Integer orderId);

}
