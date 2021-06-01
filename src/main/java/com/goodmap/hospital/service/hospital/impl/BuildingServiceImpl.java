package com.goodmap.hospital.service.hospital.impl;

import com.goodmap.hospital.mapper.BuildingMapper;
import com.goodmap.hospital.pojo.Building;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.hospital.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李美泉
 * @Data 2020/9/21 time
 * @Description
 **/
@Service
public class BuildingServiceImpl extends BaseServiceImpl<Building> implements BuildingService {
    @Autowired
    BuildingMapper buildingMapper;
    @Override
    public List<Building> selectByhospital(Integer hospitalId) {
        return buildingMapper.selectByhospital(hospitalId);
    }

}
