package com.hotel.admin.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CheckOutOrCancelQuery {
  Integer roomOrderId;
  BigDecimal returnPrice;
  String password;
  Integer type;
}
