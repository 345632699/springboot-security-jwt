package com.hotel.admin.dto;

import lombok.Data;

@Data
public class SecUserDto {
    String phone;
    String nickname;
    Integer sex;
    Integer status;
    Long createTime;
}
