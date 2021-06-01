package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.second.BlueTooth;
import com.goodmap.hospital.service.bluetooth.BlueToothService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
@RestController
@Api(tags = "蓝牙管理")
@RequestMapping("BlueTooth")
public class BlueToothController {
    @Autowired
    private BlueToothService blueToothService;

    @GetMapping("selectAll")
    @ApiOperation("查看所有")
    public EntityResult selectAll() {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),blueToothService.selectAll());
    }

    @GetMapping("selectAllByTable")
    @ApiOperation("查看所有")
    public EntityResult selectAllByTable(String tableName) {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),blueToothService.selectAllByTable(tableName));
    }

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "蓝牙管理添加",notes = "蓝牙管理添加")
    public EntityResult add(@RequestBody BlueTooth blueTooth) {
        blueToothService.insert(blueTooth);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation(value = "蓝牙管理修改",notes = "蓝牙管理修改")
    public EntityResult update(@RequestBody BlueTooth blueTooth) {
        blueToothService.update(blueTooth);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }




}
