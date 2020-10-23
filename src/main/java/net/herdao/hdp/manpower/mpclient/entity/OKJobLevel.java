package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName OKJobLevel
 * @Description  OK = one key 一键创建职级
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 19:24
 * @Version 1.0
 */
@Data
@TableName("mp_ok_job_level")
@ApiModel("一键创建职级")
public class OKJobLevel extends JobLevel {
    private Long ok_job_grade_id;
}
