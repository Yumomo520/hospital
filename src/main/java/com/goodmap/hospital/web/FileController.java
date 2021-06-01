package com.goodmap.hospital.web;

import com.goodmap.hospital.common.exception.AddDataException;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.common.utils.FileUpload;
import com.goodmap.hospital.config.Log.OperationLog;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 刘智强
 * @date 2021/2/20
 * @Description
 */
@RestController
@Api(tags = "文件操作")
@Slf4j
public class FileController {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Value("${file.root.rests}")
    private String filerootPath;

//    @Value("${file-save-path}")
//    private String filesavePath;


    private final FileUpload fileUpload;

    public FileController(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }


//    @ApiOperation(value = "图片上传")
//    @PostMapping(path = "/uploadimg")
//    public EntityResult uploadImg(@RequestParam MultipartFile file,HttpServletRequest request) {
//
//        if(file.isEmpty()) {
//            throw new AddDataException("文件不能为空！");
//        }
//
//        String directory = simpleDateFormat.format(new Date());
//
//        File dir = new File(filesavePath + directory);
//        if(!dir.exists()) {
//            dir.mkdirs();
//        }
//        log.info("图片上传保存位置:{}",filesavePath+directory);
//
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
//
//        File newFile = new File(filesavePath + directory + fileName);
//
//        try {
//            file.transferTo(newFile);
//
//            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory+ "/" + fileName;
//            log.info("图片上传，访问url:{}",url);
//            return new EntityResult(ResultStatus.SUCCESS.getCode(),"上传成功！",url);
//        } catch (IOException e) {
////            e.printStackTrace();
//            return new EntityResult("错误","上传失败！");
//        }
//
//    }


    @ApiOperation(value = "图片上传",notes = "图片上传")
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public EntityResult<String> uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images" + fileUpload.newUploadFile(file,filerootPath).getData();
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),url);
    }


}
