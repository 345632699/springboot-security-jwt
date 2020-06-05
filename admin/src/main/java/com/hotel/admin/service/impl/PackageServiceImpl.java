package com.hotel.admin.service.impl;

import com.hotel.admin.query.ConfigVo;
import com.hotel.admin.query.PackageQuery;
import com.hotel.admin.query.PageQuery;
import com.hotel.admin.query.PublishQuery;
import com.hotel.admin.service.PackageService;
import com.hotel.common.PageModel;
import com.hotel.common.utils.BeanCopierUtil;
import com.hotel.common.utils.DateUtil;
import com.hotel.common.utils.GsonUtil;
import com.hotel.model.*;
import com.hotel.model.Package;
import com.hotel.model.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author xu
 */
@Service
public class PackageServiceImpl implements PackageService {
  @Autowired
  private PackageMapper packageMapper;

  @Autowired
  private PackageImageMapper imageMapper;

  @Autowired
  private PackageItemMapper packageItemMapper;

  @Autowired
  private PackageTimeSettingMapper timeSettingMapper;

  @Autowired
  private PackageOrderMapper orderMapper;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public PageModel<Package> getList(PageQuery pageQuery) {
    PackageExample packageExample = new PackageExample();
    PackageExample.Criteria criteria = packageExample.createCriteria();
    if (pageQuery.getKeyword() != null) {
      criteria.andNameLike("%" + pageQuery.getKeyword() + "%");
    }
    int totalCount = packageMapper.countByExample(packageExample);
    if (pageQuery.getPagingOrNot()) {
      packageExample.setLimit(pageQuery.getLimit());
      packageExample.setOffset((pageQuery.getPage() - 1) * pageQuery.getLimit());
    }
    List<Package> packages = packageMapper.selectByExample(packageExample);
    PageModel<Package> packagePageModel = new PageModel<>();
    packagePageModel.setTotalCount(totalCount);
    packagePageModel.setData(packages);
    packagePageModel.setCurrentPage(pageQuery.getPage());
    packagePageModel.setLimit(pageQuery.getLimit());
    return packagePageModel;
  }


  @Override
  public Integer create(PackageQuery packageQuery) {
    ConfigVo configVo = packageQuery.getConfigVo();
    String configJson = GsonUtil.GsonString(configVo);
    packageQuery.setConfig(configJson);
    packageQuery.setCreatedAt(new Date());
    packageQuery.setUpdatedAt(new Date());
    packageMapper.insertSelective(packageQuery);
    String[] imgs = packageQuery.getImgs();

    if (imgs.length > 0) {
      // 相应的图片
      for (String img : imgs) {
        PackageImage packageImage = new PackageImage();
        packageImage.setImageUrl(img);
        packageImage.setPackageId(packageQuery.getId());
        imageMapper.insertSelective(packageImage);
      }
    }

    // 插入相应的套餐子项
    if (packageQuery.getPackageItems().size() > 0) {
      for (PackageItem item : packageQuery.getPackageItems()) {
        item.setPackageId(packageQuery.getId());
        packageItemMapper.insertSelective(item);
      }
    }
    return packageQuery.getId();
  }

  @Override
  public void update(PackageQuery packageQuery) {
    ConfigVo configVo = packageQuery.getConfigVo();
    String configJson = GsonUtil.GsonString(configVo);
    packageQuery.setConfig(configJson);
    packageMapper.updateByPrimaryKeySelective(packageQuery);
    // 更新套餐图片
    updatePackageImgs(packageQuery);
    // 更新子项目
    updatePackageItems(packageQuery);
  }

  private void updatePackageItems(PackageQuery packageQuery) {
    if (packageQuery.getPackageItems() == null) {
      return;
    }
    List<PackageItem> packageItems = packageQuery.getPackageItems();
    PackageItemExample packageItemExample = new PackageItemExample();
    PackageItemExample.Criteria criteria = packageItemExample.createCriteria();
    criteria.andPackageIdEqualTo(packageQuery.getId());
    packageItemMapper.deleteByPrimaryKey(packageQuery.getId());
    if (packageItems.size() > 0) {
      packageItems.forEach(item -> {
        packageItemMapper.insertSelective(item);
      });
    }
  }

  private void updatePackageImgs(PackageQuery packageQuery) {
    String[] imgs = packageQuery.getImgs();
    PackageImageExample packageImageExample = new PackageImageExample();
    PackageImageExample.Criteria criteria = packageImageExample.createCriteria();
    criteria.andPackageIdEqualTo(packageQuery.getId());
    imageMapper.deleteByExample(packageImageExample);
    if (imgs.length > 0) {
      for (String img : imgs) {
        PackageImage packageImage = new PackageImage();
        packageImage.setPackageId(packageQuery.getId());
        packageImage.setImageUrl(img);
        imageMapper.insertSelective(packageImage);
      }
    }
  }

  @Override
  public PackageQuery getDetail(Integer id) {
    Package aPackage = packageMapper.selectByPrimaryKey(id);
    PackageQuery packageVo = new PackageQuery();
    BeanCopierUtil.copy(aPackage, packageVo);
    packageVo.setConfigVo(GsonUtil.GsonToBean(aPackage.getConfig(), ConfigVo.class));
    PackageItemExample packageItemExample = new PackageItemExample();
    PackageItemExample.Criteria criteria = packageItemExample.createCriteria();
    criteria.andPackageIdEqualTo(id);
    List<PackageItem> packageItems = packageItemMapper.selectByExample(packageItemExample);

    PackageImageExample packageImageExample = new PackageImageExample();
    PackageImageExample.Criteria imageExampleCriteria = packageImageExample.createCriteria();
    imageExampleCriteria.andPackageIdEqualTo(id);
    List<PackageImage> packageImages = imageMapper.selectByExample(packageImageExample);
    String[] strings = packageImages.stream().map(PackageImage::getImageUrl).toArray(String[]::new);

    packageVo.setImgs(strings);
    packageVo.setPackageItems(packageItems);
    return packageVo;
  }

  @Override
  public void publishOrDown(PublishQuery publishQuery) {
    Package aPackage = packageMapper.selectByPrimaryKey(publishQuery.getId());
    aPackage.setPublishStatus(publishQuery.getOperateType());
    packageMapper.updateByPrimaryKeyWithBLOBs(aPackage);
  }

  @Override
  public void delete(Integer id) {
    packageMapper.deleteByPrimaryKey(id);
  }
}
