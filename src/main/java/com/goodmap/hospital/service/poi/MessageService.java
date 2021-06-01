package com.goodmap.hospital.service.poi;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.pojo.ErroreM;
import com.goodmap.hospital.service.base.BaseService;

/**
 * @Author 李美泉
 * @Data 2020/12/22 time
 * @Description
 **/
public interface MessageService extends BaseService<ErroreM> {
    EntityResult<ErroreM> addM(ErroreM erroreM);
}
