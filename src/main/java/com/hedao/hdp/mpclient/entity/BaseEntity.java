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
//    @TableField("OID")
    private String oid;
//    @TableField("CREATOR_CODE")
    private String creatorCode;
//    @TableField("CREATED_TIME")
    private Date createdTime;
//    @TableField("MODIFIER_CODE")
    private String modifierCode;
//    @TableField("MODIFIED_TIME")
    private Date modifiedTime;
//    @TableField("TENANT_ID")
    private Integer tenantId;
}
