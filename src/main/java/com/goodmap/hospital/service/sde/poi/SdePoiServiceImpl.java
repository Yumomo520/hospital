package com.goodmap.hospital.service.sde.poi;

import com.goodmap.hospital.config.dataSources.DS;
import com.goodmap.hospital.mapper.SdePoiMapper;
import com.goodmap.hospital.pojo.second.Poi;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SdePoiServiceImpl extends BaseServiceImpl<Poi> implements SdePoiService {
    @Autowired
    SdePoiMapper sdePoiMapper;

    @Override
    @DS("indoormapsde")
    public List<Poi> selectAllsde() {
        return sdePoiMapper.selectAllsde();
    }


}
