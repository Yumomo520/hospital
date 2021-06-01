package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Floor;
import com.goodmap.hospital.pojo.FloorEdit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
public interface FloorEditMapper extends Mapper<FloorEdit>, IdListMapper<FloorEdit,Long>, InsertMapper<FloorEdit> {
    @Select({"<script>select f.id,unitname,floor,fnum,type,layerurl,remark from floor_edit f " +
            "where 1=1" +
            "<if test='floor !=\"\" and floor != null'>and floor = #{floor}</if>" +
            "</script>"})
    List<FloorEdit> selectByFloor(@Param("floor") String floor);

    @Insert({"<script> insert into floor_edit(unitname,floor,fnum,type,layerurl,remark)" +
            "values " +
            "<foreach collection='list' item='record' separator=','>" +
            "(#{record.unitname},#{record.floor}," +
            "#{record.fnum},#{record.type},#{record.layerurl},#{record.remark})" +
            "</foreach>" +
            "</script>"})
    void insertListN(@Param("list") List<FloorEdit> list);

    @Insert({"<script> insert into floor_edit(unitname,floor,fnum,type,layerurl,remark)" +
            "values " +
            "(#{unitname},#{floor},#{fnum},#{type},#{layerurl},#{remark})" +
            "</script>"})
    void insertN(FloorEdit floor);

    @Select("<script>select * from floor_edit " +
            "<where>" +
            "<if test='unitname!=null and unitname != \"\" '>unitname like concat('%',#{unitname},'%')</if>" +
            //"<if test='buildname!=null and buildname != \"\" '> and buildname like concat('%',#{buildname},'%')</if>" +
            "<if test='remark!=null and remark != \"\" '> and remark like concat('%',#{remark},'%')</if>" +
            "</where>"+
            "</script>")
    List<FloorEdit> selectUnitAndBuild(@Param("unitname") String unitname,
                                   @Param("remark") String remark);




}
