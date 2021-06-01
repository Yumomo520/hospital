package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.second.BlueTooth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
public interface BlueToothMapper extends Mapper<BlueTooth>, IdListMapper<BlueTooth,Long > , InsertMapper<BlueTooth> {

    @Insert({"<script> insert into bluetooth(lyid,buildname,floor,x,y,state,minor,remark)" +
            "values " +
            "(#{lyid},#{buildname},#{floor},#{x},#{y},#{state},#{minor},#{remark})" +
            "</script>"})
    void insertN(BlueTooth blueTooth);

    @Select({"<script> select * from ${tableName} </script>"})
    List<BlueTooth> selectAllByTable(@Param("tableName") String tableName);
    @Select({"<script> select * from bluetooth </script>"})
    List<BlueTooth> selectAlls();
}
