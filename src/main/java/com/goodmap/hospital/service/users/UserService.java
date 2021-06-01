package com.goodmap.hospital.service.users;

import com.goodmap.hospital.common.result.EntityResult;

import java.util.Map;

/**
 * @Author 李美泉
 * @Data 2020/9/16 time
 * @Description
 **/
public interface UserService {
    EntityResult<String> login(String username,String password);
}
