package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.BaseMap;
import com.goodmap.hospital.service.basemap.BaseMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/14
 * @Description
 */
@RestController
@RequestMapping("BaseMap")
@Api(tags = "底层地图")
public class BaseMapController {

    @Autowired
    private BaseMapService baseMapService;
    @GetMapping("selectAll")
    @ApiOperation("查询全部")
    public EntityResult<List<BaseMap>> selectAll() {
        return new EntityResult<List<BaseMap>>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),baseMapService.selectAll());
    }
}
