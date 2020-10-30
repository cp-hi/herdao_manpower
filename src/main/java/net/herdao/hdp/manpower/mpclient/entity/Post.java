package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "岗位")
public class Post extends BaseEntity<Post> {
    public Post(Long id){setId(id);}

    @ApiModelProperty(value = "岗位编码",hidden = true)
    private String postCode;
    @ApiModelProperty(value = "岗位名称", required = true)
    private String postName;
    @ApiModelProperty(value = "管线ID" )
    private Long pipelineId;
    @ApiModelProperty(value = "岗位薪酬级别",hidden = true)
    private String postLevel;
    @ApiModelProperty(value = "福利级别",hidden = true)
    private String welfareLevel;
    @ApiModelProperty(value = "行政级别",hidden = true)
    private String administrativeLevel;
    @ApiModelProperty(value = "年终奖比例",hidden = true)
    private String yearPayRatio;
    @ApiModelProperty(value = "是否销售岗位",hidden = true)
    private Boolean salesPosition;
    @ApiModelProperty(value = "是否销售管理岗位",hidden = true)
    private Boolean salesManager;
    @ApiModelProperty(value = "排序",hidden = true)
    private Integer sortNo;
    @ApiModelProperty(value = "是否停用")
    @TableField("IS_STOP")
    private Boolean stop;
    @ApiModelProperty(value = "停用日期",hidden = true)
    private Date stopDate;
    @ApiModelProperty(value = "启用日期",hidden = true)
    private Date startDate;
    @ApiModelProperty(value = "岗位组织类型" ,hidden = true)
    private String orgType;
    @ApiModelProperty(value = "板块id" )
    private Long sectionId;
    @ApiModelProperty(value = "岗位职责",hidden = true)
    private String postDescr;
    @ApiModelProperty(value = "备注",hidden = true)
    private String remark;
    @ApiModelProperty(value = "岗位性质",hidden = true)
    private String postProperties;
    @ApiModelProperty(value = "绩效工资比例",hidden = true)
    private String perforSalaryRatio;

    @ApiModelProperty(value = "职级1")
    private Long jobLevelId1;
    @ApiModelProperty(value = "职级2",hidden = true)
    private Long jobLevelId2;
    @ApiModelProperty(value = "岗位序列",hidden = true)
    private Long postSeqId;
    @ApiModelProperty(value = "岗位族",hidden = true)
    private String postClan;
    @ApiModelProperty(value = "职等名称",hidden = true)
    private String gradeName;
    @ApiModelProperty(value = "集团ID",required = true)
    private Long groupId;

    @ApiModelProperty(value = "是否单职级",hidden = true)
    private Boolean singleJobLevle;
}
