package com.goodmap.hospital.service.poi;

import com.goodmap.hospital.pojo.Poi;
import com.goodmap.hospital.pojo.Vo.DestinationVo;
import com.goodmap.hospital.pojo.second.Panoramic;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

public interface PoiService extends BaseService<Poi> {
    List<Poi> selectByBuild(String buildName);
    List<Poi> selectByBname(String buildName);
    List<Poi> selectAll();

    List<Poi> selectAllByTable(String tableName);

    List<DestinationVo> selectAim(Integer hospitalId);
    List<Poi> selectByName(String name);
    void ListAdd(List<Poi> t);
    void insert(Poi poi);

}
