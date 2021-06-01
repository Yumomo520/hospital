package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Building;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

public interface BuildingMapper extends Mapper<Building>, IdListMapper<Building,Long>, InsertMapper<Building> {
    @Select({"<script>select b.id,building_name,hid from building b " +
            "where 1=1" +
            "<if test='hospitalId !=\"\" and hospitalId != null'>and hid = #{hospitalId}</if>" +
            "</script>"})
    List<Building> selectByhospital(@Param("hospitalId") Integer hospitalId);
}
