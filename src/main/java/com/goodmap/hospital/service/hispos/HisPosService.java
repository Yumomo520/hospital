package com.goodmap.hospital.service.hispos;

import com.goodmap.hospital.pojo.HisPos;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/25
 * @Description
 */

public interface HisPosService {

    void insertorUpdate(HisPos hisPos);

    List<HisPos> selectAll();

    String selectOpenidBySkey(String skey);

    List<HisPos> selectByskey(String skey);
}
