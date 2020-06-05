package com.hotel.minapp.vo;

import com.hotel.model.WxConsumer;

public class ConsumerVo extends WxConsumer {
  boolean has_mobile;

  public boolean isHas_mobile() {
    return has_mobile;
  }

  public void setHas_mobile(boolean has_mobile) {
    this.has_mobile = has_mobile;
  }
}
