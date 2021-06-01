package com.goodmap.hospital.service.trajectory;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.pojo.Gps;

import java.util.Date;
import java.util.List;

public interface TrajectoryService {
    EntityResult<List<Gps>> selectByData(String number, Date startTime, Date endTime,Integer page,Integer rows);
}
