package com.goodmap.hospital.web;

import com.goodmap.hospital.common.easyexcel.EasyExcelUtil;
import com.goodmap.hospital.common.easyexcel.ExportExcel;
import com.goodmap.hospital.common.exception.AddDataException;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.PageResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.common.utils.RedisUtils;
import com.goodmap.hospital.pojo.Floor;
import com.goodmap.hospital.pojo.FloorEdit;
import com.goodmap.hospital.service.floor.FloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/28
 * @Description
 */
@Controller
@Api(tags = "楼层管理")
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "楼层管理添加",notes = "楼层管理添加")

    public EntityResult add(@RequestBody Floor floor) {
        floorService.insert(floor);
        redisUtils.del("floorAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation(value = "楼层管理修改",notes = "楼层管理修改")
    public EntityResult update(@RequestBody Floor floor) {
        floorService.update(floor);
        redisUtils.del("floorAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("delete")
    @ResponseBody
    @ApiOperation(value = "楼层管理删除")
    public EntityResult delete(@RequestParam("id") Integer id) {
        floorService.deleteData(id);
        redisUtils.del("floorAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }
    @GetMapping("selectAll")
    @ResponseBody
    @ApiOperation("查询全部")
    public EntityResult selectAll() {
        boolean floorAll = redisUtils.hasKey("floorAll");
        if(floorAll) {
            return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),redisUtils.get("floorAll"));
        }
        else {
            List<Floor> floors = floorService.selectAll();
            redisUtils.set("floorAll",floors,redisUtils.getSecondsNextEarlyMorning());
            return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),floors);
        }
    }

    @DeleteMapping("deletes")
    @ResponseBody
    @ApiOperation(value = "楼层管理批量删除")
    public EntityResult deletes(@RequestParam("ids") Long[] ids) {
        floorService.delete(ids);
        redisUtils.del("floorAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("search")
    @ResponseBody
    @ApiOperation("模糊查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "unitname",value = "单位名称",required = false,paramType = "query",dataType = "string"),
                        @ApiImplicitParam(name = "remark",value = "备注",required = false,paramType = "query",dataType = "string")})
    public EntityResult selectUnitAndBuild (@RequestParam(value = "unitname",required = false) String unitname,
                                            @RequestParam(value = "remark",required = false) String remark) {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),floorService.selectUnitAndBuild(unitname,remark));
    }

    @PostMapping("imports")
    @ResponseBody
    @ApiOperation(value = "楼层管理导入数据")
    public EntityResult imports(@RequestParam MultipartFile file) {
        if(file.isEmpty()) {
            throw new AddDataException("上传文件不能为空");
        }
        try {
            List<Object> list = null;
            try {
                list = EasyExcelUtil.readExcel(file,new Floor(),1,1);
                List<Floor> floors = (List<Floor>)((Object) list);
                floorService.ListAdd(floors);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(file.getInputStream()!= null){
                    file.getInputStream().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("export")
    @ApiOperation(value = "楼层管理导出数据")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Floor> data = floorService.findAll().getData();
        String fileName = "楼层管理导出数据";
        ExportExcel.writeExcel(response,data,fileName,Floor.class);
    }








}
