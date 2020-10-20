package net.herdao.hdp.manpower.mpclient.entity.base;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description 基础实体表
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:13
 * @Version 1.0
 */
@ExcelIgnoreUnannotated
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T extends Model<?>> extends BaseModel<T> {
    @ApiModelProperty(value = "主键",hidden = true)
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "创建人ID" ,hidden = true)
    private Long creatorId;
    @ApiModelProperty(value = "创建人名称" ,hidden = true)
    private String creatorName;
    @ApiModelProperty(value = "创建时间" ,hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime;
    @ApiModelProperty(value = "修改ID" ,hidden = true)
    private Long modifierId;
    @ApiModelProperty(value = "修改人名称" ,hidden = true)
    private String modifierName;
    @ApiModelProperty(value = "修改时间" ,hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;

    @ApiModelProperty(value = "额外信息 对应日志表的extraKey" ,hidden = true)
    @TableField(exist = false)
    private String extraKey;

    @ApiModelProperty(value = "模块名" ,hidden = true)
    @TableField(exist = false)
    private String module;
}
