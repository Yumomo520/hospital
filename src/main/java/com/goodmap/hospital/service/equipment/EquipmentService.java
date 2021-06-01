package com.goodmap.hospital.service.equipment;

import com.goodmap.hospital.pojo.Equipment;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
public interface EquipmentService extends BaseService<Equipment> {

    void ListAdd(List<Equipment> t);
}
