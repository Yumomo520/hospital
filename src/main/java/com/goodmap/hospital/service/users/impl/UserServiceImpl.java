package com.goodmap.hospital.service.users.impl;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.service.users.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 李美泉
 * @Data 2020/9/16 time
 * @Description
 **/
@Service
public class UserServiceImpl implements UserService {



    @Override
    public EntityResult<String> login(String username, String password) {
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(usernamePasswordToken);
            String id = (String) SecurityUtils.getSubject().getSession().getId();
            return new EntityResult(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), id);
        }catch (DisabledAccountException e){
            return new EntityResult<>(ResultStatus.USER_ACCOUNT_FREEZE.getCode(),ResultStatus.USER_ACCOUNT_FREEZE.getMessage(), null);
        }catch (UnknownAccountException e){
            return new EntityResult<>(ResultStatus.USER_ACCOUNT_NOT_EXIST.getCode(),ResultStatus.USER_ACCOUNT_NOT_EXIST.getMessage(), null);
        }catch (CredentialsException e) {
            return new EntityResult<>(ResultStatus.USER_PASSWORD_ERROR.getCode(), ResultStatus.USER_PASSWORD_ERROR.getMessage(), null);
        }
    }

}
