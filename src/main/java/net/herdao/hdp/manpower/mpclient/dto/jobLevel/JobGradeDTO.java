package net.herdao.hdp.manpower.mpclient.dto.jobLevel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;

/**
 * @ClassName JobGradeDTO
 * @Description JobGradeDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 18:06
 * @Version 1.0
 */
@Data
@ApiModel(value = "职等DTO")
public class JobGradeDTO extends JobGrade {
    private Integer onJobStaffs;
    private Integer jobLevels;
}
