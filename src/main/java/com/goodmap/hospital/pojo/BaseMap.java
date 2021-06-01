package com.goodmap.hospital.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
@Data
@Table(name = "basemap")
public class BaseMap {

    @Id
    private Integer id;
    private String unitname;
    private String layername;
    private String layerurl;
}
