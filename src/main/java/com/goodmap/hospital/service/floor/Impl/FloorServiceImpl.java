package com.goodmap.hospital.service.floor.Impl;

import com.goodmap.hospital.common.utils.RedisUtils;
import com.goodmap.hospital.mapper.FloorMapper;
import com.goodmap.hospital.pojo.Floor;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;

import com.goodmap.hospital.service.floor.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
@Service
public class FloorServiceImpl extends BaseServiceImpl<Floor> implements FloorService {

    @Autowired
    private FloorMapper floorMapper;



    @Override
    public List<Floor> selectByFloor(String floor) {
        return floorMapper.selectByFloor(floor);
    }

    @Override
    public List<Floor> selectAll() {
        return floorMapper.selectAll();
    }

    @Override
    public void ListAdd(List<Floor> t) {
        floorMapper.insertListN(t);
    }

    @Override
    public void insert(Floor floor) {
        floorMapper.insertN(floor);
    }

    @Override
    public List<Floor> selectUnitAndBuild(String unitname,String remark) {
        return floorMapper.selectUnitAndBuild(unitname,remark);
    }
}
