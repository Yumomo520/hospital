package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.pojo.Gps;
import com.goodmap.hospital.service.trajectory.TrajectoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/10/12 time
 * @Description
 **/
@RestController
@Api(tags = "人员轨迹")
@RequestMapping("trajectory")
public class TrajectoryController {
    @Autowired
    private TrajectoryService trajectoryService;
    @GetMapping("")
    @ApiOperation("人员轨迹查询")
    @ApiImplicitParams( {@ApiImplicitParam(name = "number",value = "成员编号",paramType = "query",dataType = "String"),
                        @ApiImplicitParam(name = "startTime",value = "开始时间",paramType = "query",dataType = "date-time"),
                        @ApiImplicitParam(name = "endTime",value = "结束时间",paramType = "query",dataType = "date-time"),
                        @ApiImplicitParam(name = "page",value = "当前页",paramType = "query",dataType = "int"),
                        @ApiImplicitParam(name = "rows",value = "每页的条数",paramType = "query",dataType = "int")})
    public EntityResult<List<Gps>> selectByData(@RequestParam(value = "number",required = false) String nubmer,
                                                @RequestParam(value = "startTime",required = false)Date startTime,
                                                @RequestParam(value = "endTime",required = false) Date endTime,
                                                @RequestParam(value = "page",required = false) Integer page,
                                                @RequestParam(value = "rows",required = false) Integer rows){
        return trajectoryService.selectByData(nubmer,startTime,endTime,page,rows);
    }

}
