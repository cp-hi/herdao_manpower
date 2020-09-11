package com.hedao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description BaseEntity
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:13
 * @Version 1.0
 */
@Data
public class BaseEntity<T extends Model<?>> extends Model<T> {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    private String oid;
    private String creatorCode;
    private Date createdTime;
    private String modifierCode;
    private Date modifiedTime;
    private Integer tenantId;
}
