package com.goodmap.hospital.service.lastpos;

import com.goodmap.hospital.pojo.LastPos;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/25
 * @Description
 */

public interface LastPosService {

    void insertorUpdate(LastPos lastPos);

    List<LastPos> selectAll();

    String selectOpenidBySkey(String skey);

    List<LastPos> selectByskey(String skey);
}
