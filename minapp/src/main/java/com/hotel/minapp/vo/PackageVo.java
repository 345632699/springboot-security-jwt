package com.hotel.minapp.vo;


import com.hotel.model.Package;
import com.hotel.model.PackageItem;

import java.util.List;

/**
 * @author xu
 */
public class PackageVo extends Package {
  ConfigVo configVo;

  List<PackageItem> items;

  String[] imgs;

  public ConfigVo getConfigVo() {
    return configVo;
  }

  public void setConfigVo(ConfigVo configVo) {
    this.configVo = configVo;
  }

  public List<PackageItem> getItems() {
    return items;
  }

  public void setItems(List<PackageItem> items) {
    this.items = items;
  }

  public String[] getImgs() {
    return imgs;
  }

  public void setImgs(String[] imgs) {
    this.imgs = imgs;
  }
}
