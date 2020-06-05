package com.hotel.admin.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CancelQuery {
  Integer roomOrderId;
  BigDecimal returnPrice;
  String password;
}
