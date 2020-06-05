package com.hotel.minapp.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xu
 */
@Data
public class PageQuery {
  @NotNull(message = "页码不能为空")
  Integer page;
  @NotNull(message = "每页条数不能为空")
  Integer limit;
  String keyword;
}
