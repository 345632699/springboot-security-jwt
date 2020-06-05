package com.hotel.minapp.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PhoneQuery {
  @NotBlank(message = "appid 不能为空")
  String appid;
  @NotBlank(message = "signature 不能为空")
  String signature;
  @NotBlank(message = "rawData 不能为空")
  String rawData;
  @NotBlank(message = "encryptedData 不能为空")
  String encryptedData;
  @NotBlank(message = "iv 不能为空")
  String iv;
  @NotBlank(message = "code 不能为空")
  String code;
}
