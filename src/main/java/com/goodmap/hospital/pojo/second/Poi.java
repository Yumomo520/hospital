package com.goodmap.hospital.pojo.second;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author 李美泉
 * @Data 2020/12/4 time
 * @Description
 **/
@Data
@NoArgsConstructor
public class Poi extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = -6742294965610589734L;

    @Id
    private Integer OBJECTID;

    private String Shape;

    @ApiModelProperty("单位名称")
    private String unitname;

    @ApiModelProperty("楼名")
    private String buildname;

    @ApiModelProperty("楼层名")
    private String floor;

    @ApiModelProperty("兴趣点名称")
    private String poiname;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    private Double x;
    private Double y;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("楼层编号")
    private Integer fnum;

    @ApiModelProperty("楼ID")
    private Integer buildid;

    @ApiModelProperty("图片路径")
    private String picurl;

    @ApiModelProperty("视频路径")
    private String videourl;





}
