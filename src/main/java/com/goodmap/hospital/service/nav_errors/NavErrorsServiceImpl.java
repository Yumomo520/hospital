package com.goodmap.hospital.service.nav_errors;

import com.goodmap.hospital.mapper.NavErrorsMapper;
import com.goodmap.hospital.pojo.NavErrors;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘智强
 * @date 2021/4/1
 * @Description
 */
@Service
public class NavErrorsServiceImpl extends BaseServiceImpl<NavErrors> implements NavErrorService {

    @Autowired
    private NavErrorsMapper navErrorsMapper;

    @Override
    public void insertN(NavErrors navErrors1) {
        navErrorsMapper.insertN(navErrors1);
    }
}
