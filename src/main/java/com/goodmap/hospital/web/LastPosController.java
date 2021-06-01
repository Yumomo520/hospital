package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.LastPos;
import com.goodmap.hospital.service.lastpos.LastPosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 刘智强
 * @date 2021/1/25
 * @Description
 */
@RestController
@Api(tags = "定位轨迹")
@RequestMapping("lastPos")
public class LastPosController {

    @Autowired
    private LastPosService lastPosService;

    @PostMapping("insertorUpdate")
    @ResponseBody
    @ApiOperation(value = "存在更新，不存在插入",notes = "存在更新，不存在插入")
    public EntityResult insertorUpdata(@RequestBody LastPos lastPos, String skey) {
        lastPos.setTimestamp(new Date());
        lastPos.setOpenid(lastPosService.selectOpenidBySkey(skey));
        lastPosService.insertorUpdate(lastPos);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("selectAll")
    @ResponseBody
    @ApiOperation("查询全部")
    public EntityResult selectAll() {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),lastPosService.selectAll());
    }

    @GetMapping("selectBySkey")
    @ResponseBody
    @ApiOperation("查skey相关的数据")
    public EntityResult selectBySkey(@RequestParam String skey) {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),lastPosService.selectByskey(skey));
    }
}
