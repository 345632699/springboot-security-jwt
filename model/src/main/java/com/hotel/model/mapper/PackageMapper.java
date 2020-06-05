package com.hotel.model.mapper;

import com.hotel.model.Package;
import com.hotel.model.PackageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PackageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int countByExample(PackageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int deleteByExample(PackageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int insert(Package record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int insertSelective(Package record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    List<Package> selectByExampleWithBLOBs(PackageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    List<Package> selectByExample(PackageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    Package selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Package record, @Param("example") PackageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Package record, @Param("example") PackageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Package record, @Param("example") PackageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Package record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Package record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Package record);
}