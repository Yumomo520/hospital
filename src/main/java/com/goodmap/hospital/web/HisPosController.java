package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.HisPos;
import com.goodmap.hospital.service.hispos.HisPosService;
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
@Api(tags = "历史轨迹")
@RequestMapping("hisPos")
public class HisPosController {

    @Autowired
    private HisPosService hisPosService;

    @PostMapping("insertorUpdate")
    @ResponseBody
    @ApiOperation(value = "存在更新，不存在插入",notes = "存在更新，不存在插入")
    public EntityResult insertorUpdata(@RequestBody HisPos hisPos, String skey) {
        hisPos.setTimestamp(new Date());
        hisPos.setOpenid(hisPosService.selectOpenidBySkey(skey));
        hisPosService.insertorUpdate(hisPos);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("selectAll")
    @ResponseBody
    @ApiOperation("查询全部")
    public EntityResult selectAll() {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),hisPosService.selectAll());
    }

    @GetMapping("selectBySkey")
    @ResponseBody
    @ApiOperation("查skey相关的数据")
    public EntityResult selectBySkey(@RequestParam String skey) {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),hisPosService.selectByskey(skey));
    }
}
