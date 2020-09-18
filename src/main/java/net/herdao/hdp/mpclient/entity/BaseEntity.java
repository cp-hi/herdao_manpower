package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description 基础实体表
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:13
 * @Version 1.0
 */
@Data
public class BaseEntity<T extends Model<?>> extends Model<T> {
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    private Long creatorId;
    private String creatorCode;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;
    private Long modifierId;
    private String modifierCode;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date modifiedTime;
    private Integer tenantId;
}
