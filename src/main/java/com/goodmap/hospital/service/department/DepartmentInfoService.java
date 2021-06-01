package com.goodmap.hospital.service.department;

import com.goodmap.hospital.pojo.DepartmentInfo;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author 刘智强
 * @date 2021/5/12
 * @Description
 */
public interface DepartmentInfoService extends BaseService<DepartmentInfo> {

    List<DepartmentInfo> selcetAll();

    Map<String,List<DepartmentInfo>> getDepartmentInfoPinYinMap();
}
