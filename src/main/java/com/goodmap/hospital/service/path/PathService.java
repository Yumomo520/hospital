package com.goodmap.hospital.service.path;

import com.goodmap.hospital.pojo.Path;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/3/15
 * @Description
 */
public interface PathService extends BaseService<Path> {
    void insert(Path path);

    List<Path> selectAll();
}
