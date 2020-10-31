package net.herdao.hdp.manpower.mpclient.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.manpower.mpclient.handler.ImportStyleStrategy;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelBatchVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Excel工具类
 */
@Slf4j
public class ExcelUtils {

    /**
     * 导出Excel(07版.xlsx)到指定路径下
     *
     * @param path      路径
     * @param excelName Excel名称
     * @param sheetName sheet页名称
     * @param clazz     Excel要转换的类型
     * @param data      要导出的数据
     */
    public static void export2File(String path, String excelName, String sheetName, Class clazz, List data) {
        String fileName = path.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(fileName, clazz).sheet(sheetName).doWrite(data);
    }

    /**
     * 导出Excel(07版.xlsx)到web
     *
     * @param response  响应
     * @param excelName Excel名称
     * @param sheetName sheet页名称
     * @param clazz     Excel要转换的类型
     * @param data      要导出的数据
     * @throws Exception
     */
    public static void export2Web(HttpServletResponse response, String excelName, String sheetName, Class clazz, List data) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());
        //response.setHeader("Content-disposition", "attachment;filename=" + new String(excelName.getBytes("utf-8"),"ISO-8859-1" ) + ExcelTypeEnum.XLSX.getValue());


        //内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);
        // 字体策略
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 10);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.TOP);
//        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
//        headWriteCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
//        WriteFont writeFont = new WriteFont();
//        writeFont.setFontHeightInPoints((short) 10);
//        headWriteCellStyle.setWriteFont(writeFont);
        headWriteCellStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
            new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .registerWriteHandler(new ImportStyleStrategy(clazz))
                .doWrite(data);
    }

//    public static void export2Web(HttpServletResponse response, String excelName, List<LinkedHashMap<String, String>> data) throws Exception {
//        List<List<Object>> dataList = new ArrayList<>();
//        List<List<String>> headList = new ArrayList<>();
//        for (String k : data.get(0).keySet()) {
//            List<String> head = new ArrayList<>();
//            head.add(k);
//            headList.add(head);
//        }
//        for (LinkedHashMap<String, String> map : data) {
//            List<Object> objs = new ArrayList<>();
//            for (List<String> h : headList) {
//                objs.add(map.get(h.get(0)));
//            }
//            dataList.add(objs);
//        }
//
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        excelName = URLEncoder.encode(excelName, "UTF-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());
//        EasyExcel.write(response.getOutputStream()).sheet(excelName).head(headList).doWrite(dataList);
//    }

    public static void downloadTempl(HttpServletResponse response, Class templClass) throws Exception {
        ApiModel apiModel = (ApiModel) templClass.getAnnotation(ApiModel.class);

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String excelName = URLEncoder.encode(apiModel.value(), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());

        //内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);
        // 字体策略
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 14);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.TOP);
//        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
//        headWriteCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
        WriteFont writeFont = new WriteFont();
        writeFont.setFontHeightInPoints((short) 10);
        headWriteCellStyle.setWriteFont(writeFont);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        List<String > excupt = new ArrayList<>();
        excupt.add("errMsg");
        EasyExcel.write(response.getOutputStream()).sheet(excelName).head(templClass)
                .excludeColumnFiledNames(excupt)
                .registerWriteHandler(horizontalCellStyleStrategy)
//                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(new ImportStyleStrategy(templClass))

                .doWrite(null);
    }

    /**
     * 将指定位置指定名称的Excel导出到web
     *
     * @param response  响应
     * @param path      文件路径
     * @param excelName 文件名称
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String export2Web4File(HttpServletResponse response, String path, String excelName) throws UnsupportedEncodingException {
        File file = new File(path.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue()));
        if (!file.exists()) {
            return "文件不存在！";
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());

        try (
                FileInputStream in = new FileInputStream(file);
                ServletOutputStream out = response.getOutputStream();
        ) {
            IOUtils.copy(in, out);
            return "导出成功！";
        } catch (Exception e) {
            log.error("导出文件异常：", e);
        }

        return "导出失败！";
    }

}
