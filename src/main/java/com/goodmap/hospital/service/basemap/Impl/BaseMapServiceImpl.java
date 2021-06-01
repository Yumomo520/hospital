package com.goodmap.hospital.service.basemap.Impl;

import com.goodmap.hospital.mapper.BaseMapMapper;
import com.goodmap.hospital.pojo.BaseMap;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.basemap.BaseMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
@Service
public class BaseMapServiceImpl extends BaseServiceImpl<BaseMap> implements BaseMapService {

    @Autowired
    private BaseMapMapper baseMapMapper;

    @Override
    public List<BaseMap> selectAll() {
        return baseMapMapper.selectAll();
    }
}
