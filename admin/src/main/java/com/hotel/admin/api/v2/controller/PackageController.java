package com.hotel.admin.api.v2.controller;

import com.hotel.admin.query.*;
import com.hotel.admin.service.PackageService;
import com.hotel.common.ApiResponse;
import com.hotel.common.PageModel;
import com.hotel.common.utils.GsonUtil;
import com.hotel.model.Package;
import com.hotel.model.PackageOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 套餐控制器
 * @author xu
 */
@RestController
@RequestMapping(value = "/api/v2/package")
public class PackageController {

  @Autowired
  private PackageService packageService;

  /**
   * 套餐列表
   * @param pageQuery
   * @return
   */
  @PostMapping(value = "list")
  public ApiResponse list(@Valid @RequestBody PageQuery pageQuery) {
    PageModel<Package> list = packageService.getList(pageQuery);
    return ApiResponse.ofSuccess(list);
  }

  /**
   * 创建套餐订单
   * @param packageQuery
   * @return
   */
  @PostMapping(value = "create")
  public ApiResponse create(@RequestBody PackageQuery packageQuery) {
    packageService.create(packageQuery);
    return ApiResponse.ofSuccess();
  }

  /**
   * 更新套餐信息
   * @param packageQuery
   * @return
   */
  @PostMapping(value = "update")
  public ApiResponse update(@RequestBody PackageQuery packageQuery) {
    packageService.update(packageQuery);
    return ApiResponse.ofSuccess();
  }

  /**
   * 订单详情
   * @param packageQuery
   * @return
   */
  @PostMapping(value = "detail")
  public ApiResponse detail(@RequestBody Package packageQuery) {
    PackageQuery detail = packageService.getDetail(packageQuery.getId());
    return ApiResponse.ofSuccess(detail);
  }


  @PostMapping(value = "publishOrDown")
  public ApiResponse publish(@RequestBody PublishQuery publishQuery) {
    packageService.publishOrDown(publishQuery);
    return ApiResponse.ofSuccess();
  }

  @PostMapping(value = "delete")
  public ApiResponse publish(@RequestBody Package apackage) {
    packageService.delete(apackage.getId());
    return ApiResponse.ofSuccess();
  }

}
