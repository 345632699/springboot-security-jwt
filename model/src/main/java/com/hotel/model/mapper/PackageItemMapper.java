package com.hotel.model.mapper;

import com.hotel.model.PackageItem;
import com.hotel.model.PackageItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PackageItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int countByExample(PackageItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int deleteByExample(PackageItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int insert(PackageItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int insertSelective(PackageItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    List<PackageItem> selectByExample(PackageItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    PackageItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PackageItem record, @Param("example") PackageItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PackageItem record, @Param("example") PackageItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PackageItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_item
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PackageItem record);
}