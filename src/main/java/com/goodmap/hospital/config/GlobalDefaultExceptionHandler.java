package com.goodmap.hospital.config;

import com.goodmap.hospital.common.exception.*;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;

/**
 * 全局异常处理  
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    /**
     * 没有权限时抛出异常
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public EntityResult<String> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return new EntityResult<>(ResultStatus.NOT_PERMISSION_EMPLOY_API.getCode(), ResultStatus.NOT_PERMISSION_EMPLOY_API.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public EntityResult<String> notLoggedInToOperate(HttpServletRequest request, UnauthenticatedException e) {
        e.printStackTrace();
        return new EntityResult<>(ResultStatus.USER_NOT_LOGIN.getCode(), ResultStatus.USER_NOT_LOGIN.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({
            CannotGetJdbcConnectionException.class,
            PersistenceException.class,
            CommunicationsException.class,
            SQLTransientConnectionException.class,
            SQLException.class,
            MySQLNonTransientConnectionException.class,
            //数据库语法错误
            BadSqlGrammarException.class,
            MySQLSyntaxErrorException.class,
            MySQLIntegrityConstraintViolationException.class,
            DataIntegrityViolationException.class,
    })
    @ResponseBody
    public EntityResult<String> databasesExceptionHandle(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return new EntityResult<>(ResultStatus.DATABASE_SERVICE_EXCEPTION.getCode(), ResultStatus.DATABASE_SERVICE_EXCEPTION.getMessage(), request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(value = {JedisException.class, JedisConnectionException.class})
    public EntityResult<String> redisErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return new EntityResult<>(ResultStatus.REDIS_EXCEPTION.getCode(), ResultStatus.REDIS_EXCEPTION.getMessage(), request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public EntityResult<String> requestMethodNodeSupport(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return new EntityResult<>(ResultStatus.REQUEST_METHOD_NOT_SUPPORT.getCode(), ResultStatus.REQUEST_METHOD_NOT_SUPPORT.getMessage(), request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public EntityResult<String> exceptionHandler(HttpServletRequest request, RuntimeException e) {
        e.printStackTrace();
        if (e instanceof QueryParamException) {
            return new EntityResult<>(ResultStatus.USER_REQUEST_PARAM_ERROR.getCode(), e.getMessage(), request.getRequestURI());
        } else if (e instanceof HttpMessageNotReadableException) {
            return new EntityResult<>(ResultStatus.USER_REQUEST_PARAM_ERROR.getCode(), ResultStatus.USER_REQUEST_PARAM_ERROR.getMessage(), request.getRequestURI());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            return new EntityResult<>(ResultStatus.USER_REQUEST_PARAM_ERROR.getCode(), "请求参数类型异常", request.getRequestURI());
        } else if (e instanceof AddDataException) {
            return new EntityResult<>(ResultStatus.ADD_ENTITY_ERROR.getCode(), e.getMessage(), request.getRequestURI());
        } else if (e instanceof EditDataException) {
            return new EntityResult<>(ResultStatus.EDIT_ENTITY_ERROR.getCode(), e.getMessage(), request.getRequestURI());
        } else if (e instanceof DataNotFoundException) {
            return new EntityResult<>(ResultStatus.DATA_NOT_FOUND.getCode(), e.getMessage(), request.getRequestURI());
        }
        return new EntityResult<>(ResultStatus.SERVER_SIDE_ERROR.getCode(), ResultStatus.SERVER_SIDE_ERROR.getMessage(), request.getRequestURI());
    }

    /**
     * 校验失败
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public EntityResult<String> nnn(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder builder = new StringBuilder("校验失败,");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }
        return new EntityResult<>(ResultStatus.DATA_NOT_FOUND.getCode(), builder.toString(), request.getRequestURI());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public EntityResult<String> con(ConstraintViolationException e, HttpServletRequest request) {
        return new EntityResult<>(ResultStatus.USER_REQUEST_PARAM_ERROR.getCode(), e.getMessage(), request.getRequestURI());
    }

}
