package com.hotel.minapp.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoomQuery {
    @NotNull(message = "页码不能为空")
    Integer page;
    @NotNull(message = "每页条数不能为空")
    Integer limit;
    String keyword;
    Integer apartmentId;
}
