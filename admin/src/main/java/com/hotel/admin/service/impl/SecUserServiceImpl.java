package com.hotel.admin.service.impl;

import com.hotel.admin.dto.SecUserDto;
import com.hotel.admin.query.SecUserQuery;
import com.hotel.admin.service.BaseService;
import com.hotel.admin.service.SecUserService;
import com.hotel.model.SecUser;
import com.hotel.model.SecUserExample;
import com.hotel.model.mapper.SecUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SecUserServiceImpl implements SecUserService {

    @Autowired
    private SecUserMapper secUserMapper;

    @Override
    public void create(SecUserQuery secUserQuery) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(secUserQuery.getPassword());
        SecUser secUser = new SecUser();
        secUser.setPassword(password);
        secUser.setUsername(secUserQuery.getPhone());
        secUser.setPhone(secUserQuery.getPhone());
        secUser.setCreateTime(new Date().getTime());
        secUser.setUpdateTime(new Date().getTime());
        secUserMapper.insertSelective(secUser);
    }

    @Override
    public SecUserDto findByUserName(String username) {
        SecUserExample secUserExample = new SecUserExample();
        SecUserExample.Criteria criteria = secUserExample.createCriteria();
        criteria.andUsernameNotEqualTo(username);
        return getSecUserDto(secUserExample);
    }

    @Override
    public SecUserDto findByMobile(String mobile) {
        SecUserExample secUserExample = new SecUserExample();
        SecUserExample.Criteria criteria = secUserExample.createCriteria();
        criteria.andPhoneEqualTo(mobile);
        return getSecUserDto(secUserExample);
    }

    @Override
    public void updatePassword(SecUserQuery secUserQuery) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(secUserQuery.getPassword());
        SecUser secUser = new SecUser();
        secUser.setPassword(password);
        SecUserExample secUserExample = new SecUserExample();
        SecUserExample.Criteria criteria = secUserExample.createCriteria();
        criteria.andPhoneEqualTo(secUserQuery.getPhone());
        secUserMapper.updateByExampleSelective(secUser, secUserExample);
    }

    private SecUserDto getSecUserDto(SecUserExample secUserExample) {
        List<SecUser> secUsers = secUserMapper.selectByExample(secUserExample);
        if (secUsers.size() > 0) {
            SecUser secUser = secUsers.get(0);
            SecUserDto secUserDto = new SecUserDto();
            secUserDto.setPhone(secUser.getPhone());
            secUserDto.setNickname(secUser.getNickname());
            secUserDto.setSex(secUser.getSex());
            secUserDto.setStatus(secUser.getStatus());
            secUserDto.setCreateTime(secUser.getCreateTime());
            return secUserDto;
        }
        return null;
    }

}
