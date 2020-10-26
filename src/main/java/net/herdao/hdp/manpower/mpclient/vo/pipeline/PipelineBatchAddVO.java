package net.herdao.hdp.manpower.mpclient.vo.pipeline;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;

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
@ExcelIgnoreUnannotated
public class PipelineBatchAddVO implements ExcelErrMsg {

    @ExcelProperty("管线名称")
    private String pipelineName;

    @ExcelProperty("所属集团")
    private String groupName;

    @ExcelProperty("排序")
    private Integer sortNo;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("错误信息")
    private String errMsg;
}
