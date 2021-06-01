package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.pojo.ErroreM;
import com.goodmap.hospital.service.poi.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 李美泉
 * @Data 2020/12/22 time
 * @Description
 **/
@RestController
@RequestMapping("message")
@Api(tags = "错误信息")
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("")
    @ApiOperation("错误信息添加")
    public EntityResult<ErroreM> add(@RequestBody ErroreM erroreM){
        System.out.println(erroreM);
        return messageService.addM(erroreM);
    }
}
