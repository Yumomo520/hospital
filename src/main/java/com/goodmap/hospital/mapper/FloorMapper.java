package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Floor;
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
public interface FloorMapper extends Mapper<Floor>, IdListMapper<Floor,Long>, InsertMapper<Floor> {
    @Select({"<script>select f.id,unitname,floor,fnum,type,layerurl,remark from floor f " +
            "where 1=1" +
            "<if test='floor !=\"\" and floor != null'>and floor = #{floor}</if>" +
            "</script>"})
    List<Floor> selectByFloor(@Param("floor") String floor);

    @Insert({"<script> insert into floor(unitname,floor,fnum,type,layerurl,remark)" +
            "values " +
            "<foreach collection='list' item='record' separator=','>" +
            "(#{record.unitname},#{record.floor}," +
            "#{record.fnum},#{record.type},#{record.layerurl},#{record.remark})" +
            "</foreach>" +
            "</script>"})
    void insertListN(@Param("list") List<Floor> list);

    @Insert({"<script> insert into floor(unitname,floor,fnum,type,layerurl,remark)" +
            "values " +
            "(#{unitname},#{floor},#{fnum},#{type},#{layerurl},#{remark})" +
            "</script>"})
    void insertN(Floor floor);

    @Select("<script>select * from floor " +
            "<where>" +
            "<if test='unitname!=null and unitname != \"\" '>unitname like concat('%',#{unitname},'%')</if>" +
            //"<if test='buildname!=null and buildname != \"\" '> and buildname like concat('%',#{buildname},'%')</if>" +
            "<if test='remark!=null and remark != \"\" '> and remark like concat('%',#{remark},'%')</if>" +
            "</where>"+
            "</script>")
    List<Floor> selectUnitAndBuild(@Param("unitname") String unitname,
                                   @Param("remark") String remark);




}
