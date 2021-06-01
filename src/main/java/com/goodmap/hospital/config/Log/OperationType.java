package com.goodmap.hospital.config.Log;

/**
 * @Author 李美泉
 * @Data 2020/9/23 time
 * @Description
 **/
public enum OperationType {
    SELECT("查询"),UPDATE("修改"),ADD("增加"),DELETE("删除"),OTHERS("其他操作");
    private String operation;

    OperationType(String operation) {
        this.operation = operation;
    }
    public String getOperation() {
        return operation;
    }
}
