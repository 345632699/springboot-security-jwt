package com.hotel.model.mapper;

import com.hotel.model.RoomSpecs;
import com.hotel.model.RoomSpecsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomSpecsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int countByExample(RoomSpecsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int deleteByExample(RoomSpecsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int insert(RoomSpecs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int insertSelective(RoomSpecs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    List<RoomSpecs> selectByExample(RoomSpecsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    RoomSpecs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RoomSpecs record, @Param("example") RoomSpecsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RoomSpecs record, @Param("example") RoomSpecsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoomSpecs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_specs
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoomSpecs record);
}