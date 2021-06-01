package com.goodmap.hospital.config.shiro;

import com.goodmap.hospital.mapper.UserMapper;
import com.goodmap.hospital.pojo.Users;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author 李美泉
 * @Data 2020/9/16 time
 * @Description
 **/

public class MyRealm extends AuthorizingRealm {
    private static final long serialVersionUID = 634523536476L;
    @Autowired
    private UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username =  authenticationToken.getPrincipal().toString();
        Users users = userMapper.selectByName(username);
        if(users==null)
            throw new UnknownAccountException("账号不存在");
        if(users.getState()!=1)
            throw new DisabledAccountException("账户冻结");
        /**
         * 用户对象 ps:此参数可以通过subject.getPrincipal()方法获取—获取当前记录的用户，从这个用户对象进而再获取一系列的所需要的属性
         * 用户密码
         * 自定义的realm名字
         */
        return new SimpleAuthenticationInfo(users,users.getPassword(),getName());
    }
    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
