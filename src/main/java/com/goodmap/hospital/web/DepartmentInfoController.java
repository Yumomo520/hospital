package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.DepartmentInfo;
import com.goodmap.hospital.service.department.DepartmentInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 刘智强
 * @date 2021/5/12
 * @Description
 */
@RestController
@RequestMapping("departmentInfo")
@Api(tags = "科室资料")
public class DepartmentInfoController {

    @Autowired
    private DepartmentInfoService departmentInfoService;

    @GetMapping("selectAll")
    @ApiOperation("查询所有")
    private EntityResult selectAll() {
        Map<String, List<DepartmentInfo>> departmentInfoPinYinMap = departmentInfoService.getDepartmentInfoPinYinMap();

        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),departmentInfoPinYinMap);
    }

}
