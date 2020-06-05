package com.hotel.admin.service;

import com.hotel.admin.dto.SecUserDto;
import com.hotel.admin.query.SecUserQuery;

public interface SecUserService {
    void create(SecUserQuery secUserQuery);
    SecUserDto findByUserName(String username);
    SecUserDto findByMobile(String mobile);
    void updatePassword(SecUserQuery secUserQuery);
}
