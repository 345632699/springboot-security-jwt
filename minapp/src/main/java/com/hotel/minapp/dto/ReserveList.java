package com.hotel.minapp.dto;

import java.util.Map;

/**
 * @author xu
 */
public class ReserveList {
    Map<String, Boolean> list;
    /**
     * 入住时间
     */
    String checkInTime;
    /**
     * 退房时间
     */
    String checkOutTime;

    public Map<String, Boolean> getList() {
        return list;
    }

    public void setList(Map<String, Boolean> list) {
        this.list = list;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
