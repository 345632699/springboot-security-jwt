package com.hotel.admin.service.impl;

import com.hotel.admin.exception.CustomException;
import com.hotel.admin.query.PageQuery;
import com.hotel.admin.query.SpecsItemQuery;
import com.hotel.admin.query.SpecsQuery;
import com.hotel.admin.service.SpecsService;
import com.hotel.common.PageModel;
import com.hotel.common.Status;
import com.hotel.model.*;
import com.hotel.model.mapper.RoomSpecsItemMapper;
import com.hotel.model.mapper.RoomSpecsMapper;
import com.hotel.model.mapper.RoomSpecsMappingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpecsServiceImpl implements SpecsService {

    @Autowired
    private RoomSpecsMapper specsMapper;

    @Autowired
    private RoomSpecsItemMapper specsItemMapper;

    @Autowired
    private RoomSpecsMappingMapper specsMappingMapper;

    @Override
    public String index() {
        return null;
    }

    @Override
    public PageModel<RoomSpecs> specList(PageQuery pageQuery) {
        PageModel<RoomSpecs> specsPageModel = new PageModel<>();
        RoomSpecsExample roomSpecsExample = new RoomSpecsExample();
        RoomSpecsExample.Criteria criteria = roomSpecsExample.createCriteria();
        int totalCount = specsMapper.countByExample(roomSpecsExample);
        roomSpecsExample.setLimit(pageQuery.getLimit());
        roomSpecsExample.setOffset((pageQuery.getPage() - 1) * pageQuery.getLimit());
        List<RoomSpecs> roomSpecs = specsMapper.selectByExample(roomSpecsExample);
        if (pageQuery.getKeyword() != null) {
            criteria.andNameLike("%" + pageQuery.getKeyword() + "%");
        }

        specsPageModel.setCurrentPage(pageQuery.getPage());
        specsPageModel.setLimit(pageQuery.getLimit());
        specsPageModel.setData(roomSpecs);
        specsPageModel.setTotalCount(totalCount);
        return specsPageModel;
    }

    @Override
    public RoomSpecs createSpecs(SpecsQuery specs) {
        RoomSpecs roomSpecs = new RoomSpecs();
        roomSpecs.setName(specs.getName());
        roomSpecs.setCreatedAt(new Date());
        roomSpecs.setUpdatedAt(new Date());
        specsMapper.insertSelective(roomSpecs);
        return roomSpecs;
    }

    @Override
    public void updateSpecs(RoomSpecs specs) {
        specsMapper.updateByPrimaryKeySelective(specs);
    }

    @Override
    public void deleteSpecsRecord(Integer id) {
        RoomSpecsItemExample roomSpecsItemExample = new RoomSpecsItemExample();
        RoomSpecsItemExample.Criteria criteria = roomSpecsItemExample.createCriteria();
        criteria.andSpecsIdEqualTo(id);
        int i = specsItemMapper.countByExample(roomSpecsItemExample);
        if (i > 0) {
            throw new CustomException(Status.SPECS_EXIST_RECOMMEND);
        }
        specsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RoomSpecsItem createItem(SpecsItemQuery specsItemQuery) {
        RoomSpecsItem roomSpecsItem = new RoomSpecsItem();
        roomSpecsItem.setName(specsItemQuery.getName());
        roomSpecsItem.setSpecsId(specsItemQuery.getSpecsId());
        roomSpecsItem.setSpecsName(specsItemQuery.getSpecsName());
        roomSpecsItem.setCreatedAt(new Date());
        roomSpecsItem.setUpdatedAt(new Date());
        specsItemMapper.insertSelective(roomSpecsItem);
        return roomSpecsItem;
    }

    @Override
    public void updateItem(RoomSpecsItem specsItemQuery) {
        specsItemMapper.updateByPrimaryKeySelective(specsItemQuery);
    }

    @Override
    public void deleteSpecsItemRecord(Integer id) {
        RoomSpecsMappingExample roomSpecsMappingExample = new RoomSpecsMappingExample();
        RoomSpecsMappingExample.Criteria criteria = roomSpecsMappingExample.createCriteria();
        criteria.andRoomSpecsItemIdEqualTo(id);
        int i = specsMappingMapper.countByExample(roomSpecsMappingExample);
        if (i > 0) {
            throw new CustomException(Status.SPECS_EXIST_RECOMMEND);
        }
        specsItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<RoomSpecsItem> findSpecsItemListById(Integer specsId) {
        RoomSpecsItemExample roomSpecsItemExample = new RoomSpecsItemExample();
        RoomSpecsItemExample.Criteria criteria = roomSpecsItemExample.createCriteria();
        criteria.andSpecsIdEqualTo(specsId);
        List<RoomSpecsItem> roomSpecsItems = specsItemMapper.selectByExample(roomSpecsItemExample);
        return roomSpecsItems;
    }

    @Override
    public List<RoomSpecs> allSpecs() {
        List<RoomSpecs> roomSpecs = specsMapper.selectByExample(new RoomSpecsExample());
        return roomSpecs;
    }

}
