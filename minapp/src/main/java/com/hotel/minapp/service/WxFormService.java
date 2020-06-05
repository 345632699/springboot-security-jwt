package com.hotel.minapp.service;

import com.hotel.minapp.query.FormQuery;
import com.hotel.model.WxFormId;

import java.util.List;

/**
 * @author xu
 */
public interface WxFormService {

    /**
     * 插入用户提交Id
     * @param formQuery
     * @param openId
     */
    void create(FormQuery formQuery, String openId);
}
