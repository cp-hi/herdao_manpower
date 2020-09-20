package net.herdao.hdp.mpclient.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

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
@ToString
public class BaseEntity<T extends Model<?>> extends Model<T> {
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    private Long creatorId;
    private String creatorName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;
    private Long modifierId;
    private String modifierName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date modifiedTime;
    private Integer tenantId;

    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;

    private Boolean delFlag;
}
