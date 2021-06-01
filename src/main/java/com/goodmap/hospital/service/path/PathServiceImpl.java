package com.goodmap.hospital.service.path;

import com.goodmap.hospital.mapper.PathMapper;
import com.goodmap.hospital.pojo.Path;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/3/15
 * @Description
 */
@Service
public class PathServiceImpl extends BaseServiceImpl<Path> implements PathService {

    @Autowired
    private PathMapper pathMapper;

    @Override
    public void insert(Path path) {
        pathMapper.insertN(path);
    }

    @Override
    public List<Path> selectAll() {
        return pathMapper.selectAll();
    }

}
