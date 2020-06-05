package com.hotel.minapp.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.model.RoomOrderUser;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class RoomOrderQuery {
    @NotNull(message = "房间ID不能为空")
    Integer roomId;
    @NotBlank(message = "房间标题不能为空")
    String roomName;
    @NotBlank(message = "姓名不能为空")
    String name;
    @NotBlank(message = "身份证号不能为空")
    String idNo;
    @NotNull(message = "入住时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="GMT+8")
    Date checkInTime;
    @NotNull(message = "退房时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="GMT+8")
    Date checkOutTime;
    @NotBlank(message = "手机号不能为空")
    String mobile;
    @NotNull(message = "入住人不能为空")
    List<RoomOrderUser> users;
}
