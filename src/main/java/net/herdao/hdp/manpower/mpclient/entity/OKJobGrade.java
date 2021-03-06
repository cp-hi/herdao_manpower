package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName OKJobGrade
 * @Description  OK = one key  一键职等
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 19:25
 * @Version 1.0
 */
@Data
@TableName("mp_ok_job_grade")
@ApiModel("一键创建职等")
public class OKJobGrade extends JobGrade {
    private Long okJobLevelSysId;
}
