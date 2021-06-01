package com.goodmap.hospital.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pinkcatcat
 * @version 1.0
 * @date 2020/6/8 10:00
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean<T> {

    private String statusCode;

    private T data;


}
