package com.goodmap.hospital.service.hospital;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.pojo.Hospital;

import java.util.List;

public interface HospitalService {
    EntityResult<List<Hospital>> selectByName(String hospitalName);
}
