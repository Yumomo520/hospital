package com.goodmap.hospital.service.equipment.Impl;

import com.goodmap.hospital.mapper.EquipmentMapper;
import com.goodmap.hospital.pojo.Equipment;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
@Service
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment> implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public void ListAdd(List<Equipment> t) {
        equipmentMapper.insertListN(t);
    }
}
