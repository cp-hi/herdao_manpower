package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author Liu Chang
 * @Date 2020/11/28 4:43 下午
 */
@Data
@TableName("mp_staff_promote_approve")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "晋升/降职")
public class StaffPromoteApprove extends BaseModel<StaffPromoteApprove> {
    @ApiModelProperty(value = "晋升 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String oid;

    @ApiModelProperty(value = "员工 id")
    @NotNull
    private Long userId;

    private String userOid;

    @ApiModelProperty(value = "晋升前组织 id")
    @NotNull
    private Long nowOrgId;

    private String nowOrgOid;

    @ApiModelProperty(value = "变更前岗位 id")
    @NotNull
    private Long nowPostId;

    private String nowPostOid;

    @ApiModelProperty(value = "变更前职级 id")
    private Long nowJobLevelId;

    @ApiModelProperty(value = "原岗位入职日期")
    @NotNull
    private LocalDateTime nowStartDate;

    @ApiModelProperty(value = "变更后组织 id")
    @NotNull
    private Long promoteOrgId;

    private String promoteOrgOid;

    @ApiModelProperty(value = "变更后岗位 id")
    @NotNull
    private Long promotePostId;

    private String promotePostOid;

    @ApiModelProperty(value = "变更后职级 id")
    private Long promoteJobLevelId;

    @ApiModelProperty(value = "生效日期")
    @NotNull
    private LocalDateTime promoteDate;

    @ApiModelProperty(value = "考察期满日期")
    @NotNull
    private LocalDateTime expireDate;

    @ApiModelProperty(value = "晋升前总收入")
    private Double previousIncome;

    @ApiModelProperty(value = "其它补贴")
    private Double otherSubsides;

    @ApiModelProperty(value = "年度岗位编制")
    private Integer yearPostPrepareCount;

    @ApiModelProperty(value = "月度岗位编制")
    private Integer monthPostPrepareCount;

    @ApiModelProperty(value = "已到岗人数")
    private Integer postHasCount;

    @ApiModelProperty(value = "经办人意见")
    private String remark;

    @ApiModelProperty(value = "培训经历")
    private String training;

    @ApiModelProperty(value = "考核评分")
    private String proExamScore;

    @ApiModelProperty(value = "述职报告及新职位工作规划")
    private String jobReportPlan;

    @ApiModelProperty(value = "是否编制置换")
    private Boolean isPrepareChange;

    @ApiModelProperty(value = "现年度岗位编制")
    private Integer nowYearPostPrepareCount;

    @ApiModelProperty(value = "现月度岗位编制")
    private Integer nowMonthPostPrepareCount;

    @ApiModelProperty(value = "现已到岗人数")
    private Integer nowPostHasCount;

    @ApiModelProperty(value = "新建人工号")
    @TableField(value = "creator_code", fill = FieldFill.INSERT)
    private String creatorCode;

    @ApiModelProperty(value = "新建人")
    @TableField(value = "creator_name", fill = FieldFill.INSERT)
    private String creatorName;

    @ApiModelProperty(value = "新建时间")
    @TableField(value = "creator_time", fill = FieldFill.INSERT)
    private LocalDateTime creatorTime;

    @ApiModelProperty(value = "修改人工号")
    @TableField(value = "modifier_code", fill = FieldFill.UPDATE)
    private String modifierCode;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "modifier_name", fill = FieldFill.UPDATE)
    private String modifierName;

    @ApiModelProperty(value = "修改人时间")
    @TableField(value = "modifier_time", fill = FieldFill.UPDATE)
    private LocalDateTime modifierTime;

    @ApiModelProperty(value = "状态：FILLING_IN(填报中) APPROVING(审批中) APPROVED(一审批) EXECUTED(已执行) 四种")
    private String status;

    @ApiModelProperty(value = "租户 id")
    private Long tenantId;

    @ApiModelProperty(value = "岗位-组织关系 id")
    private Long postOrgId;

    @ApiModelProperty(value = "晋升类型：1-晋升，2-降级")
    private Integer approveType;

    @TableLogic
    private Boolean delFlag;
}
