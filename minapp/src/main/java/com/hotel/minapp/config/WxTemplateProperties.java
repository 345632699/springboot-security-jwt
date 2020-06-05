package com.hotel.minapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx.template-msg")
public class WxTemplateProperties {
  private String appid;
  private String take_out_success;
  private String self_take_success;
  private String remind_take;
  private String order_delivery;
  private String evaluate_wait;
  private String order_success;
  private String ticketUrl;
  private String tipUrl;
}
