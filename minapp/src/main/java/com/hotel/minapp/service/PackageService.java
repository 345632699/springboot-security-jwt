package com.hotel.minapp.service;

import com.hotel.common.PageModel;
import com.hotel.minapp.query.PackageOrderQuery;
import com.hotel.minapp.query.PageQuery;
import com.hotel.minapp.vo.DayVo;
import com.hotel.minapp.vo.PackageVo;
import com.hotel.model.Package;
import com.hotel.model.PackageOrder;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author xu
 */
public interface PackageService {
    /**
     * 根据时间获取 当天可预约的时间
     * @return
     * @throws ParseException
     */
    List<String> getEnabledTimeList(Date checkStartTime) throws ParseException;

    /**
     * 查询某个套餐的可预约日期
     * @param packageId
     * @return
     */
    List<DayVo> getDayList(Integer packageId);

    /**
     * 获取套餐列表
     * @param pageQuery
     * @return
     */
    PageModel<Package> getList(PageQuery pageQuery);

    /**
     * 套餐详情
     * @param id
     * @return
     */
    PackageVo getDetail(Integer id);

    /**
     * 创建套餐预约
     * @param orderQuery
     * @param openId
     * @return
     */
    Integer createPackageOrder(PackageOrderQuery orderQuery,String openId);
}
