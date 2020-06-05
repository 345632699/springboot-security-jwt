package com.hotel.minapp.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.hotel.minapp.config.WxMaConfiguration;
import com.hotel.minapp.config.WxTemplateProperties;
import com.hotel.minapp.service.WxTempMsgService;
import com.hotel.model.PackageOrder;
import com.hotel.model.WxFormId;
import com.hotel.model.WxFormIdExample;
import com.hotel.model.mapper.PackageMapper;
import com.hotel.model.mapper.PackageOrderMapper;
import com.hotel.model.mapper.WxFormIdMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 微信末班消息服务
 *
 * @author xu
 */
@Service
public class WxTempMsgServiceImpl implements WxTempMsgService {

  @Autowired
  private WxTemplateProperties wxTemplateProperties;

  @Autowired
  private PackageMapper packageMapper;

  @Autowired
  private PackageOrderMapper orderMapper;

  @Autowired
  private WxFormIdMapper wxFormIdMapper;

  @Override
  public void sendOrderSuccessMessage(Integer orderId) throws WxErrorException {
    final WxMaService wxService = WxMaConfiguration.getMaService(wxTemplateProperties.getAppid());
    PackageOrder packageOrder = orderMapper.selectByPrimaryKey(orderId);

    WxMaTemplateMessage wxMaTemplateMessage = new WxMaTemplateMessage();

    List<WxFormId> wxFormIds = getWxFormIds(packageOrder);
    WxFormId wxFormId = null;
    if (wxFormIds.size() > 0) {
      wxFormId = wxFormIds.get(0);
      wxMaTemplateMessage.setFormId(wxFormId.getFormId());
    } else {
      System.out.println("发送模板消息出错，不存在可用的formId");
     return;
    }

    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String format = f.format(packageOrder.getStartTime());

    // 发送模板消息通知
    List<WxMaTemplateData> wxMaTemplateDatas = new ArrayList<>();
    // 预约编号
    WxMaTemplateData keyword1 = new WxMaTemplateData("keyword1", packageOrder.getOutTradeNo());
    // 预约产品
    WxMaTemplateData keyword2 = new WxMaTemplateData("keyword2", packageOrder.getPackageName());
    // 开始时间
    WxMaTemplateData keyword3 = new WxMaTemplateData("keyword3", format);
    // 预约人
    WxMaTemplateData keyword4 = new WxMaTemplateData("keyword4", packageOrder.getUserName());
    // 联系方式
    String mobile = packageOrder.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    WxMaTemplateData keyword5 = new WxMaTemplateData("keyword5", mobile);

    wxMaTemplateDatas.add(keyword1);
    wxMaTemplateDatas.add(keyword2);
    wxMaTemplateDatas.add(keyword3);
    wxMaTemplateDatas.add(keyword4);
    wxMaTemplateDatas.add(keyword5);

    wxMaTemplateMessage.setPage("pages/index/index");
    wxMaTemplateMessage.setTemplateId(wxTemplateProperties.getOrder_success());
    wxMaTemplateMessage.setToUser(packageOrder.getOpenId());
//    wxMaTemplateMessage.setEmphasisKeyword("keyword1.DATA");
    wxMaTemplateMessage.setData(wxMaTemplateDatas);
    WxMaMsgService msgService = wxService.getMsgService();
    msgService.sendTemplateMsg(wxMaTemplateMessage);

    // 更新formId状态
    updateWxFormIdRecord(wxFormId);

  }

  private void updateWxFormIdRecord(WxFormId wxFormId) {
    wxFormId.setIsUsed(1);
    wxFormIdMapper.updateByPrimaryKeySelective(wxFormId);
  }

  private List<WxFormId> getWxFormIds(PackageOrder packageOrder) {
    WxFormIdExample wxFormIdExample = new WxFormIdExample();
    WxFormIdExample.Criteria criteria = wxFormIdExample.createCriteria();
    criteria.andOpenIdEqualTo(packageOrder.getOpenId());
    criteria.andExpiredAtGreaterThan(new Date());
    criteria.andIsUsedEqualTo(0);
    return wxFormIdMapper.selectByExample(wxFormIdExample);
  }
}
