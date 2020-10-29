package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @ClassName JobLevelDTO
 * @Description JobLevelDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/9 14:42
 * @Version 1.0
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(20)
@ExcelIgnoreUnannotated
@ApiModel(value = "批量导入职级", description = "" +
        "导入说明：\r\n" +
        "1、标红字段为必填\r\n" +
        "2、操作导入前请删除示例数据\r\n"
)
public class JobLevelBatchVO implements ExcelMsg {

    @ExcelProperty(value = {"", "错误信息"})
    @HeadFontStyle
    private String errMsg;

    @ExcelProperty(value = {"", "职级名称"})
    @HeadFontStyle(color = 10)
    private String jobLevelName;

    @ExcelProperty(value = {"", "职等"})
    @HeadFontStyle
    private String jobGrade;

    @ExcelProperty(value = {"", "排序"})
    @HeadFontStyle
    private Integer sortNo;

    @ExcelProperty(value = {"", "描述"})
    @HeadFontStyle
    private String description;

}
