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
public class JobLevelDTO  extends JobLevel{
    @ExcelProperty("集团")
    private String group;
}
