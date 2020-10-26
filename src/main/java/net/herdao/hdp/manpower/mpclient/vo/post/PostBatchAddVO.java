package net.herdao.hdp.manpower.mpclient.vo.post;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;

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
@ExcelIgnoreUnannotated
public class PostBatchAddVO implements ExcelErrMsg {

    @ExcelProperty(value = "岗位名称")
    private String postName;

    @ExcelProperty(value = "所属集团")
    private String groupName;

    @ExcelProperty(value = "所属板块")
    private String sectionName;

    @ExcelProperty(value = "所属管线")
    private String pipelineName;

    @ExcelProperty(value = "岗位序列")
    private String postSeqName;

    @ExcelProperty(value = "职级")
    private String jobLevelName;

    @ExcelProperty(value = "备注")
    private String description;

    @ExcelProperty(value = "错误信息")
    private String errMsg;
}
