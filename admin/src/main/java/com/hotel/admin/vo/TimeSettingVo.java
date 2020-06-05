package com.hotel.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author xu
 */
@Data
public class TimeSettingVo {
  Integer id;
  /**
   * 套餐ID
   */
  Integer packageId;
  /**
   * 时间范围
   */
  Integer month;
  /**
   * 选择周几 可用
   */
  int[] weekdays;
  /**
   * 开始时间点
   */
  @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
  Date startTime;
  /**
   * 结束时间点
   */
  @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
  Date endTime;
  /**
   * 时间间隔
   */
  Integer timeSpace;
  /**
   * 一个时间段 限制人数
   */
  Integer limitNumber;
  /**
   * 占用时间段数
   */
  Integer occupyNumber;
  Integer storeId;
}
