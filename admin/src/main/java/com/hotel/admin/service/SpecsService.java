package com.hotel.admin.service;

import com.hotel.admin.query.PageQuery;
import com.hotel.admin.query.SpecsItemQuery;
import com.hotel.admin.query.SpecsQuery;
import com.hotel.common.PageModel;
import com.hotel.model.Room;
import com.hotel.model.RoomSpecs;
import com.hotel.model.RoomSpecsItem;

import java.util.List;

public interface SpecsService {
    String index();
    PageModel<RoomSpecs> specList(PageQuery pageQuery);
    RoomSpecs createSpecs(SpecsQuery specs);
    void updateSpecs(RoomSpecs specs);
    void deleteSpecsRecord(Integer id);
    RoomSpecsItem createItem(SpecsItemQuery specsItemQuery);
    void updateItem(RoomSpecsItem specsItemQuery);
    void deleteSpecsItemRecord(Integer id);
    List<RoomSpecsItem> findSpecsItemListById(Integer specsId);
    List<RoomSpecs> allSpecs();
}
