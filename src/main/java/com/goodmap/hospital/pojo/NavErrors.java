package com.goodmap.hospital.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author 刘智强
 * @date 2021/4/1
 * @Description
 */
@Data
public class NavErrors {

    @Id
    private Integer id;
    private String start_point;
    private String end_point;
    private Date time;
    private String remark;

}
