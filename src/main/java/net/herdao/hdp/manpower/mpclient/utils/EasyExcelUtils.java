package net.herdao.hdp.manpower.mpclient.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;

import cn.hutool.core.util.ObjectUtil;

/**
 * @description: easyExcel工具类
 * 
 * @author shuling
 * @date 2020-10-20 11:37:27
 */
@SuppressWarnings("rawtypes")
public class EasyExcelUtils {

    private EasyExcelUtils(){}

   
	public static void webWriteExcel(HttpServletResponse response, List objects, Class clazz, String fileName) throws IOException {
        String sheetName = fileName;
        webWriteExcel(response,objects,clazz,fileName,sheetName, null);
    }
	
	public static void webWriteExcel(HttpServletResponse response, List objects, Class clazz, String fileName, WriteHandler handler) throws IOException {
        String sheetName = fileName;
        webWriteExcel(response,objects,clazz,fileName,sheetName, handler);
    }
    
	public static void webWriteExcel(HttpServletResponse response, List objects, Class clazz, String fileName, String sheetName, WriteHandler handler) throws IOException {
		
        fileName = URLEncoder.encode(fileName, "UTF-8");
        
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ExcelTypeEnum.XLSX.getValue());
        
        //内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);
        // 字体策略
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 14);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            if(ObjectUtil.isNotNull(handler)) {
            	EasyExcel.write(outputStream, clazz)
            	.registerWriteHandler(handler)
            	.registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle)).useDefaultStyle(true).relativeHeadRowIndex(1).sheet(sheetName).doWrite(objects);
            }else {
            	EasyExcel.write(outputStream, clazz)
            	.registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle)).sheet(sheetName).doWrite(objects);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            outputStream.close();
        }
    }

}
