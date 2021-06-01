package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.second.BlueTooth;
import com.goodmap.hospital.pojo.second.Panoramic;
import com.goodmap.hospital.service.bluetooth.BlueToothService;
import com.goodmap.hospital.service.panoramic.PanoramicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘智强
 * @date 2021/1/23
 * @Description
 */
@RestController
@Api(tags = "全景管理")
@RequestMapping("Panoramic")
public class PanoramicController {
    @Autowired
    private PanoramicService panoramicService;

    @GetMapping("selectAllByTable")
    @ApiOperation("查看所有")
    public EntityResult selectAllByTable(String tableName) {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),panoramicService.selectAllByTable(tableName));
    }

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "全景管理添加",notes = "全景管理添加")
    public EntityResult add(@RequestBody Panoramic panoramic) {
        panoramicService.insert(panoramic);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation(value = "全景管理修改",notes = "全景管理修改")
    public EntityResult update(@RequestBody Panoramic panoramic) {
        panoramicService.update(panoramic);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }




}
