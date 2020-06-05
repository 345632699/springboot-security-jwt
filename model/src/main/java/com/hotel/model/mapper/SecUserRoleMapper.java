package com.hotel.model.mapper;

import com.hotel.model.SecUserRole;
import com.hotel.model.SecUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SecUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    int countByExample(SecUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    int deleteByExample(SecUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    int insert(SecUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    int insertSelective(SecUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    List<SecUserRole> selectByExample(SecUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SecUserRole record, @Param("example") SecUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user_role
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SecUserRole record, @Param("example") SecUserRoleExample example);
}