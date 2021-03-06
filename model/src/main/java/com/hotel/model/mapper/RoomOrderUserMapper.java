package com.hotel.model.mapper;

import com.hotel.model.RoomOrderUser;
import com.hotel.model.RoomOrderUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomOrderUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int countByExample(RoomOrderUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int deleteByExample(RoomOrderUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int insert(RoomOrderUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int insertSelective(RoomOrderUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    List<RoomOrderUser> selectByExample(RoomOrderUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    RoomOrderUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RoomOrderUser record, @Param("example") RoomOrderUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RoomOrderUser record, @Param("example") RoomOrderUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoomOrderUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_order_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoomOrderUser record);
}