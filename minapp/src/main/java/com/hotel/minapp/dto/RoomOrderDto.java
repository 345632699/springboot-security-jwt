package com.hotel.minapp.dto;

import com.hotel.model.RoomOrder;
import com.hotel.model.RoomOrderUser;

import java.util.List;

public class RoomOrderDto extends RoomOrder {
    List<RoomOrderUser> users;

    String address;

    public List<RoomOrderUser> getUsers() {
        return users;
    }

    public void setUsers(List<RoomOrderUser> users) {
        this.users = users;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
