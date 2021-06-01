package com.goodmap.hospital.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author 刘智强
 * @date 2021/4/24
 * @Description
 */
@Data
public class PeopleTrack {

    @Id
    private Integer id;
    private String username;
    private String floor;
    private String x;
    private String y;
    private String longitude;
    private String latitude;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

}
