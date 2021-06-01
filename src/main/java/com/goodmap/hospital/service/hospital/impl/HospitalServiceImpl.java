package com.goodmap.hospital.service.hospital.impl;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultBean;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.mapper.HospitalMapper;
import com.goodmap.hospital.pojo.Hospital;
import com.goodmap.hospital.service.hospital.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/9/17 time
 * @Description
 **/
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalMapper hospitalMapper;
    public EntityResult<List<Hospital>> selectByName(String hospitalNmae){
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),hospitalMapper.selectByName(hospitalNmae));
    }
}
