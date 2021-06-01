package com.goodmap.hospital.config.shiro;

import com.goodmap.hospital.config.CustomSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;

/**
 * @Author 李美泉
 * @Data 2020/9/16 time
 * @Description
 **/
@Configuration
public class ShiroConfig {
    @Value("${swagger.enabled}")
    private boolean flag;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    /**
     * 把shiro的生命周期给spring管理
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * web组件配置
     * 注册自定义的过滤器，支持依赖注入，组件排序
     * @return
     */
    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/*"));
        filterRegistrationBean.setName("myShiroFilter");
        return filterRegistrationBean;
    }
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(sessionManager());
        defaultWebSecurityManager.setRealm(myRealm());
        return defaultWebSecurityManager;
    }
    /**
     * 该方法用于返回过滤器工厂
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/users/nologin");
        HashMap<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/users/login","anon");
        filterChainDefinitionMap.put("/users/loginexit","anon");
        if(flag){
            //swagger接口权限 开放
            filterChainDefinitionMap.put("/swagger-ui.html", "anon");
            filterChainDefinitionMap.put("/webjars/**", "anon");
            filterChainDefinitionMap.put("/v2/**", "anon");
            filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        }
        filterChainDefinitionMap.put("/**","authc");
        //跳过shiro登录验证直接访问接口数据
        filterChainDefinitionMap.put("sign/**/skip/**","anon");
        filterChainDefinitionMap.put("/websocket/**","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 自定义的myRealm数据源封装成实体类
     * @return  数据源
     */
    @Bean("myRealm")
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
//        缓存总开关
        myRealm.setCachingEnabled(true);
//        授权缓存
        myRealm.setAuthorizationCachingEnabled(true);
//        认证缓存
        myRealm.setAuthenticationCachingEnabled(true);
        return myRealm;
    }
    /**
     * 缓存管理(用户信息权限管理)
     * CacheManager不仅仅用于CachingSessionDAO，还有CachingRealm
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisManager = new RedisCacheManager();
        redisManager.setRedisManager(redisManager());
        return redisManager;
    }

    /**
     * 配置redis连接，设置地址与端口
     * @return
     */
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }

    /**
     * 配置RedisSessionDAO用于持久化
     *管理RedisCacheManager
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     * 配置sessionDao(session持久化)管理和缓存管理
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setCacheManager(cacheManager());
        return sessionManager;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
