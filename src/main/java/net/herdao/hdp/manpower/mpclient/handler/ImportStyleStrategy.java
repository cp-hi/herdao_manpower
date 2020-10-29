package net.herdao.hdp.manpower.mpclient.handler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import io.swagger.annotations.ApiModel;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @ClassName ImportStyleStrategy
 * @Description ImportStyleStrategy
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/28 17:59
 * @Version 1.0
 */
public class ImportStyleStrategy extends AbstractColumnWidthStyleStrategy {

//    Class templClass;

    //    Map<String, Field> fieldMap;
    String description = null;

    List data = null;
    Field[] fields = null;

    public ImportStyleStrategy(Class clazz) {
//        this.templClass = clazz;
//        fieldMap = new LinkedHashMap<>();
        fields = clazz.getDeclaredFields();
        ApiModel apiModel = (ApiModel) clazz.getAnnotation(ApiModel.class);
        this.description = apiModel.description();
//        for (Field field : fields) {
//            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
//            if (null != excelProperty)
//                fieldMap.put(excelProperty.value()[excelProperty.value().length - 1], field);
//        }
    }

    public ImportStyleStrategy(Class clazz, List data) {
        this(clazz);
        this.data = data;
    }

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> list, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
//            Integer length = cell.getStringCellValue().length();
            if (0 == relativeRowIndex) {
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                cellStyle.setAlignment(HorizontalAlignment.LEFT);
                cellStyle.setWrapText(true);
//                cellStyle.setFillBackgroundColor((short) -1);
                Font font = workbook.createFont();
                font.setFontHeight((short) 200);
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(description);
            } else if (1 == relativeRowIndex) {
//                CellStyle cellStyle = workbook.createCellStyle();
//                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//                cellStyle.setAlignment(HorizontalAlignment.CENTER);
//                cellStyle.setWrapText(true);

//                cellStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//                Font font = workbook.createFont();
//                font.setFontHeight((short) 200);
//                Field field = fieldMap.get(cell.getStringCellValue());
//                if (null != field) {
//                    HeadFontStyle headFontStyle = field.getAnnotation(HeadFontStyle.class);
//                    if (null != headFontStyle) font.setColor(headFontStyle.color());
//                }
//                cellStyle.setFont(font);
//                cell.setCellStyle(cellStyle);
                Integer length = cell.getStringCellValue().length();
                writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), length * 1000);
//
                if (head.getColumnIndex() == (fields.length - 2) && null == data)
                    writeSheetHolder.getSheet().setColumnHidden(0, true);
            }
        } else {
//        TODO    动态插入错误信息
            // 这里已经写完最后一条数据
            if (null != data && data.size() == (relativeRowIndex + 1)
                    && head.getColumnIndex() == (fields.length - 2)) {
//                Row r = writeSheetHolder.getSheet().getRow(0);
//                writeSheetHolder.getSheet().removeRow(r);
            }
        }
    }
}
