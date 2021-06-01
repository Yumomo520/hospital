package com.goodmap.hospital.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 刘智强
 * @date 2021/1/29
 * @Description
 */
@Data
@Table(name = "his_pos")
public class HisPos {
    @Id
    private Integer id;
    private String openid;
    private String x;
    private String y;
    private String longitude;
    private String latitude;
    private String floor;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp;
}
