package com.goodmap.hospital.service.history.impl;

import com.goodmap.hospital.common.result.PageResult;
import com.goodmap.hospital.mapper.HistoryMapper;
import com.goodmap.hospital.pojo.History;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.base.impl.BaseStringServiceImpl;
import com.goodmap.hospital.service.history.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Service
public class HistoryServiceImpl extends BaseServiceImpl<History> implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;


    @Override
    public void insertorUpdate(History history) {
        historyMapper.insertorUpdate(history);
    }

    @Override
    public List<History> findByNewTen() {
        return historyMapper.selectByNewTen();
    }

    @Override
    public List<History> selectAll() {
        return historyMapper.selectAll();
    }
}
