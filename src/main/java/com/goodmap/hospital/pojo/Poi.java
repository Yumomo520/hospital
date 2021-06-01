package com.goodmap.hospital.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Author 李美泉
 * @Data 2020/12/4 time
 * @Description
 **/
@Data
@NoArgsConstructor
@Table(name = "poi")
public class Poi extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = -6742294965610589734L;
    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("兴趣点编号")
    private Integer id;

    @ApiModelProperty("单位名称")
    @ExcelProperty(value = "单位名称")
    private String unitname;

    @ApiModelProperty("楼名")
    @ExcelProperty(value = "楼名")
    private String buildname;

    @ApiModelProperty("楼层名")
    @ExcelProperty(value = "楼层名")
    private String floor;

    @ApiModelProperty("兴趣点名称")
    @ExcelProperty(value = "兴趣点名称")
    private String poiname;

    @ApiModelProperty("经度")
    @ExcelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty("纬度")
    @ExcelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty("备注")
    @ExcelProperty(value = "备注")
    private String remark;

    @ApiModelProperty("楼层编号")
    @ExcelProperty(value = "楼层编号")
    private Integer fnum;

    @ApiModelProperty("楼ID")
    @ExcelProperty(value = "楼ID")
    private Integer buildid;

    @ApiModelProperty("图片路径")
    @ExcelProperty(value = "图片路径")
    private String picurl;

    @ApiModelProperty("视频路径")
    @ExcelProperty(value = "视频路径")
    private String videourl;



}
