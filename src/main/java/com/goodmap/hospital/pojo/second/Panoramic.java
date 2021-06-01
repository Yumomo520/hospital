package com.goodmap.hospital.pojo.second;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author 刘智强
 * @date 2021/2/24
 * @Description
 */
@Data
public class Panoramic {

    @Id
    private Integer OBJECTID;
    private String name;
    private String url;
    private String remark;
    private String SHAPE;
}
