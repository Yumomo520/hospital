package com.goodmap.hospital.pojo.second;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlueTooth {

    @Id
    private String OBJECTID;
    private Integer id;
    private String lyid;
    private String buildname;
    private String floor;
    private String x;
    private String y;
    private String state;
    private String remark;
    private String minor;
    private String SHAPE;


}
