package com.hotel.minapp.service.impl;

import com.hotel.common.PageModel;
import com.hotel.common.Status;
import com.hotel.common.order.OrderStatus;
import com.hotel.common.utils.BeanCopierUtil;
import com.hotel.common.utils.DateUtil;
import com.hotel.common.utils.GsonUtil;
import com.hotel.minapp.exception.CustomException;
import com.hotel.minapp.query.PackageOrderQuery;
import com.hotel.minapp.query.PageQuery;
import com.hotel.minapp.service.BaseService;
import com.hotel.minapp.service.PackageService;
import com.hotel.minapp.vo.ConfigVo;
import com.hotel.minapp.vo.DayVo;
import com.hotel.minapp.vo.PackageVo;
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

/**
 * @author xu
 */
@Service
public class PackageServiceImpl implements PackageService {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private PackageOrderMapper orderMapper;

  @Autowired
  private PackageTimeSettingMapper timeSettingMapper;

  @Autowired
  private PackageMapper packageMapper;

  @Autowired
  private PackageImageMapper imageMapper;

  @Autowired
  private PackageItemMapper itemMapper;

  @Override
  public List<String> getEnabledTimeList(Date checkStartTime) throws ParseException {
    // 时间格式化
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    String formatDay = f.format(checkStartTime);
    /**
     * 计算当前选择天的 可用时间段
     */
    Map<Long, Integer> map = calculateEnabledTime(checkStartTime, formatDay);
    List<String> list = new ArrayList<>();
    for (Long aLong : map.keySet()) {
      if (map.get(aLong) > 0 ) {
        list.add(String.valueOf(aLong));
      }
    }
    return list;
  }

