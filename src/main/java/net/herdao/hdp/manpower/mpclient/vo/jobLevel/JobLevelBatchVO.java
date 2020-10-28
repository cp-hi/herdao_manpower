package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

import com.alibaba.excel.annotation.ExcelProperty;
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
@HeadRowHeight(100)
//@ContentRowHeight(20)
@ApiModel(value = "批量新增职级模板",description = "" +
        "导入说明：\r\n" +
        "1、标红字段为必填\r\n"  +
        "2、操作导入前请删除示例数据\r\n"  +
        "3、上级组织名称请填写已启用的组织编码\r\n"
)
public class JobLevelBatchVO implements ExcelMsg {

    @ExcelProperty(value = "职级名称")
    @HeadFontStyle(color = 10)
    private String jobLevelName;

    @ExcelProperty(value = "职等")
    private String jobGrade;

    @ExcelProperty(value = "排序")
    private Integer sortNo;

    @ExcelProperty(value = "描述")
    private String description;

    @ExcelProperty(value = "错误信息")
    private String errMsg;
}
