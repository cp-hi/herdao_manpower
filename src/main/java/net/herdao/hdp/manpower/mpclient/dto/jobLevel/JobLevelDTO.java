package net.herdao.hdp.manpower.mpclient.dto.jobLevel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;

/**
 * @ClassName JobLevelDTO
 * @Description JobLevelDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 16:11
 * @Version 1.0
 */
@Data
@ApiModel(value = "职级DTO")
public class JobLevelDTO extends JobLevel {
    private Integer onJobStaffs;
    private JobGrade jobGrade;
}
