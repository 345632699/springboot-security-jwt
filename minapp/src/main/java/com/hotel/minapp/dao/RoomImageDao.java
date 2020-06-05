package com.hotel.minapp.dao;

import com.hotel.model.RoomImages;

import java.util.List;

public interface RoomImageDao {
    List<RoomImages> findImagesByRoomId(Integer roomId);
}
