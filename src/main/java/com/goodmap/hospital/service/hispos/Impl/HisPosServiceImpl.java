package com.goodmap.hospital.service.hispos.Impl;

import com.goodmap.hospital.mapper.HisPosMapper;
import com.goodmap.hospital.pojo.HisPos;
import com.goodmap.hospital.service.hispos.HisPosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/25
 * @Description
 */
@Service
public class HisPosServiceImpl implements HisPosService {

    @Autowired
    private HisPosMapper hisPosMapper;

    @Override
    public void insertorUpdate(HisPos hisPos) {
        hisPosMapper.insertorUpdate(hisPos);
    }

    @Override
    public List<HisPos> selectAll() {
        return hisPosMapper.selectAll();
    }

    @Override
    public String selectOpenidBySkey(String skey) {
        return hisPosMapper.selectOpenidBySkey(skey);
    }

    @Override
    public List<HisPos> selectByskey(String skey) {
        return hisPosMapper.selectByskey(skey);
    }
}
