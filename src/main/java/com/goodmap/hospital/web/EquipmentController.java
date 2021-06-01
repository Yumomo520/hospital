package com.goodmap.hospital.web;

import com.goodmap.hospital.common.easyexcel.EasyExcelUtil;
import com.goodmap.hospital.common.exception.AddDataException;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.Equipment;
import com.goodmap.hospital.service.equipment.EquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 刘智强
 * @date 2020/12/26
 * @Description
 */
@RestController
@Api(tags = "设备管理")
@RequestMapping("equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "设备管理添加",notes = "设备管理添加")
    public EntityResult add(@RequestBody Equipment equipment) {
        equipmentService.add(equipment);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation(value = "设备管理修改",notes = "设备管理修改")
    public EntityResult update(@RequestBody Equipment equipment) {
        equipmentService.update(equipment);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("delete")
    @ResponseBody
    @ApiOperation(value = "设备管理删除")
    public EntityResult delete(@RequestParam("id") Integer id) {
        equipmentService.deleteData(id);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @DeleteMapping("deletes")
    @ResponseBody
    @ApiOperation(value = "设备管理批量删除")
    public EntityResult deletes(@RequestParam("ids") Long[] ids) {
        equipmentService.delete(ids);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    @PostMapping("imports")
    @ResponseBody
    @ApiOperation(value = "设备管理导入数据")
    public EntityResult imports(@RequestParam MultipartFile file) {
        if(file.isEmpty()) {
            throw new AddDataException("上传文件不能为空");
        }
        try {
            List<Object> list = null;
            try {
                list = EasyExcelUtil.readExcel(file,new Equipment(),1,1);
                List<Equipment> equipments = (List<Equipment>)((Object) list);
                equipmentService.ListAdd(equipments);
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
}
