package com.hotel.minapp.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CheckInQuery {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    Date startAt;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    Date endAt;
    @NotNull(message = "房间ID不能为空")
    Integer roomId;
}
