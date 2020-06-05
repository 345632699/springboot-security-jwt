package com.hotel.admin.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SecUserQuery {
    String username;
    @NotBlank(message = "密码不能为空")
    String password;
    @NotBlank(message = "确认密码不能为空")
    String confirmPassword;
    @NotBlank(message = "手机号码不能为空")
    String phone;
    String code;
}
