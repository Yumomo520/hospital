package com.goodmap.hospital.service.wx.users;

import com.goodmap.hospital.pojo.WXUser;
import com.goodmap.hospital.service.base.BaseService;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/26
 * @Description
 */
public interface WXUserService extends BaseService<WXUser>{
    WXUser selectByid(String id);
    List<WXUser> selectAll();
    List<WXUser> selectBySkey(String skey);
    void updateByskey(String state,String skey);
}
