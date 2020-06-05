package com.hotel.admin.service;

import com.hotel.admin.query.PackageQuery;
import com.hotel.admin.query.PageQuery;
import com.hotel.admin.query.PublishQuery;
import com.hotel.common.PageModel;
import com.hotel.model.Package;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author xu
 */
public interface PackageService {
    /**
     * 获取套餐列表
     * @param pageQuery
     * @return
     */
    PageModel<Package> getList(PageQuery pageQuery);

    /**
     * 创建套餐
     * @param packageQuery
     */
    Integer create(PackageQuery packageQuery);

    /**
     * 根据主键Id 更新套餐
     * @param packageQuery
     */
    void update(PackageQuery packageQuery);

    /**
     * 根据ID获取套餐详情
     * @param id
     * @return
     */
    PackageQuery getDetail(Integer id);

    /**
     * 套餐上下架
     * operationType 0 下架 1 上架
     * @param publishQuery
     */
    void publishOrDown(PublishQuery publishQuery);

    /**
     * 根据ID 删除套餐
     * @param id
     */
    void delete(Integer id);

}
