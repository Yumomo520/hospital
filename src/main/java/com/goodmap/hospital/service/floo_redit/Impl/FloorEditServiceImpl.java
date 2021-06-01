package com.goodmap.hospital.service.floo_redit.Impl;

import com.goodmap.hospital.mapper.FloorEditMapper;
import com.goodmap.hospital.pojo.FloorEdit;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.floo_redit.FloorEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
@Service
public class FloorEditServiceImpl extends BaseServiceImpl<FloorEdit> implements FloorEditService {

    @Autowired
    FloorEditMapper floorEditMapper;

    @Override
    public List<FloorEdit> selectByFloor(String floor) {
        return floorEditMapper.selectByFloor(floor);
    }

    @Override
    public List<FloorEdit> selectAll() {
        return floorEditMapper.selectAll();
    }

    @Override
    public void ListAdd(List<FloorEdit> t) {
        floorEditMapper.insertListN(t);
    }

    @Override
    public void insert(FloorEdit floor) {
        floorEditMapper.insertN(floor);
    }

    @Override
    public List<FloorEdit> selectUnitAndBuild(String unitname, String remark) {
        return floorEditMapper.selectUnitAndBuild(unitname,remark);
    }
}
