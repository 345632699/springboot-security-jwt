package com.hotel.admin.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SpecsQuery {
    @NotBlank(message = "规格名称不能为空")
    String name;
    Integer id;
}
