package com.goodmap.hospital.service.hospital;

import com.goodmap.hospital.pojo.Building;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/9/21 time
 * @Description
 **/
public interface BuildingService extends BaseService<Building> {
    List<Building> selectByhospital(Integer hospitalId);
}
