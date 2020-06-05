package com.hotel.admin.service.impl;

import com.github.binarywang.wxpay.service.WxPayService;
import com.hotel.admin.dao.RoomDao;
import com.hotel.admin.dto.RoomDto;
import com.hotel.admin.query.RoomAddQuery;
import com.hotel.admin.query.RoomQuery;
import com.hotel.admin.service.RoomService;
import com.hotel.common.PageModel;
import com.hotel.model.*;
import com.hotel.model.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomSpecsMappingMapper specsMappingMapper;

    @Autowired
    private RoomSpecsItemMapper specsItemMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomSettingMappingMapper settingMappingMapper;


    @Autowired
    private RoomImagesMapper imagesMapper;

    @Autowired
    private RoomSettingMapper settingMapper;

    @Autowired
    private RoomDao roomDao;



    @Override
    public List<RoomSpecsItem> getRoomSpecs(Integer roomId) {
        RoomSpecsMappingExample roomSpecsMappingExample = new RoomSpecsMappingExample();
        RoomSpecsMappingExample.Criteria criteria = roomSpecsMappingExample.createCriteria();
        criteria.andRoomIdEqualTo(roomId);
        List<RoomSpecsMapping> roomSpecsMappings = specsMappingMapper.selectByExample(roomSpecsMappingExample);
        if (roomSpecsMappings.size() > 0) {
            List<Integer> specsItemIds = roomSpecsMappings.stream().map(RoomSpecsMapping::getRoomSpecsItemId).collect(Collectors.toList());
            RoomSpecsItemExample roomSpecsItemExample = new RoomSpecsItemExample();
            RoomSpecsItemExample.Criteria criteria1 = roomSpecsItemExample.createCriteria();
            criteria1.andIdIn(specsItemIds);
            List<RoomSpecsItem> specsItemList = specsItemMapper.selectByExample(roomSpecsItemExample);
            return specsItemList;
        }
        return null;
    }

    @Override
    public PageModel<Room> getRoomList(RoomQuery roomQuery) {
        PageModel<Room> roomDtoPageModel = new PageModel<>();

        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andApartmentIdEqualTo(roomQuery.getApartmentId());
        criteria.andDeletedEqualTo(0);

        int totalCount = roomMapper.countByExample(roomExample);

        roomExample.setLimit(roomQuery.getLimit());
        roomExample.setOffset((roomQuery.getPage() - 1) * roomQuery.getLimit());
        List<Room> rooms = roomMapper.selectByExample(roomExample);

        roomDtoPageModel.setData(rooms);
        roomDtoPageModel.setLimit(roomQuery.getLimit());
        roomDtoPageModel.setCurrentPage(roomQuery.getPage());
        roomDtoPageModel.setTotalCount(totalCount);

        return roomDtoPageModel;
    }

    @Override
    public RoomDto roomDetail(Integer id) {
        RoomDto roomDto = roomDao.selectByPrimaryKey(id);

        // 规格
        RoomSpecsMappingExample roomSpecsMappingExample = new RoomSpecsMappingExample();
        RoomSpecsMappingExample.Criteria criteria = roomSpecsMappingExample.createCriteria();
        criteria.andRoomIdEqualTo(id);
        List<RoomSpecsMapping> roomSpecsMappings = specsMappingMapper.selectByExample(roomSpecsMappingExample);
        List<RoomSpecsItem> specsItemList = Collections.EMPTY_LIST;
        if (roomSpecsMappings.size() > 0) {
            List<Integer> specItemIds = roomSpecsMappings.stream().map(RoomSpecsMapping::getRoomSpecsItemId).collect(Collectors.toList());
            RoomSpecsItemExample roomSpecsItemExample = new RoomSpecsItemExample();
            RoomSpecsItemExample.Criteria criteria1 = roomSpecsItemExample.createCriteria();
            criteria1.andIdIn(specItemIds);
            specsItemList = specsItemMapper.selectByExample(roomSpecsItemExample);
        }
        roomDto.setSpecsItemList(specsItemList);
        // 配置
        RoomSettingMappingExample roomSettingMappingExample = new RoomSettingMappingExample();
        RoomSettingMappingExample.Criteria criteria1 = roomSettingMappingExample.createCriteria();
        criteria1.andRoomIdEqualTo(id);
        List<RoomSettingMapping> roomSettingMappings = settingMappingMapper.selectByExample(roomSettingMappingExample);
        List<RoomSetting> roomSettings = Collections.EMPTY_LIST;
        if (roomSettingMappings.size() > 0) {
            List<Integer> settingIds = roomSettingMappings.stream().map(RoomSettingMapping::getRoomSettingId).collect(Collectors.toList());
            RoomSettingExample roomSettingExample = new RoomSettingExample();
            RoomSettingExample.Criteria criteria2 = roomSettingExample.createCriteria();
            criteria2.andIdIn(settingIds);
            roomSettings = settingMapper.selectByExample(roomSettingExample);
        }
        roomDto.setRoomSettings(roomSettings);
        //图片
        RoomImagesExample roomImagesExample = new RoomImagesExample();
        RoomImagesExample.Criteria criteria2 = roomImagesExample.createCriteria();
        criteria2.andRoomIdEqualTo(id);
        List<RoomImages> roomImages = imagesMapper.selectByExampleWithBLOBs(roomImagesExample);
        roomDto.setRoomImages(roomImages);

        return roomDto;
    }

    @Override
    @Transactional
    public void createRoom(RoomAddQuery roomAddQuery) {
        roomAddQuery.setDeleted(0);
        roomDao.insertSelective(roomAddQuery);
        insertRelations(roomAddQuery);
    }

    @Override
    @Transactional
    public void updateRoom(RoomAddQuery roomAddQuery) {
        roomDao.updateByPrimaryKeySelective(roomAddQuery);
        // 删除设置的关联关系
        if (roomAddQuery.getSpecItemIds() != null && roomAddQuery.getSettingIds().length > 0) {
            RoomSettingMappingExample settingMappingExample = new RoomSettingMappingExample();
            RoomSettingMappingExample.Criteria criteria = settingMappingExample.createCriteria();
            criteria.andRoomIdEqualTo(roomAddQuery.getId());
            settingMappingMapper.deleteByExample(settingMappingExample);
        }


        // 删除规格的关联关系
        if (roomAddQuery.getSpecItemIds() != null && roomAddQuery.getSpecItemIds().length > 0) {
            RoomSpecsMappingExample specsMappingExample = new RoomSpecsMappingExample();
            RoomSpecsMappingExample.Criteria criteria1 = specsMappingExample.createCriteria();
            criteria1.andRoomIdEqualTo(roomAddQuery.getId());
            specsMappingMapper.deleteByExample(specsMappingExample);
        }


        // 删除添加图片的挂链关系
        if (roomAddQuery.getImagesIds() != null && roomAddQuery.getImagesIds().length > 0) {
            RoomImagesExample roomImagesExample = new RoomImagesExample();
            RoomImagesExample.Criteria criteria2 = roomImagesExample.createCriteria();
            criteria2.andRoomIdEqualTo(roomAddQuery.getId());
            imagesMapper.deleteByExample(roomImagesExample);
        }
        insertRelations(roomAddQuery);

    }

    private void insertRelations(RoomAddQuery roomAddQuery) {
        Integer id = roomAddQuery.getId();
        // 添加房间配置
        if (roomAddQuery.getSettingIds() != null && roomAddQuery.getSettingIds().length > 0) {
            Arrays.stream(roomAddQuery.getSettingIds()).forEach(item -> {
                RoomSettingMapping roomSettingMapping = new RoomSettingMapping();
                roomSettingMapping.setRoomId(id);
                roomSettingMapping.setRoomSettingId(item);
                roomSettingMapping.setCreatedAt(new Date());
                settingMappingMapper.insertSelective(roomSettingMapping);
            });
        }

        // 添加房间规格
        if (roomAddQuery.getSpecItemIds() != null && roomAddQuery.getSpecItemIds().length > 0) {
            RoomSpecsItemExample roomSpecsItemExample = new RoomSpecsItemExample();
            RoomSpecsItemExample.Criteria criteria = roomSpecsItemExample.createCriteria();
            criteria.andIdIn(Arrays.asList(roomAddQuery.getSpecItemIds()));
            List<RoomSpecsItem> specsItemList = specsItemMapper.selectByExample(roomSpecsItemExample);
            specsItemList.stream().forEach(specItem -> {
                RoomSpecsMapping roomSpecsMapping = new RoomSpecsMapping();
                roomSpecsMapping.setRoomSpecsId(specItem.getSpecsId());
                roomSpecsMapping.setRoomId(id);
                roomSpecsMapping.setRoomSpecsItemId(specItem.getId());
                roomSpecsMapping.setCreatedAt(new Date());
                specsMappingMapper.insertSelective(roomSpecsMapping);
            });
        }

        // 添加房间图片
        if (roomAddQuery.getImagesIds() != null && roomAddQuery.getImagesIds().length > 0) {
            Arrays.stream(roomAddQuery.getImagesIds()).forEach(image -> {
                RoomImages roomImages = new RoomImages();
                roomImages.setImageUrl(image);
                roomImages.setRoomId(id);
                imagesMapper.insertSelective(roomImages);
            });
        }

    }

    @Override
    public void delete(Integer roomId) {
        Room room = roomMapper.selectByPrimaryKey(roomId);
        room.setDeleted(1);
        roomMapper.updateByPrimaryKeyWithBLOBs(room);
    }

    @Override
    public Room getById(Integer id) {
        return roomMapper.selectByPrimaryKey(id);
    }
}
