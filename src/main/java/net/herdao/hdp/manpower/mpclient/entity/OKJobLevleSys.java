package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * @ClassName OKJobLevle
 * @Description OK = one key 一键创建职级体系
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 19:22
 * @Version 1.0
 */
@Data
@TableName("mp_ok_job_level_sys")
public class OKJobLevleSys extends BaseEntity<OKJobLevleSys> {
    private Long id;
    private String title;
    private String description;
}
