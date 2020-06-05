package com.hotel.minapp.vo;

public class ConfigVo {
  /**
   * 是否需要支付
   */
  Boolean needPay;
  /**
   * 是否需要选择日期
   */
  Boolean needTimePicker;
  /**
   * 是否需要选择具体时间
   */
  Boolean needTimeDetail;

  public Boolean getNeedPay() {
    return needPay;
  }

  public void setNeedPay(Boolean needPay) {
    this.needPay = needPay;
  }

  public Boolean getNeedTimePicker() {
    return needTimePicker;
  }

  public void setNeedTimePicker(Boolean needTimePicker) {
    this.needTimePicker = needTimePicker;
  }

  public Boolean getNeedTimeDetail() {
    return needTimeDetail;
  }

  public void setNeedTimeDetail(Boolean needTimeDetail) {
    this.needTimeDetail = needTimeDetail;
  }
}
