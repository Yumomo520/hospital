package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Equipment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
public interface EquipmentMapper extends Mapper<Equipment>, IdListMapper<Equipment,Long>, InsertMapper<Equipment> {

    @Insert({"<script> insert into equipment(id,equname,longitude,latitude,height,condition,unitname,buildname,floor)" +
            "values " +
            "<foreach collection='list' item='record' separator=','>" +
            "(#{record.id},#{record.equname},#{record.longitude},#{record.latitude},#{record.height},#{record.condition},#{record.unitname},#{record.buildname},#{record.floor})" +
            "</foreach>" +
            "</script>"})
    void insertListN(@Param("list") List<Equipment> list);
}
