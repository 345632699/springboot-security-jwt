package com.hotel.admin.controller;

import com.hotel.common.ApiResponse;
import com.hotel.common.GetQiniuTokenUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/qiniu")
@Validated
public class QiNiuController {

  @GetMapping(value = "getToken")
  public ApiResponse getUploadToken(@NotBlank(message = "命名空间不能为空") @RequestParam String bucket) {
    GetQiniuTokenUtils getQiniuTokenUtils = new GetQiniuTokenUtils();
    String accessKey = "azHsCgWKqcWpMhgaSPRSyF7mxbiO6qLoiO-J1qZ8";
    String secretKey = "jSolkifWYcRv35URM8rE3VjFKQZ9nyJlgPI7wdlF";
    Map result = new HashMap<>();
    result.put("qiniuToken", getQiniuTokenUtils.getQiNiuToken(accessKey, secretKey, bucket));
    return ApiResponse.ofSuccess(result);
  }
}
