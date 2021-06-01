package com.goodmap.hospital.config;

import com.alibaba.fastjson.JSONObject;
import com.goodmap.hospital.common.utils.HttpContextUtils;
import com.goodmap.hospital.config.Log.OperationLog;
import com.goodmap.hospital.pojo.Logall;
import com.goodmap.hospital.pojo.Users;
import com.goodmap.hospital.service.Log.LogService;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author 李美泉
 * @Data 2020/9/23 time
 * @Description
 **/
@Log
@Component
@Aspect
public class LogallAspect {
    private final LogService logService;

    public LogallAspect(LogService logService) {
        this.logService = logService;
    }
    /**
     * 切入点
     * 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.goodmap.hospital.config.Log.OperationLog)")
    public void executePackage(){
    }

    @AfterReturning("executePackage()")
    public void saveLog(JoinPoint joinPoint){
        Logall logall = new Logall();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        logall.setType(annotation.type().getOperation());
        logall.setOperation(annotation.value());
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String name = method.getName();
        logall.setMethod(className+"."+name);
        //请求的参数
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //屏蔽一些参数
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile || args[i] instanceof BindingResult) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        if (arguments.length != 0) {
            String paramter;
            try {
                paramter = JSONObject.toJSONString(arguments);
            } catch (Exception e) {
                paramter = Arrays.toString(arguments);
            }
            //将参数所在的数组转换成json
//            String params = JSON.toJSONString(args);
            logall.setParams(paramter);
        }
        //操作时间
        logall.setCreateDate(new Date());
        //获取用户名
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if(users != null){
            if (StringUtils.isNotEmpty(users.getUsername()))
                logall.setUsername(users.getUsername());
            if (StringUtils.isNotEmpty(users.getChineseName()))
                logall.setChinaName(users.getChineseName());
        }
        //获取用户ip地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        logall.setIp(request.getRemoteAddr());
        //保存到数据库
        logService.add(logall);
    }
}
