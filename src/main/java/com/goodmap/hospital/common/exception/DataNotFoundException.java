package com.goodmap.hospital.common.exception;

/**
 * @author pinkcatcat
 * @version 1.0
 * @date 2020/7/24 10:56
 * @description
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message){
        super(message);
    }
}
