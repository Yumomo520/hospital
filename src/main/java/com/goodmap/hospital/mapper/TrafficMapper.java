package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Traffic;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
public interface TrafficMapper extends Mapper<Traffic>, IdListMapper<Traffic,Long>, InsertMapper<Traffic> {
}
