package com.goodmap.hospital.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Author 李美泉
 * @Data 2020/10/12 time
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("gps")
public class Gps {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty("人员编号")
    private String number;
    @ApiModelProperty("人员名称")
    private String username;
    @ApiModelProperty("经度")
    private Double longitude;
    @ApiModelProperty("维度")
    private Double latitude;
    @ApiModelProperty("高度")
    private Double height;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("方向")
    private String direction;
    @ApiModelProperty("速度")
    private Double speed;
    @ApiModelProperty("记录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    private Date recordTime;
}
