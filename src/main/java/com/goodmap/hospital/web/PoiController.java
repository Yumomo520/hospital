package com.goodmap.hospital.web;

import com.goodmap.hospital.common.easyexcel.EasyExcelUtil;
import com.goodmap.hospital.common.easyexcel.ExportExcel;
import com.goodmap.hospital.common.exception.AddDataException;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.common.utils.RedisUtils;
import com.goodmap.hospital.pojo.Floor;
import com.goodmap.hospital.pojo.Poi;
import com.goodmap.hospital.pojo.Vo.DestinationVo;
import com.goodmap.hospital.service.poi.PoiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/11/28 time
 * @Description
 **/
@RestController
@RequestMapping("poi")
@Api(tags = "目的地")
@Slf4j
public class PoiController {
    @Autowired
    private PoiService poiService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("destination")
    @ApiOperation("目的地")
    @ApiImplicitParam(name = "hospitalId",value = "医院id",paramType = "query",dataType = "int",required = true)
    public EntityResult<List<DestinationVo>> select(@RequestParam Integer hospitalId){
        List<DestinationVo> destinationVos = poiService.selectAim(hospitalId);
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),destinationVos);
    }
    @GetMapping("selectAll")
    @ApiOperation("查询全部")
    public EntityResult<List<Poi>> selectAll(){
        boolean poiAll = redisUtils.hasKey("poiAll");
        if (poiAll){
            return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),redisUtils.get("poiAll"));
        }else {
            List<Poi> pois = poiService.selectAll();
            redisUtils.set("poiAll",pois,redisUtils.getSecondsNextEarlyMorning());
            return new EntityResult<List<Poi>>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), pois);
        }
    }

    @GetMapping("selectPoi")
    @ApiOperation("模糊查询Poi")
    public EntityResult<List<Poi>> selectByName(@RequestParam String name){
        return new EntityResult<List<Poi>>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(), poiService.selectByName(name));
    }
    @GetMapping("build")
    @ApiOperation("根据楼名查询poi所有")
    public EntityResult<List<Poi>> selectByBuild(@RequestParam String buildName){
        return new EntityResult<List<Poi>>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),poiService.selectByBuild(buildName));
    }
    @GetMapping("buildid")
    @ApiOperation("根据楼名查询poi楼层")
    public EntityResult<List<Poi>> selectByBname(@RequestParam("bname") String bname){
        return new EntityResult<List<Poi>>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),poiService.selectByBname(bname));
    }
    @GetMapping("selectAllByTable")
    @ApiOperation("用表名查看所有")
    public EntityResult selectAllByTable(String tableName) {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),poiService.selectAllByTable(tableName));
    }


    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "兴趣点添加",notes = "兴趣点添加")
    public EntityResult add(@RequestBody Poi poi) {
        poiService.insert(poi);
        redisUtils.del("poiAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation(value = "兴趣点修改",notes = "兴趣点修改")
    public EntityResult update(@RequestBody Poi poi) {
        poiService.update(poi);
        redisUtils.del("poiAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("delete")
    @ResponseBody
    @ApiOperation(value = "兴趣点删除",notes = "兴趣点删除")
    public EntityResult delete(@RequestParam("id") Integer id) {
        poiService.deleteData(id);
        redisUtils.del("poiAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("deletes")
    @ResponseBody
    @ApiOperation(value = "兴趣点批量删除" ,notes = "兴趣点批量删除")
    public EntityResult deletes(@RequestParam("ids") Long[] ids) {
        poiService.delete(ids);
        redisUtils.del("poiAll");
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PostMapping("imports")
    @ResponseBody
    @ApiOperation(value = "兴趣点excel导入",notes = "兴趣点excel导入")
    public EntityResult imports(@RequestParam MultipartFile file) {
        if(file.isEmpty()) {
            throw new AddDataException("上传文件不能为空");
        }
        try {
            List<Object> list = null;
            try {
                list = EasyExcelUtil.readExcel(file,new Poi(),1,1);
                List<Poi> pois = (List<Poi>)((Object) list);
                poiService.ListAdd(pois);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (file.getInputStream() != null){
                    file.getInputStream().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @GetMapping("export")
    @ApiOperation(value = "兴趣点导出数据")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Poi> data = poiService.findAll().getData();
        String fileName = "兴趣点导出数据";
        ExportExcel.writeExcel(response,data,fileName,Poi.class);
    }

}
