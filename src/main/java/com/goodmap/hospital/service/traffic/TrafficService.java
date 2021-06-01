package com.goodmap.hospital.service.traffic;

import com.goodmap.hospital.pojo.Traffic;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
public interface TrafficService extends BaseService<Traffic> {

   List<Traffic> selectAll();
}
