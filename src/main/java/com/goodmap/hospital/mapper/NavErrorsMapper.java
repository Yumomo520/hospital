package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.NavErrors;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/4/1
 * @Description
 */
public interface NavErrorsMapper extends Mapper<NavErrors> , InsertMapper<NavErrors>, IdListMapper<NavErrors,Long> {

    @Insert("<script>insert into nav_errors(start_point,end_point,time,remark)" +
            "values (#{start_point},#{end_point},#{time},#{remark})</script>")
    void insertN(NavErrors navErrors12);
}
