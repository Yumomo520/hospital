package com.goodmap.hospital.service.lastpos.Impl;

import com.goodmap.hospital.mapper.LastPosMapper;
import com.goodmap.hospital.pojo.LastPos;
import com.goodmap.hospital.service.lastpos.LastPosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/25
 * @Description
 */
@Service
public class LastPosServiceImpl implements LastPosService {

    @Autowired
    private LastPosMapper lastPosMapper;

    @Override
    public void insertorUpdate(LastPos lastPos) {
        lastPosMapper.insertorUpdate(lastPos);
    }

    @Override
    public List<LastPos> selectAll() {
        return lastPosMapper.selectAll();
    }

    @Override
    public String selectOpenidBySkey(String skey) {
        return lastPosMapper.selectOpenidBySkey(skey);
    }

    @Override
    public List<LastPos> selectByskey(String skey) {
        return lastPosMapper.selectByskey(skey);
    }
}
