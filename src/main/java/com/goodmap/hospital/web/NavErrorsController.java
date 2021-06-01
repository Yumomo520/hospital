package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.NavErrors;
import com.goodmap.hospital.pojo.Vo.NavPoi;
import com.goodmap.hospital.pojo.Vo.NavPoiList;
import com.goodmap.hospital.pojo.second.Poi;
import com.goodmap.hospital.service.nav_errors.NavErrorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 刘智强
 * @date 2021/4/1
 * @Description
 */
@RestController
@RequestMapping("navErrors")
@Api(tags = "导航路线错误信息")
public class NavErrorsController {

    @Autowired
    private NavErrorService navErrorService;

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "添加")
    public EntityResult add(@RequestBody NavPoiList navPoiList) {

        NavErrors navErrors = new NavErrors();
        NavPoi start = navPoiList.getStart();
        NavPoi end = navPoiList.getEnd();
        String message = navPoiList.getMessage();

        navErrors.setStart_point(start.getBuildname()+start.getFloor()+start.getPoiname());
        navErrors.setEnd_point(end.getBuildname()+end.getFloor()+end.getPoiname());
        navErrors.setTime(new Date());
        navErrors.setRemark(message);

        navErrorService.insertN(navErrors);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("selectAll")
    @ResponseBody
    @ApiOperation(value = "查询所有")
    public EntityResult selectAll(){
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),navErrorService.findAll());
    }

}
