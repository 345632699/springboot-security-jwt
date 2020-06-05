package com.hotel.minapp.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CheckOutQuery {
  Integer roomOrderId;
  BigDecimal returnPrice;
  String password;
}
