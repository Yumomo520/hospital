package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Gps;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface TrajectoryMapper extends Mapper<Gps>, IdsMapper<Gps>{
    @Select({"<script>" +
            "select * from gps where 1=1" +
            "<if test='number != null and number !=\"\"'>and #{number}=number</if>" +
            "<if test='startTime != null'>and #{startTime} &lt;=record_time</if>" +
            "<if test='endTime != null'>and #{endTime} &gt;=record_time</if>" +
            "<if test='page != null and rows != null'>limit #{page},#{rows}</if>" +
            "</script>"})
    List<Gps> selectByData(@Param("number") String number, @Param("startTime")Date startTime,@Param("endTime") Date endTime,@Param("page") Integer page,@Param(("rows")) Integer rows);
}
