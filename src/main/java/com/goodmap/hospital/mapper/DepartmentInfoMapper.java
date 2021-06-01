package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.DepartmentInfo;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * @author 刘智强
 * @date 2021/5/12
 * @Description
 */
public interface DepartmentInfoMapper extends Mapper<DepartmentInfo>, IdListMapper<DepartmentInfo,Long>, InsertMapper<DepartmentInfo> {
}
