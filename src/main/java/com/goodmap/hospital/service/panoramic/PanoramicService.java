package com.goodmap.hospital.service.panoramic;



import com.goodmap.hospital.pojo.second.BlueTooth;
import com.goodmap.hospital.pojo.second.Panoramic;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
public interface PanoramicService extends BaseService<Panoramic> {

    List<Panoramic> selectAllByTable(String tableName);

    void insert(Panoramic panoramic);
}
