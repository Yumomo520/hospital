package com.goodmap.hospital.service.floor;

import com.goodmap.hospital.pojo.Building;
import com.goodmap.hospital.pojo.Floor;
import com.goodmap.hospital.service.base.BaseService;
import com.goodmap.hospital.service.base.BaseStringService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
public interface FloorService extends BaseService<Floor> {
    List<Floor> selectByFloor(String floor);

    List<Floor> selectAll();

    void ListAdd(List<Floor> t);

    void insert(Floor floor);

    List<Floor> selectUnitAndBuild(String unitname,String remark);
}
