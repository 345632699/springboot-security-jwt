package com.hotel.admin.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SpecsItemQuery {

    Integer id;

    @Size(max=14,min=0,message = "配置名称不允许超过14个字符")
    String name;

    @NotNull(message = "必须选择一个规格项")
    Integer specsId;

    @NotBlank(message = "规格名字不能为空")
    String specsName;

}