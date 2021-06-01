package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.BaseMap;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
public interface BaseMapMapper extends Mapper<BaseMap>, IdListMapper<BaseMap,Long>, InsertMapper<BaseMap> {
}
