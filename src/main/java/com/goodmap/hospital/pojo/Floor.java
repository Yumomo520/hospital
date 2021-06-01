package com.goodmap.hospital.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
@Data
@Table(name = "floor")
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
public class Floor extends BaseRowModel implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("编号")
    private Integer id;

    @ApiModelProperty("单位名称")
    @ExcelProperty(value = "单位名称")
    private String unitname;

//    @ApiModelProperty("楼名")
//    @ExcelProperty(value = "楼名")
//    private String buildname;
//
//    @ApiModelProperty("楼ID")
//    @ExcelProperty(value = "楼ID")
//    private String buildid;

    @ApiModelProperty("楼层名")
    @ExcelProperty(value = "楼层名")
    private String floor;

    @ApiModelProperty("楼层编号")
    @ExcelProperty(value = "楼层编号")
    private String fnum;

    @ApiModelProperty("类型")
    @ExcelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty("图层URL")
    @ExcelProperty(value = "图层URL")
    private String layerurl;

    @ApiModelProperty("备注")
    @ExcelProperty(value = "备注")
    private String remark;
}
