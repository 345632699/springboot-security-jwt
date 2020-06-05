package com.hotel.minapp.query;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginQuery {

	@NotBlank(message = "appid不能为空")
	private String appid;
	@NotBlank(message = "signature不能为空")
	private String signature;
	@NotBlank(message = "rawData不能为空")
	private String rawData;
	@NotBlank(message = "encryptedData不能为空")
	private String encryptedData;
	@NotBlank(message = "iv不能为空")
	private String iv;
	@NotBlank(message = "code不能为空")
	private String code;
}
