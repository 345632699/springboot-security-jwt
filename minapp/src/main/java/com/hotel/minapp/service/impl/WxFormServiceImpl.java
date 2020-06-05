package com.hotel.minapp.service.impl;

import com.hotel.common.utils.DateUtil;
import com.hotel.minapp.query.FormQuery;
import com.hotel.minapp.service.BaseService;
import com.hotel.minapp.service.WxFormService;
import com.hotel.model.WxFormId;
import com.hotel.model.mapper.WxFormIdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xu
 */
@Service
public class WxFormServiceImpl implements WxFormService {

    @Autowired
    private WxFormIdMapper formIdMapper;

    @Override
    public void create(FormQuery formQuery, String openId) {
        String[] formIds = formQuery.getFormIds();
        for (String formId : formIds) {
            Date now = new Date();
            WxFormId wxFormId = new WxFormId();
            wxFormId.setOpenId(openId);
            wxFormId.setFormId(formId);
            wxFormId.setCreatedAt(now);
            wxFormId.setExpiredAt(DateUtil.addDays(now, 7));
            formIdMapper.insertSelective(wxFormId);
        }
    }
}
