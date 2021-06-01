package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.Traffic;
import com.goodmap.hospital.service.traffic.TrafficService;
import io.swagger.annotations.Api;
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
@Api(tags = "楼栋交通")
@RequestMapping("traffic")
public class TrafficController {
    @Autowired
    private TrafficService trafficService;

    @GetMapping("selectAll")
    @ApiOperation("查询所有")
    public EntityResult<List<Traffic>> selectAll() {
        return new EntityResult<List<Traffic>>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),trafficService.selectAll());
    }
}
