package com.goodmap.hospital.service.poi.Impl;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.config.dataSources.DS;
import com.goodmap.hospital.mapper.PoiMapper;
import com.goodmap.hospital.pojo.Building;
import com.goodmap.hospital.pojo.Poi;
import com.goodmap.hospital.pojo.Vo.DestinationVo;
import com.goodmap.hospital.pojo.second.Panoramic;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.poi.PoiService;
import com.goodmap.hospital.service.hospital.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李美泉
 * @Data 2020/11/28 time
 * @Description
 **/
@Service
@Primary
public class PoiServiceImpl extends BaseServiceImpl<Poi> implements PoiService {
    @Autowired
    PoiMapper poiMapper;
    @Autowired
    private BuildingService buildingService;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public List<DestinationVo> selectAim(Integer hospitalId) {
        List<DestinationVo> o = (List<DestinationVo>) redisTemplate.opsForValue().get("医院" + hospitalId);
        if(o != null) return o;
        List<DestinationVo> destinationVos = new ArrayList<>();
        List<Building> buildings = buildingService.selectByhospital(hospitalId);
        buildings.forEach(building -> {
            DestinationVo destinationVo = new DestinationVo();
            destinationVo.setBuildname(building.getBuildingName());
            List<Poi> floors = poiMapper.selectByBuild(building.getBuildingName());
            destinationVo.setDepart(floors);
            destinationVos.add(destinationVo);
        });
        redisTemplate.opsForValue().set("医院"+hospitalId,destinationVos,30, TimeUnit.MINUTES);
        return destinationVos;
    }

    @Override
    public List<Poi> selectByName(String name) {
        List<Poi> pois = poiMapper.selectByName(name);
        return pois;
    }

    @Override
    public void ListAdd(List<Poi> t) {
        poiMapper.insertListN(t);
    }

    @Override
    public void insert(Poi poi) {
        poiMapper.insertN(poi);
    }

    @Override
    public List<Poi> selectByBuild(String buildName) {
        List<Poi> pois = poiMapper.selectByBuild(buildName);
        return pois;
    }

    @Override
    public List<Poi> selectByBname(String buildName) {
        return poiMapper.selectByBname(buildName);
    }

    @Override
    public List<Poi> selectAll() {
        return poiMapper.selectAll();
    }

    @Override
    @DS("indoormapsde")
    public List<Poi> selectAllByTable(String tableName) {
        return poiMapper.selectAllByTable(tableName);
    }
}
