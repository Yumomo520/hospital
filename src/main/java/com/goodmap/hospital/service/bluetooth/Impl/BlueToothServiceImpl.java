package com.goodmap.hospital.service.bluetooth.Impl;

import com.goodmap.hospital.config.dataSources.DS;
import com.goodmap.hospital.mapper.BlueToothMapper;

import com.goodmap.hospital.pojo.second.BlueTooth;

import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.bluetooth.BlueToothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
@Service
public class BlueToothServiceImpl extends BaseServiceImpl<BlueTooth> implements BlueToothService  {

    @Autowired
    private BlueToothMapper blueToothMapper;

    @Override
    public List<BlueTooth> selectAll() {
        return blueToothMapper.selectAlls();
    }

    @Override
    @DS("indoormapsde")
    public List<BlueTooth> selectAllByTable(String tableName) {
        return blueToothMapper.selectAllByTable(tableName);
    }

    @Override
    public void insert(BlueTooth blueTooth) {
        blueToothMapper.insertN(blueTooth);
    }
}
