package com.goodmap.hospital.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 刘智强
 * @Date 2020/12/23 11:27
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@Table(name = "history")
public class History implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty(value = "历史编号")
    private Integer id;

    @ApiModelProperty(value = "楼id")
    private Integer buildid;

    @ApiModelProperty(value = "楼名")
    private String buildname;

    @ApiModelProperty(value = "楼层")
    private String floor;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "poi名称")
    private String poiname;

    @ApiModelProperty(value = "楼层编号")
    private Integer fnum;

    @ApiModelProperty(value = "添加时间")
    private Date addtime;

    @ApiModelProperty(value = "用户Id")
    private Integer uid;



}
