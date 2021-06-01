package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Path;
import com.goodmap.hospital.pojo.Path;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/3/15
 * @Description
 */
public interface PathMapper extends Mapper<Path>, IdListMapper<Path,Long>, InsertMapper<Path> {

    @Insert({"<script> insert into path(unitname,buildname,floor,fnum,buildid,url)" +
            "values " +
            "<foreach collection='list' item='record' separator=','>" +
            "(#{record.unitname},#{record.buildname},#{record.floor},#{record.fnum},#{record.buildid},#{record.url})" +
            "</foreach>" +
            "</script>"})
    void insertListN(@Param("list") List<Path> list);

    @Insert({"<script> insert into path(unitname,buildname,buildid,floor,fnum,remark,url)" +
            "values " +
            "(#{unitname},#{buildname},#{buildid},#{floor},#{fnum},#{remark},#{url})" +
            "</script>"})
    void insertN(Path Path);
}
