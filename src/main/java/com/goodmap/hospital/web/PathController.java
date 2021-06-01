package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.Path;
import com.goodmap.hospital.service.path.PathService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/3/15
 * @Description
 */
@RestController
@Api(tags = "路径管理")
@RequestMapping("path")
public class PathController {
    
    @Autowired
    private PathService pathService;

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "路径添加",notes = "路径添加")
    public EntityResult add(@RequestBody Path Path) {
        pathService.insert(Path);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation(value = "路径修改",notes = "路径修改")
    public EntityResult update(@RequestBody Path Path) {
        pathService.update(Path);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("delete")
    @ResponseBody
    @ApiOperation(value = "路径删除",notes = "路径删除")
    public EntityResult delete(@RequestParam("id") Integer id) {
        pathService.deleteData(id);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("deletes")
    @ResponseBody
    @ApiOperation(value = "路径批量删除" ,notes = "路径批量删除")
    public EntityResult deletes(@RequestParam("ids") Long[] ids) {
        pathService.delete(ids);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("selectAll")
    @ApiOperation("查询全部")
    public EntityResult<List<Path>> selectAll(){
        return new EntityResult<List<Path>>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(), pathService.selectAll());
    }
}
