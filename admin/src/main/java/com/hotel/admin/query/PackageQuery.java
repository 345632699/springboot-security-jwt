package com.hotel.admin.query;

import com.hotel.model.Package;
import com.hotel.model.PackageItem;

import java.util.List;

/**
 * @author xu
 */
public class PackageQuery extends Package {
  /**
   * 图片详情数组
   */
  private String[] imgs;

  private ConfigVo configVo;

  /**
   * 套餐子项目
   */
  private List<PackageItem> packageItems;

  public String[] getImgs() {
    return imgs;
  }

  public void setImgs(String[] imgs) {
    this.imgs = imgs;
  }

  public List<PackageItem> getPackageItems() {
    return packageItems;
  }

  public void setPackageItems(List<PackageItem> packageItems) {
    this.packageItems = packageItems;
  }

  public ConfigVo getConfigVo() {
    return configVo;
  }

  public void setConfigVo(ConfigVo configVo) {
    this.configVo = configVo;
  }
}