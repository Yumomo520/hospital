package com.goodmap.hospital.service.panoramic.Impl;

import com.goodmap.hospital.config.dataSources.DS;
import com.goodmap.hospital.mapper.PanoramicMapper;
import com.goodmap.hospital.pojo.second.Panoramic;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.panoramic.PanoramicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
@Service
public class PanoramicServiceImpl extends BaseServiceImpl<Panoramic> implements PanoramicService {

    @Autowired
    private PanoramicMapper panoramicMapper;

    @Override
    @DS("indoormapsde")
    public List<Panoramic> selectAllByTable(String tableName) {
        return panoramicMapper.selectAllByTable(tableName);
    }

    @Override
    public void insert(Panoramic panoramic) {
        panoramicMapper.insertN(panoramic);
    }
}
