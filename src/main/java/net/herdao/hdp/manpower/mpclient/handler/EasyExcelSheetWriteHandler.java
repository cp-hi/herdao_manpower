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
 * 组织批量新增、编辑 Handler
 *
 * @author shuling
 * @date 2020-10-27 14:37:22
 */
public class EasyExcelSheetWriteHandler implements SheetWriteHandler{
	
	int lastCol = 0;
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
        Sheet sheet = workbook.getSheetAt(0);
        Row remarksRow = sheet.createRow(0);
        // 设置高度
        remarksRow.setHeight((short) 2000);
        // 设置 cell 样式
        Cell remarksCell = remarksRow.createCell(0);
        remarksCell.setCellValue(remarks);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setWrapText(true);
        Font font = workbook.createFont();
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        remarksCell.setCellStyle(cellStyle);
        // 设置合并坐标（默认第一行）
        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, lastCol));
    }

}
