package com.hedao.hdp.ehrclient.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @ClassName BaseEntity
 * @Description BaseEntity
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:13
 * @Version 1.0
 */

public class BaseEntity<T extends Model<?>> extends Model<T> {

    private Integer id;

    private Integer tenantId;
}
