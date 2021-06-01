package com.goodmap.hospital.common.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.goodmap.hospital.common.utils.excelUtils.CustomCellWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/11/19 time
 * @Description
 **/
@Slf4j
public class ExportExcel {
    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }
    /**
     * 生成excle
     * @param fileName 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     */
    public static void writeWithTemplate(String fileName, List<? extends BaseRowModel> data, HttpServletResponse response){
        writeWithTemplateAndSheet(fileName,data,null,response);
    }

    /**
     * 生成excle
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     * @param sheet excle页面样式
     */
    public static void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet, HttpServletResponse response){
        if(CollectionUtils.isEmpty(data)){
            return;
        }

        sheet = (sheet != null) ? sheet : initSheet;
        sheet.setClazz(data.get(0).getClass());

        OutputStream out = null;
        ExcelWriter writer = null;
        try {
            out = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(out);
            writer.write(data,sheet);
            writer.finish();
            out.flush();
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }
    /**
     * 导出数据为Excel文件
     * @param response  响应实体
     * @param data  导出数据
     * @param fileName 文件名
     * @param sheetName 单元格名
     * @param clazz  定义excel导出的实体
     * @throws IOException
     */
    public static void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> data, String fileName, String sheetName, Class clazz) throws IOException {
        //防止中文乱码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止导入excel文件名中文不乱码
        response.setHeader("Content-disposition", "attachment;fileName=" + fileName + ".xlsx" + ";fileName*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).registerWriteHandler(new CustomCellWriteHandler()).doWrite(data);
    }
    /**
     * 默认excel文件名和单元sheet名一样的 Excel文件导出
     * @param httpServletResponse
     * @param data
     * @param fileName
     * @param clazz
     * @throws IOException
     */
    public static void writeExcel(HttpServletResponse httpServletResponse, List<? extends BaseRowModel> data, String fileName, Class clazz) throws IOException {
        writeExcel(httpServletResponse, data, fileName, fileName, clazz);
    }

}
