package com.goodmap.hospital.service.nav_errors;

import com.goodmap.hospital.pojo.NavErrors;
import com.goodmap.hospital.service.base.BaseService;

/**
 * @author 刘智强
 * @date 2021/4/1
 * @Description
 */
public interface NavErrorService extends BaseService<NavErrors> {

    void insertN(NavErrors navErrors);
}
