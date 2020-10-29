package net.herdao.hdp.manpower.mpclient.vo.pipeline;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;

/**
 * @ClassName PipelineBatchAddDTO
 * @Description PipelineBatchAddDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 14:53
 * @Version 1.0
 */


//管线名称、所属集团、排序、备注
@Data
@HeadRowHeight(30)
@ContentRowHeight(20)
@ExcelIgnoreUnannotated
@ApiModel(value = "批量新增管线", description = "" +
        "导入说明：\r\n" +
        "1、标红字段为必填\r\n" +
        "2、操作导入前请删除示例数据\r\n"
)
public class PipelineBatchAddVO implements ExcelMsg {

    @ExcelProperty("错误信息")
    private String errMsg;

    @ExcelProperty({"", "管线名称"})
    private String pipelineName;

    @ExcelProperty({"", "所属集团"})
    private String groupName;

    @ExcelProperty({"", "排序"})
    private Integer sortNo;

    @ExcelProperty({"", "备注"})
    private String remark;

}
