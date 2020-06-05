package com.hotel.admin.controller;

import com.hotel.common.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base")
public class BaseController {

    public ApiResponse index() {
        return ApiResponse.ofSuccess();
    }
}
