package com.goodmap.hospital.mapper;


import com.goodmap.hospital.pojo.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface HistoryMapper extends Mapper<History>, IdListMapper<History,Long>, InsertMapper<History>, MySqlMapper<History> {

    @Insert("if not exists (select 1 from history where id = #{id})\n" +
            "   insert into history(id,buildid,buildname,floor,longitude,latitude,poiname,fnum,addtime) " +
            "   values(#{id},#{buildid},#{buildname},#{floor},#{longitude},#{latitude},#{poiname},#{fnum},#{addtime})\n" +
            "else\n" +
            "   update history set " +
            "   buildid=#{buildid},buildname=#{buildname},floor=#{floor},longitude=#{longitude}," +
            "   latitude=#{latitude},poiname=#{poiname},fnum=#{fnum},addtime=#{addtime} " +
            "   where id = #{id}")
    void insertorUpdate(History history);

    @Select("select * from (select top 10 * from history order by id desc)aa order by id")
    List<History> selectByNewTen();

}
