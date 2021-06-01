package com.goodmap.hospital.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 李美泉
 * @Data 2020/11/28 time
 * @Description
 **/
@Data
@Table
@ApiModel("大楼")
public class Building {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty("楼名")
    private String buildingName;
}
