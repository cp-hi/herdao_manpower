package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import net.herdao.hdp.mpclient.entity.base.BaseModel;
import net.herdao.hdp.sys.annotation.ExcludeField;

/**
 * @ClassName Pipeline
 * @Description Pipeline
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 14:55
 * @Version 1.0
 */
//@ExcludeField(value = {"tenantId"})
@Data
@TableName("MP_Pipeline")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "管线")
public class Pipeline extends BaseModel<Pipeline> {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    private String pipelineCode;
    private String pipelineName;
    private Integer groupId;
    private Integer sortNo;
    @TableField("IS_STOP")
    private Boolean stop;
    private String pipelineStdCode;

    @TableField(exist = false)
    private Group group;
}
