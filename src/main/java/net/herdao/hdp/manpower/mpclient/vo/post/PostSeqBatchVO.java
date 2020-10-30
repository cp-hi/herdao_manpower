package net.herdao.hdp.manpower.mpclient.vo.post;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;

/**
 * @ClassName PostSeqBatchDTO
 * @Description PostSeqBatchDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 17:22
 * @Version 1.0
 */
//岗位序列名称、上级岗位序列、描述
@Data
@HeadRowHeight(30)
@ContentRowHeight(20)
@ExcelIgnoreUnannotated
@ApiModel(value = "批量新增岗位序列", description = "" +
        "导入说明：\r\n" +
        "1、标红字段为必填\r\n" +
        "2、操作导入前请删除示例数据\r\n"
)
public class PostSeqBatchVO implements ExcelMsg {

    @ExcelProperty(value = {"","错误信息"})
    @HeadFontStyle
    private String errMsg;

    @ExcelProperty(value ={"", "岗位序列名称"})
    @HeadFontStyle(color = 10)
    private String postSeqName;

    @ExcelProperty({"", "所属集团"})
    @HeadFontStyle(color = 10)
    private String groupName;

    @ExcelProperty(value ={"", "上级岗位序列"})
    @HeadFontStyle
    private String parentName;

    @ExcelProperty(value = {"","描述"})
    @HeadFontStyle
    private String description;

}
