package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "职等")
public class JobGrade extends BaseEntity<JobGrade> {
    @ApiModelProperty(value = "职等名称")
    private String jobGradeName;
    @ApiModelProperty(value = "职等编码", hidden = true)
    private String jobGradeCode;
    @ApiModelProperty(value = "描述", hidden = true)
    private String description;
    @ApiModelProperty(value = "集团id",required = true)
    private Long groupId;
    @ApiModelProperty(value = "职等编码", hidden = true)
    private Integer sortNo;
}
