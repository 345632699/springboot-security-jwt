package com.hotel.minapp.service.impl;

import com.hotel.common.PageModel;
import com.hotel.common.Status;
import com.hotel.common.utils.DateUtil;
import com.hotel.minapp.dao.RoomDao;
import com.hotel.minapp.dto.ReserveList;
import com.hotel.minapp.dto.RoomDto;
import com.hotel.minapp.query.CheckInQuery;
import com.hotel.minapp.query.CheckOutQuery;
import com.hotel.minapp.query.RoomQuery;
import com.hotel.minapp.service.RoomService;
import com.hotel.model.*;
import com.hotel.model.mapper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private interface orderPayStatus {
        Integer UNPAY = 0;
        Integer PAID = 1;
        Integer WAIT_REFUND = 2;
        Integer REFUND_DONE = 3;
    }

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private RoomImagesMapper roomImagesMapper;

    @Autowired
    private RoomSpecsMappingMapper specsMappingMapper;

    @Autowired
    private RoomSpecsItemMapper specsItemMapper;

    @Autowired
    private RoomSettingMappingMapper settingMappingMapper;

    @Autowired
    private RoomSettingMapper settingMapper;

    @Autowired
    private RoomOrderMapper roomOrderMapper;

    @Autowired
    private ApartmentMapper apartmentMapper;

    @Override
    public PageModel<RoomDto> getRoomList(RoomQuery roomQuery) {
        PageModel<RoomDto> roomDtoPageModel = new PageModel<>();

        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andDeletedEqualTo(0);
        if (roomQuery.getApartmentId() != null) {
            criteria.andApartmentIdEqualTo(roomQuery.getApartmentId());
        }
        if (roomQuery.getKeyword() != null) {
            criteria.andTitleLike("%" + roomQuery.getKeyword() + "%");
        }
        int totalCount = roomMapper.countByExample(roomExample);
        roomExample.setLimit(roomQuery.getLimit());
        roomExample.setOffset((roomQuery.getPage() - 1) * roomQuery.getLimit());
        List<RoomDto> rooms = roomDao.selectByExampleWithBLOBs(roomExample);
        roomDtoPageModel.setData(rooms);
        roomDtoPageModel.setCurrentPage(roomQuery.getPage());
        roomDtoPageModel.setTotalCount(totalCount);

        return roomDtoPageModel;
    }

    @Override
    public RoomDto roomInfo(Integer id) {
        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria roomExampleCriteria = roomExample.createCriteria();
        roomExampleCriteria.andIdEqualTo(id);
        List<RoomDto> roomDtos = roomDao.selectByExampleWithBLOBs(roomExample);
        RoomDto roomDto = null;
        if (roomDtos.size() > 0) {
            roomDto = roomDtos.get(0);
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

            RoomImagesExample roomImagesExample = new RoomImagesExample();
            RoomImagesExample.Criteria roomImagesExampleCriteria = roomImagesExample.createCriteria();
            roomImagesExampleCriteria.andRoomIdEqualTo(id);
            // 房间图片
            List<RoomImages> roomImages = roomImagesMapper.selectByExampleWithBLOBs(roomImagesExample);
            roomDto.setRoomImages(roomImages);

            Integer apartmentId = roomDto.getApartmentId();
            Apartment apartment = apartmentMapper.selectByPrimaryKey(apartmentId);
            roomDto.setApartmenetAddress(apartment.getAddress());
        }
        return roomDto;
    }

    @Override
    public ReserveList canNotReserveList(CheckInQuery checkInQuery, Integer month) {
        checkInQuery.setStartAt(new Date());
        checkInQuery.setEndAt(DateUtil.addMonth(new Date(), month));
        RoomOrderExample roomOrderExample = new RoomOrderExample();
        RoomOrderExample.Criteria criteria = roomOrderExample.createCriteria();
        criteria.andCheckInTimeGreaterThan(checkInQuery.getStartAt());
        criteria.andCheckOutTimeLessThan(DateUtil.addDays(checkInQuery.getEndAt(), 1));
        criteria.andRoomIdEqualTo(checkInQuery.getRoomId());
        criteria.andPayStatusEqualTo(orderPayStatus.PAID);
        // 0 未入住 1 已入住 2 待退房 3 已完成 4 已取消
        criteria.andStatusLessThan(3);
        List<RoomOrder> roomOrders = roomOrderMapper.selectByExample(roomOrderExample);
        Map<String, Boolean> canNotReserveList = new LinkedHashMap<>();
        if (roomOrders.size() > 0) {
            // 找出已经出租的时间
            List<Map<String, Date>> timeMaps = roomOrders.stream().map(item -> {
                Map<String, Date> dateMap = new HashMap<>();
                dateMap.put("checkInTime", item.getCheckInTime());
                dateMap.put("checkOutTime", item.getCheckOutTime());
                return dateMap;
            }).collect(Collectors.toList());

            // 轮询日期
            Date end = DateUtil.addDays(checkInQuery.getEndAt(), 1);

            for (Date start = checkInQuery.getStartAt(); start.compareTo(end) < 0; ) {
                Date currentTime = start;
                String s = DateUtil.convDateToString(currentTime, "yyyy-MM-dd");
                canNotReserveList.put(s, true);
                timeMaps.stream().forEach(item -> {
                    // 初始化开始时间
                    Date checkInTime= item.get("checkInTime");
                    String s2 = DateUtil.dateToString(checkInTime, "yyyy-MM-dd");
                    Date startTime = new Date();
                    try {
                        startTime = DateUtil.stringToDate(s2, "yyyy-MM-dd");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // 初始化结束时间
                    Date checkOutTime = item.get("checkOutTime");
                    String s1 = DateUtil.dateToString(checkOutTime, "yyyy-MM-dd");
                    Date endTime = new Date();

                    try {
                        endTime = DateUtil.stringToDate(s1, "yyyy-MM-dd");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // 大于等于开始时间 小于结束时间
                    if (currentTime.compareTo(startTime) >= 0 && currentTime.compareTo(endTime) < 0) {
                        canNotReserveList.put(s, false);
                    }
                });
                Date now = new Date();
                if (now.compareTo(currentTime) > 0) {
                    canNotReserveList.put(s, false);
                }
                start = DateUtil.addDays(start, 1);
            }

        } else {
            Date end = DateUtil.addDays(checkInQuery.getEndAt(), 1);
            for (Date start = checkInQuery.getStartAt(); start.compareTo(end) < 0; ) {
                Date finalStart = start;
                Date now = new Date();
                String s = DateUtil.convDateToString(finalStart, "yyyy-MM-dd");
                if (now.compareTo(start) > 0) {
                    canNotReserveList.put(s, false);
                } else {
                    canNotReserveList.put(s, true);
                }
                start = DateUtil.addDays(start, 1);
            }
        }
        Room room = roomMapper.selectByPrimaryKey(checkInQuery.getRoomId());
        ReserveList reserveList = new ReserveList();
        reserveList.setList(canNotReserveList);
        reserveList.setCheckInTime(room.getCheckInTime());
        reserveList.setCheckOutTime(room.getCheckOutTime());
        return reserveList;
    }

}
