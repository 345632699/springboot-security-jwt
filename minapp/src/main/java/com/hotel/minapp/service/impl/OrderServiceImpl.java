package com.hotel.minapp.service.impl;

import com.hotel.common.Status;
import com.hotel.common.utils.BeanCopierUtil;
import com.hotel.common.utils.DateUtil;
import com.hotel.common.utils.UniqueIdGen;
import com.hotel.minapp.dto.RoomOrderDto;
import com.hotel.minapp.exception.CustomException;
import com.hotel.minapp.query.RoomOrderQuery;
import com.hotel.minapp.service.OrderService;
import com.hotel.model.*;
import com.hotel.model.mapper.RoomMapper;
import com.hotel.model.mapper.RoomOrderMapper;
import com.hotel.model.mapper.RoomOrderUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RoomOrderMapper roomOrderMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomOrderUserMapper roomOrderUserMapper;

    @Override
    public Integer createOrder(RoomOrderQuery roomOrderQuery, String openId) {
        RoomOrder roomOrder = new RoomOrder();
        BeanCopierUtil.copy(roomOrderQuery, roomOrder);
        roomOrder.setOrderSn(UniqueIdGen.genUniqueId("RO"));
        roomOrder.setCreatedAt(new Date());
        roomOrder.setUpdatedAt(new Date());
        int countDay = DateUtil.differentDays(roomOrderQuery.getCheckInTime(), roomOrderQuery.getCheckOutTime());
        roomOrder.setCountDay(countDay);

//        Calendar aCalendar = Calendar.getInstance();
//        aCalendar.setTime(roomOrderQuery.getCheckInTime());
//        int in = aCalendar.get(Calendar.DAY_OF_YEAR);
//        aCalendar.setTime(roomOrderQuery.getCheckOutTime());
//        int out = aCalendar.get(Calendar.DAY_OF_YEAR);
//        int countDays = out - in;

        RoomOrderExample roomOrderExample = new RoomOrderExample();
        RoomOrderExample.Criteria criteria = roomOrderExample.createCriteria();
        criteria.andCheckInTimeGreaterThanOrEqualTo(roomOrderQuery.getCheckInTime());
        criteria.andCheckInTimeLessThan(roomOrderQuery.getCheckOutTime());
        criteria.andStatusLessThan(3);
        criteria.andPayStatusEqualTo(1);
        criteria.andRoomIdEqualTo(roomOrderQuery.getRoomId());
        int count = roomOrderMapper.countByExample(roomOrderExample);
        if (count > 0) {
            throw new CustomException(Status.CAN_NOT_ORDER_REPEAT);
        }

        Room room = roomMapper.selectByPrimaryKey(roomOrderQuery.getRoomId());
        if (room == null) {
            throw new CustomException(Status.ROOM_NOT_EXIST);
        }
        BigDecimal rentPrice = room.getRentPrice();
        BigDecimal realPayRentPrice = rentPrice.multiply(BigDecimal.valueOf(countDay));
        roomOrder.setRealPayRentPrice(realPayRentPrice);
        roomOrder.setRentPrice(rentPrice);
        roomOrder.setOpenId(openId);
        roomOrder.setDepositPrice(room.getDepositPrice());
        roomOrder.setRealPayDepositPrice(room.getDepositPrice());
        roomOrderMapper.insertSelective(roomOrder);

        Integer insertId = roomOrder.getId();
        List<RoomOrderUser> users = roomOrderQuery.getUsers();
        users.forEach(item -> {
            RoomOrderUser roomOrderUser = new RoomOrderUser();
            roomOrderUser.setRoomId(roomOrderQuery.getRoomId());
            roomOrderUser.setUserName(item.getUserName());
            roomOrderUser.setOrderId(insertId);
            roomOrderUser.setUserIdno(item.getUserIdno());
            roomOrderUserMapper.insertSelective(roomOrderUser);
        });
        return roomOrder.getId();
    }

    @Override
    public RoomOrderDto getInfoById(int id) {
        RoomOrder roomOrder = roomOrderMapper.selectByPrimaryKey(id);
        RoomOrderDto roomOrderDto = new RoomOrderDto();
        BeanCopierUtil.copy(roomOrder, roomOrderDto);
        RoomOrderUserExample roomOrderUserExample = new RoomOrderUserExample();
        RoomOrderUserExample.Criteria criteria = roomOrderUserExample.createCriteria();
        criteria.andOrderIdEqualTo(id);
        List<RoomOrderUser> roomOrderUsers = roomOrderUserMapper.selectByExample(roomOrderUserExample);
        roomOrderDto.setUsers(roomOrderUsers);
        Room room = roomMapper.selectByPrimaryKey(roomOrderDto.getRoomId());
        roomOrderDto.setAddress(room.getAddress());
        return roomOrderDto;
    }

    @Override
    public void cancelOrder(Integer orderId) {
        RoomOrder roomOrder = roomOrderMapper.selectByPrimaryKey(orderId);
        // 更新状态为4 取消状态 0 未入住 1 已入住 2 待退房 3 已完成 4 已取消
        roomOrder.setStatus(4);
        roomOrderMapper.updateByPrimaryKeySelective(roomOrder);
    }
}
