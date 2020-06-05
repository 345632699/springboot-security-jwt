package com.hotel.minapp.query;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xu
 */
public class PackageOrderQuery {

  /**
   * 姓名
   */
  String name;
  /**
   * 手机号
   */
  String mobile;
  /**
   * 开始时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
  Date startTime;
  /**
   * 结束时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
  Date endTime;
  /**
   * 留言
   */
  String memo;

  /**
   * 套餐ID
   */
  Integer packageId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
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

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Integer getPackageId() {
    return packageId;
  }

  public void setPackageId(Integer packageId) {
    this.packageId = packageId;
  }
}
