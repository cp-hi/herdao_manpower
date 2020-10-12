package net.herdao.hdp.manpower.mpclient.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;

/**
 * @ClassName JobLevelDTO
 * @Description JobLevelDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/9 14:42
 * @Version 1.0
 */
@Data
public class JobLevelDTO  extends JobLevel implements ExcelDTO{
    @ExcelProperty("职等")
    private String jobGrade;

    @ExcelProperty(value = "错误信息",index = 4)
    private String errMsg;
}
