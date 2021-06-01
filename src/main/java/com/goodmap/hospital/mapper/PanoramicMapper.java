package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.second.Panoramic;
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
public interface PanoramicMapper extends Mapper<Panoramic>, IdListMapper<Panoramic,Long > , InsertMapper<Panoramic> {

    @Insert({"<script> insert into bluetooth(name,url,remark)" +
            "values " +
            "(#{name},#{url},#{remark})" +
            "</script>"})
    void insertN(Panoramic blueTooth);

    @Select({"<script> select * from ${tableName} </script>"})
    List<Panoramic> selectAllByTable(@Param("tableName") String tableName);
}
