package com.hotel.admin.service;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.hotel.admin.dto.RoomOrderDto;
import com.hotel.admin.query.*;
import com.hotel.common.PageModel;
import com.hotel.model.PackageOrder;
import com.hotel.model.RoomOrder;

import java.util.List;

public interface OrderService {
    /**
     * 分页获取订单列表
     * @param orderQuery
     * @return
     */
    PageModel<RoomOrderDto> getOrderListByPage(OrderQuery orderQuery);

    /**
     * 退房
     * @param checkOutOrCancelQuery
     * @throws WxPayException
     */
    void checkout(CheckOutOrCancelQuery checkOutOrCancelQuery) throws WxPayException;

    /**
     * 取消订单
     * @param cancelQuery
     * @throws WxPayException
     */
    void cancel(CheckOutOrCancelQuery cancelQuery) throws Exception;

    /**
     * 获取需要提醒的订单  入住前一天
     * @return
     */
    List<RoomOrder> getRemindList();

    /**
     * 获取套餐订单列表
     * @param selectQuery
     * @return
     */
    PageModel<PackageOrder> getPackageOrderList(PackageOrderSelectQuery selectQuery);

    /**
     * 取消订单
     * @param cancelOrderQuery
     */
    void cancelPackageId(CancelOrderQuery cancelOrderQuery);

    /**
     * 核销完成订单
     * @param id
     */
    void confirmOrder(Integer id);

    /**
     * 发送房间订单密码
     * @param id 房间Id
     * @param appid 短信appid
     * @param appkey 短信服务商 appkey
     */
    void sendPasswordMsg(Integer id, String appid, String appkey);
}
