package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.config.Log.OperationLog;
import com.goodmap.hospital.config.Log.OperationType;
import com.goodmap.hospital.config.shiro.MyRealm;
import com.goodmap.hospital.mapper.UserMapper;
import com.goodmap.hospital.pojo.Users;
import com.goodmap.hospital.service.users.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/9/16 time
 * @Description
 **/
@Controller
@RequestMapping("/users")
@Api(tags = "用户")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    MyRealm myRealm;
    @ResponseBody
    @PostMapping("login")
    @ApiOperation(value = "用户登录",notes = "用户登录")
//    @OperationLog(value = "用户登录",type = OperationType.OTHERS)
    public EntityResult login(@RequestBody Users users) {
        return userService.login(users.getUsername(),users.getPassword());
    }
    @ResponseBody
    @GetMapping("nologin")
    public EntityResult nologin(){
        return new EntityResult(ResultStatus.USER_NOT_LOGIN.getCode(),ResultStatus.USER_NOT_LOGIN.getMessage());
    }


    /**
     * @RequiresUser
     * 验证用户是否被记忆，user有两种含义：
     *
     * 一种是成功登录的（subject.isAuthenticated() 结果为true）；
     *
     * 另外一种是被记忆的（subject.isRemembered()结果为true）。
     */
    @RequiresUser
    @GetMapping("loginexit")
    @ResponseBody
    @ApiOperation(value = "用户退出",notes = "用户推出")
    public EntityResult notLogin(){
        Subject subject = SecurityUtils.getSubject();
        try {
            Users users = (Users) subject.getPrincipal();
            if (users == null) {
                return new EntityResult(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), null);
            } else {
                subject.logout();
                myRealm.clearCachedAuthenticationInfo(subject.getPrincipals());
                myRealm.clearCachedAuthorizationInfo(subject.getPrincipals());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return new EntityResult(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), null);
    }
}
