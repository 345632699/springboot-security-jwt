package com.hotel.admin.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

/**
 * @author xu
 */
public class TimeQuery {
  @JsonFormat(pattern="yy-MM-dd",timezone="GMT+8")
  Date checkStartTime;

  public Date getCheckStartTime() {
    return checkStartTime;
  }

  public void setCheckStartTime(Date checkStartTime) {
    this.checkStartTime = checkStartTime;
  }
}
