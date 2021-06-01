package com.goodmap.hospital.common.exception;

/**
 * @author 付洪峰
 * @date 2020/8/17
 * @description 删除数据异常
 */
public class DeleteDataException extends RuntimeException {
    public DeleteDataException(String msg) {
        super(msg);
    }
}
