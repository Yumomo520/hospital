package com.goodmap.hospital.common.exception;

/**
 * @author 付洪峰
 * @date 2020/7/24
 * @description 请求参数异常
 */
public class QueryParamException extends RuntimeException {
    public QueryParamException(String message) {
        super(message);
    }
}
