package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;

/**
 * @ClassName JobLevelDTO
 * @Description JobLevelDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/9 14:42
 * @Version 1.0
 */
@Data
public class JobLevelBatchVO implements ExcelMsg {

    @ExcelProperty(value = "职级名称")
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
