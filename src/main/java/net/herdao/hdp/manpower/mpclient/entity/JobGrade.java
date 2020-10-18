package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * @ClassName JobGrade
 * @Description 职等实体类
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 18:55
 * @Version 1.0
 */
@Data
@TableName("MP_JOB_GRADE")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "职等")
public class JobGrade extends BaseEntity<JobGrade> {
    public JobGrade(Long id) {
        this.setId(id);
    }
    private String jobGradeName;
    private String jobGradeCode;
    private String description;
    private Long groupId;
}
