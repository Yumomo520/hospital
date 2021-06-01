package com.goodmap.hospital.service.traffic.Impl;

import com.goodmap.hospital.mapper.TrafficMapper;
import com.goodmap.hospital.pojo.Traffic;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.traffic.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
@Service
public class TrafficServiceImpl extends BaseServiceImpl<Traffic> implements TrafficService {

    @Autowired
    private TrafficMapper trafficMapper;

    @Override
    public List<Traffic> selectAll() {
        return trafficMapper.selectAll();
    }
}
