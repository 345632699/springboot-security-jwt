package com.hotel.admin.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PackageTimeSettingQuery {

  Integer id;
  /**
   * 日期选择范围 单位月
   */
  private Integer month;

  /**
   * 可预约日期
   */
  private Integer[] weekdays;

  /**
   * 开始时间
   */
  @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
  private Date startTime;

  /**
   * 结束时间
   */
  @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
  private Date endTime;

  /**
   * 时间间隔 单位分钟
   */
  private Integer timeSpace;

  /**
   * limit 预约人数限制
   */
  private Integer limitNumber;

  /**
   * 占用时间段 单位 时间间隔
   */
  private Integer occupyNumber;

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer[] getWeekdays() {
    return weekdays;
  }

  public void setWeekdays(Integer[] weekdays) {
    this.weekdays = weekdays;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Integer getTimeSpace() {
    return timeSpace;
  }

  public void setTimeSpace(Integer timeSpace) {
    this.timeSpace = timeSpace;
  }

  public Integer getLimitNumber() {
    return limitNumber;
  }

  public void setLimitNumber(Integer limitNumber) {
    this.limitNumber = limitNumber;
  }

  public Integer getOccupyNumber() {
    return occupyNumber;
  }

  public void setOccupyNumber(Integer occupyNumber) {
    this.occupyNumber = occupyNumber;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
