package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Logall;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

public interface LogMapper extends Mapper<Logall>, IdListMapper<Logall,Long>, InsertMapper<Logall> {
}
