package net.herdao.hdp.manpower.mpclient.handler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
/**
 * 批量新增、编辑 Handler
 *
 * @author shuling
 * @date 2020-10-27 14:37:22
 */
public class EasyExcelSheetWriteHandler implements SheetWriteHandler{
	// sheet 大小 （sheet从0开始） 特殊规则请单独编写 handler
	int sheetSize = 1;
	// 备注开始行
	int remarksRowIndex = 0;
	// 备注行高度
	int remarksRowHeight = 2000;
	// 单元格
	int remarksCellIndex = 0;
	// 最后列
	int lastCol = 0;
	// font 高度
	int fontHeight = 200;
	// 备注信息
	String remarks = "";
	
	public EasyExcelSheetWriteHandler() {}
	
	public EasyExcelSheetWriteHandler(Integer lastCol, String remarks) {
		this.lastCol = lastCol;
		this.remarks = remarks;
	}

	@Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
 
    }
 
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        // 设置批量新增、编辑备注信息
        for(int i=0; i<sheetSize; i++) {
        	 Sheet sheet = workbook.getSheetAt(i);
             Row remarksRow = sheet.createRow(remarksRowIndex);
             // 设置高度
             remarksRow.setHeight((short) remarksRowHeight);
             // 设置 cell 样式
             Cell remarksCell = remarksRow.createCell(remarksCellIndex);
             remarksCell.setCellValue(remarks);
             CellStyle cellStyle = workbook.createCellStyle();
             cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
             cellStyle.setAlignment(HorizontalAlignment.LEFT);
             cellStyle.setWrapText(true);
             Font font = workbook.createFont();
             font.setFontHeight((short) fontHeight);
             cellStyle.setFont(font);
             remarksCell.setCellStyle(cellStyle);
             // 设置合并坐标（默认第一行）
             sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, lastCol));
        }
    }
}
