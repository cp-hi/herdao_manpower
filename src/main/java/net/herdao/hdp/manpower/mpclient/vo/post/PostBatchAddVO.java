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
 * @ClassName PostBatchAddDTO
 * @Description PostBatchAddDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/20 20:44
 * @Version 1.0
 */

//    岗位名称、所属集团、所属板块、所属管线、岗位类别、职级、备注

@Data
@HeadRowHeight(30)
@ContentRowHeight(20)
@ExcelIgnoreUnannotated
@ApiModel(value = "批量新增岗位", description = "" +
        "导入说明：\r\n" +
        "1、标红字段为必填\r\n" +
        "2、操作导入前请删除示例数据\r\n"
)
public class PostBatchAddVO implements ExcelMsg {

    @ExcelProperty(value = "错误信息")
    private String errMsg;

    @ExcelProperty({"", "岗位名称"})
    @HeadFontStyle(color = 10)
    private String postName;

    @ExcelProperty({"", "所属集团"})
    @HeadFontStyle(color = 10)
    private String groupName;

    @ExcelProperty({"", "所属板块"})
    private String sectionName;

    @ExcelProperty({"", "所属管线"})
    private String pipelineName;

    @ExcelProperty({"", "岗位序列"})
    private String postSeqName;

    @ExcelProperty({"", "职级"})
    private String jobLevelName;

    @ExcelProperty({"", "备注"})
    private String description;

}
