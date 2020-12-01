
package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 人才标签
 *
 * @author Andy
 * @date 2020-11-25 14:40:54
 */
@Data
@TableName("mp_recruitment_label")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "人才标签")
public class RecruitmentLabel extends Model<RecruitmentLabel> {
private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 标签名称
     */
    @ApiModelProperty(value="标签名称")
    private String name;
    /**
     * 人才ID
     */
    @ApiModelProperty(value="人才ID")
    private Long recruitmentId;
    /**
     * 新建人工号
     */
    @ApiModelProperty(value="新建人工号")
    private String creatorCode;
    /**
     * 新建人
     */
    @ApiModelProperty(value="新建人")
    private String creatorName;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime creatorTime;
    /**
     * 修改人工号
     */
    @ApiModelProperty(value="修改人工号")
    private String modifierCode;
    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private String modifierName;
    /**
     * 修改人时间
     */
    @ApiModelProperty(value="修改人时间")
    private LocalDateTime modifierTime;
    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    private String field1;
    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    private String field2;
    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    private String field3;
    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    private String field4;
    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    private String field5;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value="逻辑删除")
    @TableLogic
    private Boolean delFlag;

}
