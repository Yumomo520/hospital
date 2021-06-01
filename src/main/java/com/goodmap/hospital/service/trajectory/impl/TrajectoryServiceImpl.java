package com.goodmap.hospital.service.trajectory.impl;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.mapper.TrajectoryMapper;
import com.goodmap.hospital.pojo.Gps;
import com.goodmap.hospital.service.trajectory.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/10/12 time
 * @Description
 **/
@Service
public class TrajectoryServiceImpl implements TrajectoryService {
    @Autowired
    private TrajectoryMapper trajectoryMapper;
    @Override
    public EntityResult<List<Gps>> selectByData(String number, Date startTime, Date endTime,Integer page,Integer rows) {
        List<Gps> gps = null;
        try {
            if (page <= 0) {
                page = 1;
            }
            if (rows <= 0) {
                page = 10;
            }
            int startPage = (page-1)*rows;
            gps = trajectoryMapper.selectByData(number, startTime, endTime,startPage,rows);
        }catch (NullPointerException e){
            e.printStackTrace();
            gps = trajectoryMapper.selectByData(number, startTime, endTime,null,null);
        }
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),gps);
    }
}
