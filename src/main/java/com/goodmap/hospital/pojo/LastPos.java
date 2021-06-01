package com.goodmap.hospital.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 刘智强
 * @date 2021/1/25
 * @Description
 */
@Data
@Table(name = "last_pos")
public class LastPos {
    @Id
    private Integer id;
    private String openid;
    private String x;
    private String y;
    private String floor;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp;
}
