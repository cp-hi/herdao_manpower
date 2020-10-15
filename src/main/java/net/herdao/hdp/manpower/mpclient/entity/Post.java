package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @ClassName Post
 * @Description Post
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/10 19:20
 * @Version 1.0
 */

@Data
@TableName("mp_post")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "岗位实体类")
public class Post extends BaseEntity<Post> {
    @ApiModelProperty(value = "岗位编码")
    private String postCode;
    @ApiModelProperty(value = "岗位名称", required = true)
    private String postName;
    @ApiModelProperty(value = "管线ID", required = true)
    private Long pipelineId;
    @ApiModelProperty(value = "岗位级别", required = true)
    private String postLevel;
    @ApiModelProperty(value = "福利级别", required = true)
    private String welfareLevel;
    @ApiModelProperty(value = "行政级别", required = true)
    private String administrativeLevel;
    @ApiModelProperty(value = "年终奖薪酬比例", required = true)
    private BigDecimal yearPayRatio;
    @ApiModelProperty(value = "是否销售岗位", required = true)
    private Boolean salesPosition;
    @ApiModelProperty(value = "是否销售管理岗位", required = true)
    private Boolean salesManager;
    @ApiModelProperty(value = "排序")
    private Integer sortNo;
    @ApiModelProperty(value = "是否停用", required = true)
    @TableField("IS_STOP")
    private Boolean stop;
    @ApiModelProperty(value = "组织类型", required = true)
    private String orgType;
    @ApiModelProperty(value = "板块id", required = true)
    private String sectionId;
    @ApiModelProperty(value = "岗位职责", required = true)
    private String postDescr;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "岗位性质")
    private String postProperties;
    @ApiModelProperty(value = "绩效工资比例")
    private String perforSalaryRatio;
    @ApiModelProperty(value = "职级1")
    private Long jobLevelId1;
    @ApiModelProperty(value = "职级2")
    private Long jobLevelId2;
    @ApiModelProperty(value = "岗位序列")
    private Long postSeqId;
    @ApiModelProperty(value = "岗位族")
    private String postClan;
    @ApiModelProperty(value = "职等名称")
    private String gradeName;
    @ApiModelProperty(value = "集团ID")
    private Long groupId;
}
