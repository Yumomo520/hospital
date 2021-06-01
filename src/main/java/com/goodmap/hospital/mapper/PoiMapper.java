package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Floor;
import com.goodmap.hospital.pojo.Poi;
import com.goodmap.hospital.pojo.second.Panoramic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

public interface PoiMapper extends Mapper<Poi>, IdListMapper<Poi,Long>, InsertMapper<Poi> {


    @Select("<script>select unitname,poiname,floor,longitude,latitude,remark,fnum,buildname,picurl,videourl,buildid as buildName from poi where 1 = 1 " +
            "<if test='buildName != null and buildName != \"\"'>and buildname = #{buildName}</if>" +
            "</script>")
    List<Poi> selectByBuild(@Param("buildName") String buildName);

    @Select("<script>select p.*,building_name as buildname from poi p left join building b on p.buildid = b.id where 1=1 " +
            "<if test='name != null and name != \"\"'>and poiname like concat('%',#{name},'%')</if>" +
            "</script>")
    List<Poi> selectByName(@Param("name") String name);


    @Select("select distinct(fnum) from poi where buildname = #{bname}")
    List<Poi> selectByBname(@Param("bname") String bname);

    @Insert({"<script> insert into poi(unitname,buildname,floor,poiname,longitude,latitude,fnum,buildid,picurl,videourl)" +
            "values " +
            "<foreach collection='list' item='record' separator=','>" +
            "(#{record.unitname},#{record.buildname},#{record.floor},#{record.poiname},#{record.longitude},#{record.latitude},#{record.fnum},#{record.buildid},#{record.picurl},#{record.videourl})" +
            "</foreach>" +
            "</script>"})
    void insertListN(@Param("list") List<Poi> list);

    @Insert({"<script> insert into poi(unitname,buildname,buildid,floor,fnum,poiname,longitude,latitude,remark,picurl,videourl)" +
            "values " +
            "(#{unitname},#{buildname},#{buildid},#{floor},#{fnum},#{poiname},#{longitude},#{latitude},#{remark},#{picurl},#{videourl})" +
            "</script>"})
    void insertN(Poi poi);

    @Select({"<script> select * from ${tableName} </script>"})
    List<Poi> selectAllByTable(@Param("tableName") String tableName);

}
