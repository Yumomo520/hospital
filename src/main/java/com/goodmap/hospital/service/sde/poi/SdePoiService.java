package com.goodmap.hospital.service.sde.poi;


import com.goodmap.hospital.pojo.second.Poi;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

public interface SdePoiService extends BaseService<Poi> {

    List<Poi> selectAllsde();

}
