package com.goodmap.hospital.common.utils;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class FileUpload {

    public void delFile(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
     
    /**
     * 文件上传处理
     *
     * @param file
     * @return
     */
	
    public String writeUploadFile(MultipartFile file, String imgrootPath) {
        String filename = file.getOriginalFilename();
        String realpath = imgrootPath +"/";
        File fileDir = new File(realpath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }
 
        String extname = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png,pdf";
        if (!allowImgFormat.contains(extname.toLowerCase())) {
            return "NOT_IMAGE";
        }
         
        filename = Math.abs(file.getOriginalFilename().hashCode()) + RandomUtils.createRandomString( 4 ) + "." + extname;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(realpath + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return "/" + filename;
    }

    /**
     * 文件上传 新
     *
     * @param file
     * @param imgrootPath
     * @return
     */
    public EntityResult<String> newUploadFile(MultipartFile file, String imgrootPath) {
        String filename = file.getOriginalFilename();
        String realpath = imgrootPath + "/";
        File fileDir = new File(realpath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String extname = FilenameUtils.getExtension(filename);
        filename = Math.abs(file.getOriginalFilename().hashCode()) + RandomUtils.createRandomString(4) + "." + extname;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(realpath + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(), "上传成功", "/" + filename);
    }
}

class RandomUtils {
	 
    private static final String charlist = "0123456789";
 
    public static String createRandomString(int len) {
        String str = "";
        for (int i = 0; i < len; i++) {
            str += charlist.charAt(getRandom(charlist.length()));
        }
        return str;
    }
 
    public static int getRandom(int mod) {
        if (mod < 1) {
            return 0;
        }
        int ret = getInt() % mod;
        return ret;
    }
 
    private static int getInt() {
        int ret = Math.abs(Long.valueOf(getRandomNumString()).intValue());
        return ret;
    }
    private static String getRandomNumString() {
        double d = Math.random();
        String dStr = String.valueOf(d).replaceAll("[^\\d]", "");
        if (dStr.length() > 1) {
            dStr = dStr.substring(0, dStr.length() - 1);
        }
        return dStr;
    }
}
