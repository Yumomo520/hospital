package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.ErroreM;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

public interface MessageMapper extends Mapper<ErroreM>, IdListMapper<ErroreM,Long>, InsertMapper<ErroreM> {
    @Insert("insert into emessage (message,poiname,textbox) VALUES(#{message},#{poiname},#{textbox}) ")
    int add(@Param("message") String message,
            @Param("poiname") String poiname,
            @Param("textbox") String textbox);
}
