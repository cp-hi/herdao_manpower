package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * @ClassName JobLevelPipeline
 * @Description JobLevelPipeline
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/27 10:18
 * @Version 1.0
 */
@Data
@TableName("MP_JOB_LEVEL_CHANNEL")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "职级通道实体类")
public class JobLevelChannel extends BaseEntity<JobLevelChannel> {
    @ApiModelProperty(value = "职级通道名称",required = true)
    private String jobLevelChannelName;
    @ApiModelProperty(value = "职级通道编码" ,required = true)
    private String jobLevelChannelCode;
    @ApiModelProperty(value = "描述"  )
    private String description;
    @ApiModelProperty(value = "排序" ,required = true)
    private String sortNo;
}
