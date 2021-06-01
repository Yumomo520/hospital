package com.goodmap.hospital.common.exception;

/**
 * @author 付洪峰
 * @date 2020/8/17
 * @description 添加数据异常
 */
public class AddDataException extends RuntimeException {
    public AddDataException(String msg) {
        super(msg);
    }
}
