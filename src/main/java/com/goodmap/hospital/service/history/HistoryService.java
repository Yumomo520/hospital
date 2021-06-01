package com.goodmap.hospital.service.history;

import com.goodmap.hospital.common.result.PageResult;
import com.goodmap.hospital.pojo.History;
import com.goodmap.hospital.service.base.BaseService;
import com.goodmap.hospital.service.base.BaseStringService;

import java.util.List;


public interface HistoryService extends BaseService<History> {

    void insertorUpdate(History history);
    /**
     * 查询最新10条数据
     * @return
     */
    List<History> findByNewTen();

    List<History> selectAll();
}
