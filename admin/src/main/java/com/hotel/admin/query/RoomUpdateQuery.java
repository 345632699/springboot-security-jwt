package com.hotel.admin.query;

import com.hotel.model.Room;

public class RoomUpdateQuery extends Room {
    Integer[] settingIds;
    Integer[] specItemIds;
    String[] imagesIds;

    public Integer[] getSettingIds() {
        return settingIds;
    }

    public void setSettingIds(Integer[] settingIds) {
        this.settingIds = settingIds;
    }

    public Integer[] getSpecItemIds() {
        return specItemIds;
    }

    public void setSpecItemIds(Integer[] specItemIds) {
        this.specItemIds = specItemIds;
    }

    public String[] getImagesIds() {
        return imagesIds;
    }

    public void setImagesIds(String[] imagesIds) {
        this.imagesIds = imagesIds;
    }
}
