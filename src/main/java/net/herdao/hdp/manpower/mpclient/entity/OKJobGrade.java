package net.herdao.hdp.manpower.mpclient.entity;

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
public class OKJobGrade extends JobGrade {
    private Long okJobLevelSysId;
}
