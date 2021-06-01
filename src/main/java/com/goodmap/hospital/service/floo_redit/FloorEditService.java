package com.goodmap.hospital.service.floo_redit;

import com.goodmap.hospital.pojo.FloorEdit;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
public interface FloorEditService extends BaseService<FloorEdit> {
    List<FloorEdit> selectByFloor(String floor);

    List<FloorEdit> selectAll();

    void ListAdd(List<FloorEdit> t);

    void insert(FloorEdit floor);

    List<FloorEdit> selectUnitAndBuild(String unitname, String remark);
}
