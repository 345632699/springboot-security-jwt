package com.hotel.admin.query;

import lombok.Data;

/**
 * @author xu
 */
@Data
public class PageQuery {
  /**
   * 页码
   */
  Integer page;
  /**
   * 每页条数
   */
  Integer limit;
  /**
   * 套餐名字 模糊搜索
   */
  String keyword;

  /**
   * 是否分页
   */
  Boolean pagingOrNot;
}
