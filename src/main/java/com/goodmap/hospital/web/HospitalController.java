package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.Building;
import com.goodmap.hospital.pojo.Floor;
import com.goodmap.hospital.pojo.Hospital;
import com.goodmap.hospital.service.floor.FloorService;
import com.goodmap.hospital.service.hospital.BuildingService;
import com.goodmap.hospital.service.hospital.HospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/9/17 time
 * @Description
 **/
@Controller
@RequestMapping("/hospital")
@Api(tags = "医院")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private FloorService floorService;

    @GetMapping("")
    @ResponseBody
    @ApiOperation(value = "医院查询",notes = "医院查询")
    @ApiImplicitParam(name = "hospitalName",value = "医院名字",paramType = "query",dataType = "String")
    EntityResult<List<Hospital>> selectByName(@RequestParam(value = "hospitalName",required = false) String hospitalName){
        return hospitalService.selectByName(hospitalName);
    }
    @GetMapping("building")
    @ResponseBody
    @ApiOperation(value = "楼名查询",notes = "楼名查询")
    @ApiImplicitParam(name = "hospitalId",value = "医院id",paramType = "query",dataType = "Integer")
    EntityResult<List<Building>> selectByhospital(@RequestParam("hospitalId") Integer hospitalId){
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),buildingService.selectByhospital(hospitalId));
    }

    @GetMapping("selectByBuildName")
    @ResponseBody
    @ApiOperation(value = "楼层查询",notes = "楼层查询")
    @ApiImplicitParam(name = "buildName",value = "楼名",paramType = "query",dataType = "String")
    EntityResult<List<Floor>> selectByFloor(@RequestParam("floor") String floor){
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),floorService.selectByFloor(floor));
    }

}


