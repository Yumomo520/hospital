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
@Table(name = "traffic")
public class Traffic {

    @Id
    private Integer id;
    private Integer buildid;
    private String name;
    private String longitude;
    private String latitude;
    private String xmax;
    private String xmin;
    private String ymax;
    private String ymin;

}
