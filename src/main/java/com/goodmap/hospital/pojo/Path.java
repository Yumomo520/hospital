package com.goodmap.hospital.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 刘智强
 * @date 2021/3/15
 * @Description
 */
@Data
@Table(name = "path")
public class Path {

    @Id
    private Integer id;
    private String unitname;
    private String buildname;
    private String floor;
    private Integer fnum;
    private Integer buildid;
    private String url;
    private String remark;
}
