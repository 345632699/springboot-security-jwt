package com.hotel.admin.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ApartmentQuery {
    @Size(max = 10, min = 1, message = "房源名称不允许超过10个字符")
    String name;
    @Size(max = 35, min = 1, message = "房源名称不允许超过35个字符")
    String address;
    String keyword;
}
