package com.goodmap.hospital.web;


import com.alibaba.fastjson.JSON;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.History;
import com.goodmap.hospital.pojo.Users;
import com.goodmap.hospital.service.history.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

/**
 * @Author 刘智强
 * @Date 2020/12/23
 * @Version 1.0
 */
@Controller
@Api(tags = "历史记录")
@RequestMapping("history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserController userController;


    @PostMapping("insertorUpdate")
    @ResponseBody
    @ApiOperation(value = "历史记录存在更新，不存在插入",notes = "历史记录存在更新，不存在插入")
    public EntityResult insertorUpdate(@RequestBody History history,Principal principal){
        String s = principal.getName().split(",")[0];
        String uid = s.substring(s.length()-1);
        System.out.println("Uid:"+uid);
        history.setUid(Integer.parseInt(uid));
        history.setAddtime(new Date());
        historyService.insertorUpdate(history);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("selectByNewTen")
    @ResponseBody
    @ApiOperation(value = "查询最新10条记录",notes = "查询最新10条记录")
    public EntityResult selectByNewTen(){
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),historyService.findByNewTen());
    }

    @GetMapping("selectAll")
    @ResponseBody
    @ApiOperation("查询全部")
    public EntityResult selectAll() {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),historyService.selectAll());
    }

    @DeleteMapping("deletes")
    @ResponseBody
    @ApiOperation(value = "历史记录批量删除")
    public EntityResult deletes(@RequestParam("ids") Long[] ids) {
        historyService.delete(ids);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }




}
