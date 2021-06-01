package com.goodmap.hospital.pojo.Vo;

import lombok.Data;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/4/6
 * @Description
 */
@Data
public class NavPoiList {

    NavPoi start;
    NavPoi end;
    String message;
}
