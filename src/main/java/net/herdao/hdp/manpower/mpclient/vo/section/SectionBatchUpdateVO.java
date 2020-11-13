package net.herdao.hdp.manpower.mpclient.vo.section;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName SectionBatchUpdateDTO
 * @Description SectionBatchUpdateDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 12:14
 * @Version 1.0
 */
//版块名称、所属集团、启用状态、排序、备注
@Data
@HeadRowHeight(30)
@ContentRowHeight(20)
@ExcelIgnoreUnannotated
@ApiModel(value = "批量编辑板块", description = "" +
        "导入说明：\r\n" +
        "1、标红字段为必填\r\n" +
        "2、操作导入前请删除示例数据\r\n"+
        "3、是否停用字段只能用 已停用/已启用 来表示\r\n"
)
public class SectionBatchUpdateVO  implements ExcelMsg {
    @HeadFontStyle
    @ExcelProperty({"", "错误信息"})
    private String errMsg;
    @HeadFontStyle(color = 10)
    @ExcelProperty({"", "板块名称"})
    private String sectionName;
    @HeadFontStyle(color = 10)
    @ExcelProperty({"", "所属集团"})
    private String groupName;
    @HeadFontStyle
    @ExcelProperty({"", "是否停用"})
    @DtoField
    private String stop;
    @HeadFontStyle
    @ExcelProperty({"", "排序"})
    private Integer sortNo;
    @HeadFontStyle
    @ExcelProperty({"", "备注"})
    private String remark;

}
