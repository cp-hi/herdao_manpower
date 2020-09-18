package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "职等实体类")
public class JobGrade extends BaseEntity<JobGrade>  {
    private String jobGradeName;
    private String jobGradeCode;
    private String description;
}