  @Override
  public List<DayVo> getDayList(Integer packageId) {
    // 时间设置
    List<PackageTimeSetting> packageTimeSettings = timeSettingMapper.selectByExample(new PackageTimeSettingExample());
    PackageTimeSetting timeSetting;
    if (packageTimeSettings.size() > 0) {
      timeSetting = packageTimeSettings.get(0);
    } else {
      throw new CustomException(Status.NO_TIME_SETTING);
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    Date startDate = calendar.getTime();
    Date lastDate = DateUtil.addMonth(startDate, timeSetting.getMonth());

    String[] days = timeSetting.getWeekdays().split(",");

    Package aPackage = packageMapper.selectByPrimaryKey(packageId);
    ConfigVo configVo = GsonUtil.GsonToBean(aPackage.getConfig(), ConfigVo.class);

    List<DayVo> dayList = new ArrayList<>();
    if (configVo.getNeedTimeDetail()) {
      for (; startDate.compareTo(lastDate) < 0; ) {
        Map<String, Object> dateToWeek = DateUtil.dateToWeek(startDate);
        if (Arrays.asList(days).contains(dateToWeek.get("index").toString())) {
          DayVo dayVo = new DayVo();
          dayVo.setDay(DateUtil.dateToString(startDate, DateUtil.YYYY_MM_DD));
          dayVo.setWeekName(dateToWeek.get("name").toString());
          dayList.add(dayVo);
        }
        startDate = DateUtil.addDays(startDate, 1);
      }
    } else {
      Map<Long, Integer> dayListMap = calculateEnableDay(startDate, lastDate, aPackage, timeSetting);
      for (Long date : dayListMap.keySet()) {
        if (dayListMap.get(date) > 0) {
          DayVo dayVo = new DayVo();
          Map<String, Object> dateToWeek = DateUtil.dateToWeek(new Date(date * 1000));
          dayVo.setDay(DateUtil.dateToString(new Date(date * 1000), DateUtil.YYYY_MM_DD));
          dayVo.setWeekName(dateToWeek.get("name").toString());
          dayList.add(dayVo);
        }
      }
    }

    return dayList;
  }

  private Map<Long, Integer> calculateEnableDay(Date startDate, Date lastDate, Package aPackage, PackageTimeSetting timeSetting) {

    PackageOrderExample packageOrderExample = new PackageOrderExample();
    PackageOrderExample.Criteria criteria = packageOrderExample.createCriteria();
    criteria.andPackageIdEqualTo(aPackage.getId());
    criteria.andStartTimeGreaterThanOrEqualTo(startDate);
    criteria.andStartTimeLessThanOrEqualTo(lastDate);
    criteria.andStatusEqualTo(OrderStatus.UNDONE.getCode());
    List<PackageOrder> packageOrders = orderMapper.selectByExample(packageOrderExample);

    Map<Long, Integer> map = new HashMap<>();
    String[] days = timeSetting.getWeekdays().split(",");

    for (; startDate.compareTo(lastDate) < 0; ) {

      Map<String, Object> dateToWeek = DateUtil.dateToWeek(startDate);
      if (Arrays.asList(days).contains(dateToWeek.get("index").toString())) {
        map.put(DateUtil.getSecondTimestamp(startDate), timeSetting.getLimitNumber());
      }
      startDate = DateUtil.addDays(startDate, 1);
    }

    if (packageOrders.size() > 0) {
      packageOrders.forEach(item -> {
        Long startTime = DateUtil.getSecondTimestamp(item.getStartTime());
        if (map.containsKey(startTime)) {
          Integer value = map.get(startTime) - 1;
          map.put(DateUtil.getSecondTimestamp(item.getStartTime()), value);
        }
      });
    }

    return map;
  }

  private Map<Long, Integer> calculateEnabledTime(Date checkStartTime, String formatDay) throws ParseException {
    // 查询某一天现有预约订单
    PackageOrderExample packageOrderExample = new PackageOrderExample();
    PackageOrderExample.Criteria criteria = packageOrderExample.createCriteria();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
    Date checkEndDate = DateUtil.addDays(checkStartTime, 1);
    criteria.andStartTimeBetween(checkStartTime, checkEndDate);
    criteria.andStatusEqualTo(OrderStatus.UNDONE.getCode());
    List<PackageOrder> packageOrders = orderMapper.selectByExample(packageOrderExample);

    // 时间设置
    List<PackageTimeSetting> packageTimeSettings = timeSettingMapper.selectByExample(new PackageTimeSettingExample());
    PackageTimeSetting timeSetting = packageTimeSettings.get(0);

    // date起始时间
    long temp = DateUtil.getSecondTimestamp(dateFormat.parse("1970-01-01"));
    // 时间限制的开始时间
    long startStamp = DateUtil.getSecondTimestamp(timeSetting.getStartTime()) - temp;
    // 时间限制的结束时间
    long endStamp = DateUtil.getSecondTimestamp(timeSetting.getEndTime()) - temp;
    // 开始当天的时间戳
    long startDateTimestamp = DateUtil.getSecondTimestamp(checkStartTime);
    LinkedHashMap<Long, Integer> map = new LinkedHashMap<>();
    for (; startStamp < endStamp; ) {

      // redis存储的日期key值
      long index = startDateTimestamp + startStamp;
      String key = formatDay + ":" + index;

      /**
       * 时间间隔秒数 分钟 * 60 * 占用时间段数
       */
      long addSeconds = timeSetting.getOccupyNumber() * timeSetting.getTimeSpace() * 60;


      if (timeSetting.getLimitNumber() < 0) {
        map.put(index, 9999);
      } else {
        map.put(index, timeSetting.getLimitNumber());

        // 判断是否已经被预约
        packageOrders.forEach(item -> {
          Long orderStartSecondTimestamp = DateUtil.getSecondTimestamp(item.getStartTime());
          Long orderEndSecondTimestamp = orderStartSecondTimestamp + addSeconds;
          if (index >= orderStartSecondTimestamp && index < orderEndSecondTimestamp) {
            map.put(index, map.get(index) - 1);
          }
          if ((index + addSeconds) > orderStartSecondTimestamp && index < orderStartSecondTimestamp) {
            map.put(index, map.get(index) - 1);
          }
        });
      }

      // 根据截止时间 去除不足时间 间隔的时间段
      if ((index + addSeconds) > (startDateTimestamp + endStamp)) {
        map.put(index, 0);
      }

      // 过滤掉 比当前时间早的时间戳
      if (index < DateUtil.getSecondTimestamp(new Date())) {
        map.put(index, 0);
      }

      startStamp += timeSetting.getTimeSpace() * 60;
    }
    stringRedisTemplate.opsForValue().set(formatDay, GsonUtil.GsonString(map));
    return map;
  }

  @Override
  public PageModel<Package> getList(PageQuery pageQuery) {
    PageModel<Package> packagePageModel = new PageModel<>();
    packagePageModel.setLimit(pageQuery.getLimit());
    packagePageModel.setCurrentPage(pageQuery.getPage());

    PackageExample packageExample = new PackageExample();

    PackageExample.Criteria criteria = packageExample.createCriteria();
    // 上架状态 0 未上架 1 已上架
    criteria.andPublishStatusEqualTo(1);
    if (pageQuery.getKeyword() != null) {
      criteria.andNameLike("%" + pageQuery.getKeyword() + "%");
    }

    int count = packageMapper.countByExample(packageExample);

    packageExample.setOffset((pageQuery.getPage() - 1) * pageQuery.getLimit());
    packageExample.setLimit(pageQuery.getLimit());

    List<Package> packages = packageMapper.selectByExampleWithBLOBs(packageExample);
    packagePageModel.setData(packages);
    packagePageModel.setTotalCount(count);
    return packagePageModel;
  }

  @Override
  public PackageVo getDetail(Integer id) {
    Package aPackage = packageMapper.selectByPrimaryKey(id);
    String config = aPackage.getConfig();
    ConfigVo configVo = GsonUtil.GsonToBean(config, ConfigVo.class);
    PackageVo packageVo = new PackageVo();
    BeanCopierUtil.copy(aPackage, packageVo);
    packageVo.setConfigVo(configVo);

    List<PackageImage> imageList = getPackageImageExample(id);
    if (imageList.size() > 0) {
      String[] imgs = imageList.stream().map(PackageImage::getImageUrl).toArray(String[]::new);
      packageVo.setImgs(imgs);
    }

    List<PackageItem> packageItems = getPackageItemByPackageId(id);
    packageVo.setItems(packageItems);
    return packageVo;
  }

  private List<PackageItem> getPackageItemByPackageId(Integer id) {
    PackageItemExample packageItemExample = new PackageItemExample();
    PackageItemExample.Criteria criteria1 = packageItemExample.createCriteria();
    criteria1.andPackageIdEqualTo(id);
    return itemMapper.selectByExample(packageItemExample);
  }

  private List<PackageImage> getPackageImageExample(Integer id) {
    PackageImageExample packageImageExample = new PackageImageExample();
    PackageImageExample.Criteria criteria = packageImageExample.createCriteria();
    criteria.andPackageIdEqualTo(id);
    return imageMapper.selectByExample(packageImageExample);
  }

  @Override
  public Integer createPackageOrder(PackageOrderQuery orderQuery, String openId) {
    Integer packageId = orderQuery.getPackageId();
    Package aPackage = packageMapper.selectByPrimaryKey(packageId);
    String config = aPackage.getConfig();
    ConfigVo configVo = GsonUtil.GsonToBean(config, ConfigVo.class);

    PackageOrder packageOrder = new PackageOrder();
    if (configVo.getNeedPay()) {
      packageOrder.setPrice(aPackage.getPrice());
      packageOrder.setRealPrice(aPackage.getPrice());
      packageOrder.setStatus(OrderStatus.WAIT_TO_PAY.getCode());
    } else {
      packageOrder.setStatus(OrderStatus.UNDONE.getCode());
    }
    packageOrder.setCreatedAt(new Date());
    packageOrder.setUserName(orderQuery.getName());
    packageOrder.setMemo(orderQuery.getMemo());
    packageOrder.setOutTradeNo("PO_" + System.currentTimeMillis());
    packageOrder.setMobile(orderQuery.getMobile());
    packageOrder.setOpenId(openId);
    packageOrder.setStartTime(orderQuery.getStartTime());
    packageOrder.setEndTime(orderQuery.getEndTime());

    packageOrder.setPackageId(orderQuery.getPackageId());
    packageOrder.setPackageName(aPackage.getName());
    orderMapper.insertSelective(packageOrder);
    return packageOrder.getId();
  }
}
