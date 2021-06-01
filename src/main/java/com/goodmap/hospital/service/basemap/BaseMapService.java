package com.goodmap.hospital.service.basemap;

import com.goodmap.hospital.pojo.BaseMap;
import com.goodmap.hospital.pojo.Poi;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
public interface BaseMapService extends BaseService<BaseMap> {

    List<BaseMap> selectAll();
}
