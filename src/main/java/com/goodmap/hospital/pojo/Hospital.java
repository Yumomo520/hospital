package com.goodmap.hospital.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

/**
 * @Author 李美泉
 * @Data 2020/9/17 time
 * @Description
 **/
@Data
@ApiModel("医院")
public class Hospital {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty("医院名字")
    private String hospitalName;
    @ApiModelProperty("医院地址")
    private String adress;
    @ApiModelProperty("电话号码")
    private String telephone;
}
