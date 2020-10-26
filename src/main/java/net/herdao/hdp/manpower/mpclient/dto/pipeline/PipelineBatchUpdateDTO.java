package net.herdao.hdp.manpower.mpclient.dto.pipeline;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;

/**
 * @ClassName PipelineBatchUpdateDTO
 * @Description PipelineBatchUpdateDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 15:06
 * @Version 1.0
 */
@Data
@ExcelIgnoreUnannotated
public class PipelineBatchUpdateDTO extends PipelineBatchAddDTO implements ExcelErrMsg {

    @ExcelProperty("管线名称")
    private String pipelineName;

    @ExcelProperty("所属集团")
    private String groupName;

    @ExcelProperty("是否停用")
    private String stop;

    @ExcelProperty("排序")
    private Integer sortNo;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("错误信息")
    private String errMsg;
}
