package com.goodmap.hospital.pojo;

import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
@Data
@NoArgsConstructor
@Table
public class Equipment extends BaseRowModel {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty("设备名称")
    private String equname;
    @ApiModelProperty("经度")
    private String longitude;
    @ApiModelProperty("纬度")
    private String latitude;
    @ApiModelProperty("高度")
    private Double height;
    @ApiModelProperty("状态")
    private String condition;
    @ApiModelProperty("单位名称")
    private String unitname;
    @ApiModelProperty("楼名")
    private String buildname;
    @ApiModelProperty("楼层名")
    private String floorname;
    @ApiModelProperty("备注")
    private String remark;
}
