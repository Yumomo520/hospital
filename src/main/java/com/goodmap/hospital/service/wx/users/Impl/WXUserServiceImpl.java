package com.goodmap.hospital.service.wx.users.Impl;

import com.goodmap.hospital.mapper.WXUserMapper;
import com.goodmap.hospital.pojo.WXUser;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.wx.users.WXUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/26
 * @Description
 */
@Service
public class WXUserServiceImpl extends BaseServiceImpl<WXUser> implements WXUserService {

    @Autowired
    private WXUserMapper wxUserMapper;

    @Override
    public WXUser selectByid(String id) {
        return wxUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WXUser> selectAll() {
        return wxUserMapper.selectAll();
    }

    @Override
    public List<WXUser> selectBySkey(String skey) {
        return wxUserMapper.selectBySkey(skey);
    }

    @Override
    public void updateByskey(String state,String skey) {
        wxUserMapper.updateBySkey(state,skey);
    }
}
