package com.hotel.minapp.dto;

import com.hotel.model.Room;
import com.hotel.model.RoomImages;
import com.hotel.model.RoomSetting;
import com.hotel.model.RoomSpecsItem;

import java.util.List;

public class RoomDto extends Room {
    List<RoomImages> roomImages;
    List<RoomSetting> roomSettings;
    List<RoomSpecsItem> specsItemList;
    String apartmenetAddress;

    public List<RoomImages> getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(List<RoomImages> roomImages) {
        this.roomImages = roomImages;
    }

    public List<RoomSetting> getRoomSettings() {
        return roomSettings;
    }

    public void setRoomSettings(List<RoomSetting> roomSettings) {
        this.roomSettings = roomSettings;
    }

    public List<RoomSpecsItem> getSpecsItemList() {
        return specsItemList;
    }

    public void setSpecsItemList(List<RoomSpecsItem> specsItemList) {
        this.specsItemList = specsItemList;
    }

    public String getApartmenetAddress() {
        return apartmenetAddress;
    }

    public void setApartmenetAddress(String apartmenetAddress) {
        this.apartmenetAddress = apartmenetAddress;
    }
}
