package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Hospital;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HospitalMapper {
    @Select({"<script>select * from hospital where 1=1" +
            "<if test='hospitalName!=\"\" and hospitalName!=null'>and hospital_name like concat('%',#{hospitalName},'%')</if>" +
            "</script>"})
    List<Hospital> selectByName(@Param("hospitalName") String hospitalName);
}
