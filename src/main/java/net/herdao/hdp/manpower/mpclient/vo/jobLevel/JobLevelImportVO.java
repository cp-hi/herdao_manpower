package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelVO;

/**
 * @ClassName JobLevelDTO
 * @Description JobLevelDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/9 14:42
 * @Version 1.0
 */
@Data
public class JobLevelImportVO implements ExcelVO {

    @ExcelProperty(value = "职级名称", index = 0)
    private String jobLevelName;

    @ExcelProperty(value = "职等", index = 1)
    private String jobGrade;

    @ExcelProperty(value = "排序", index = 2)
     private Integer sortNo;

    @ExcelProperty(value = "描述", index = 3)
    private String description;

    @ExcelProperty(value = "错误信息", index = 4)
    private String errMsg;
}
