package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

/**
 * @ClassName Pipeline
 * @Description Pipeline
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 14:55
 * @Version 1.0
 */

@Data
@TableName("MP_Pipeline")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "管线")
public class Pipeline extends BaseEntity<Pipeline> {
    @ApiModelProperty(value = "管线编码",hidden = true)
    private String pipelineCode;
    @ApiModelProperty(value = "管线名称" )
    private String pipelineName;
    @ApiModelProperty(value = "集团id",required = true)
    private Long groupId;
    @ApiModelProperty(value = "排序",hidden = true)
    private Integer sortNo;
    @ApiModelProperty(value = "是否停用",hidden = true)
    @TableField("IS_STOP")
    private Boolean stop;
    @ApiModelProperty(value = "管线标准码",hidden = true)
    private String pipelineStdCode;
}
