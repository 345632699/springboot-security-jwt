package com.hotel.admin.service.impl;

import com.hotel.admin.exception.CustomException;
import com.hotel.admin.query.ApartmentQuery;
import com.hotel.admin.service.ApartmentService;
import com.hotel.admin.service.BaseService;
import com.hotel.common.Status;
import com.hotel.model.Apartment;
import com.hotel.model.ApartmentExample;
import com.hotel.model.RoomExample;
import com.hotel.model.mapper.ApartmentMapper;
import com.hotel.model.mapper.RoomMapper;
import com.qiniu.rtc.model.RoomAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentMapper apartmentMapper;

    @Autowired
    RoomMapper roomMapper;

    @Override
    public List<Apartment> list() {

        return apartmentMapper.selectByExample(new ApartmentExample());
    }

    @Override
    public Apartment createApartment(ApartmentQuery apartmentQuery) {
        Apartment apartment = new Apartment();
        apartment.setName(apartmentQuery.getName());
        apartment.setAddress(apartmentQuery.getAddress());
        apartment.setCreatedAt(new Date());
        apartment.setUpdatedAt(new Date());
        apartmentMapper.insertSelective(apartment);
        return apartment;
    }

    @Override
    public void updateApartment(Apartment apartment) {
        apartmentMapper.updateByPrimaryKeySelective(apartment);
    }

    @Override
    public void deleteApartment(Integer id) {
        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andApartmentIdEqualTo(id);
        int i = roomMapper.countByExample(roomExample);
        if (i > 0 ) {
            throw new CustomException(Status.EXIST_RECOMMEND);
        }
        apartmentMapper.deleteByPrimaryKey(id);
    }
}
