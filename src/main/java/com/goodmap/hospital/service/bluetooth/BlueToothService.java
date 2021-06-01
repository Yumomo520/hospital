package com.goodmap.hospital.service.bluetooth;

import com.goodmap.hospital.pojo.second.BlueTooth;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
public interface BlueToothService extends BaseService<BlueTooth> {

    List<BlueTooth> selectAll();

    List<BlueTooth> selectAllByTable(String tableName);

    void insert(BlueTooth blueTooth);
}
