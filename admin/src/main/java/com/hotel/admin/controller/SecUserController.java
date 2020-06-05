package com.hotel.admin.controller;

import com.hotel.admin.dto.SecUserDto;
import com.hotel.admin.query.SecUserQuery;
import com.hotel.admin.service.SecUserService;
import com.hotel.admin.utils.JwtUtil;
import com.hotel.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value = "/api/secUser")
@RestController
public class SecUserController {

    @Autowired
    private SecUserService secUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "create")
    public ApiResponse create(@Valid @RequestBody SecUserQuery secUserQuery) {
        secUserService.create(secUserQuery);
        return ApiResponse.ofSuccess();
    }

    @PostMapping(value = "info")
    public ApiResponse info(HttpServletRequest request) {
        String jwt = jwtUtil.getJwtFromRequest(request);
        String username = jwtUtil.getUsernameFromJWT(jwt);
        SecUserDto secUser = secUserService.findByUserName(username);

        return ApiResponse.ofSuccess(secUser);
    }
}
