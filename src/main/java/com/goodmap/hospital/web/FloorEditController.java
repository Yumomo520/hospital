package com.goodmap.hospital.web;

import com.goodmap.hospital.common.easyexcel.EasyExcelUtil;
import com.goodmap.hospital.common.easyexcel.ExportExcel;
import com.goodmap.hospital.common.exception.AddDataException;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.common.utils.RedisUtils;
import com.goodmap.hospital.pojo.FloorEdit;
import com.goodmap.hospital.service.floo_redit.FloorEditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
@Api(tags = "后台楼层管理")
@RequestMapping("/floorEdit")
public class FloorEditController {

    @Autowired
    private FloorEditService floorService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "后台楼层管理添加",notes = "后台楼层管理添加")
    public EntityResult add(@RequestBody FloorEdit floor) {
        redisUtils.del("floorEditAll");
        floorService.insert(floor);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation(value = "后台楼层管理修改",notes = "后台楼层管理修改")
    public EntityResult update(@RequestBody FloorEdit floor) {
        floorService.update(floor);
        redisUtils.del("floorEditAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("delete")
    @ResponseBody
    @ApiOperation(value = "后台楼层管理删除")
    public EntityResult delete(@RequestParam("id") Integer id) {
        floorService.deleteData(id);
        redisUtils.del("floorEditAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }
    @GetMapping("selectAll")
    @ResponseBody
    @ApiOperation("查询全部")
    public EntityResult selectAll() {
        boolean floorEditAll = redisUtils.hasKey("floorEditAll");
        if(floorEditAll){
            long s = System.currentTimeMillis();
            Object floorEditAll1 = redisUtils.get("floorEditAll");
            long e = System.currentTimeMillis();
            System.out.println("远行时间："+(e-s)+"ms");
            return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),floorEditAll1);


        }else {
            long s = System.currentTimeMillis();
            List<FloorEdit> floorEdits = floorService.selectAll();
            redisUtils.set("floorEditAll",floorEdits,redisUtils.getSecondsNextEarlyMorning());
            long e = System.currentTimeMillis();
            System.out.println("远行时间："+(e-s)+"ms");
            return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),floorEdits);
        }
    }

    @DeleteMapping("deletes")
    @ResponseBody
    @ApiOperation(value = "后台楼层管理批量删除")
    public EntityResult deletes(@RequestParam("ids") Long[] ids) {
        floorService.delete(ids);
        redisUtils.del("floorEditAll");
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
    @ApiOperation(value = "后台楼层管理导入数据")
    public EntityResult imports(@RequestParam MultipartFile file) {
        if(file.isEmpty()) {
            throw new AddDataException("上传文件不能为空");
        }
        try {
            List<Object> list = null;
            try {
                list = EasyExcelUtil.readExcel(file,new FloorEdit(),1,1);
                List<FloorEdit> floors = (List<FloorEdit>)((Object) list);
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
    @ApiOperation(value = "后台楼层管理导出数据")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<FloorEdit> data = floorService.findAll().getData();
        String fileName = "后台楼层管理导出数据";
        ExportExcel.writeExcel(response,data,fileName,FloorEdit.class);
    }








}
