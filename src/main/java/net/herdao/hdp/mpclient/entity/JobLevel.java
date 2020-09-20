package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.mpclient.entity.base.BaseEntity;

/**
 * @ClassName JobLevel
 * @Description JobLevel
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 18:55
 * @Version 1.0
 */

@Data
@TableName("MP_JOB_LEVEL")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "职级实体类")
public class JobLevel extends BaseEntity<JobLevel> {
    private String jobLevelName;
    private String jobLevelCode;
    private String description;
}
