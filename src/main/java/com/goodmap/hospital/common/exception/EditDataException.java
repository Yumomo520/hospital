package com.goodmap.hospital.common.exception;

/**
 * @author 付洪峰
 * @date 2020/8/17
 * @description 修改数据异常
 */
public class EditDataException extends RuntimeException {
    public EditDataException(String msg) {
        super(msg);
    }
}
