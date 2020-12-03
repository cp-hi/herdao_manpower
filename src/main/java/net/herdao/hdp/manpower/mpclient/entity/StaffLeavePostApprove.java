package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author Liu Chang
 * @Date 2020/12/1 11:34 上午
 */
@Data
@TableName("mp_staff_promote_approve")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "离职")
public class StaffLeavePostApprove extends BaseEntity<StaffLeavePostApprove> {

    @ApiModelProperty(value = "离职 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String oid;

    @ApiModelProperty(value = "员工 id")
    private Long userId;

    private String userOid;

    @ApiModelProperty(value = "状态")
    @NotNull
    private String status;

    @ApiModelProperty(value = "原就职部门 id")
    @NotNull
    private Long nowOrgId;

    private String nowOrgOid;

    @ApiModelProperty(value = " 原就职岗位 id")
    @NotNull
    private Long nowPostId;

    private String nowPostOid;

    @ApiModelProperty(value = "用人部门意见")
    private String eeptRemark;

    @ApiModelProperty(value = "人力资源部门意见")
    private String hrDeptRemark;

    @ApiModelProperty(value = "公司领导意见")
    private String leadRemark;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "离职类型")
    private String leaveType;

    @ApiModelProperty(value = "离职原因")
    private String leaveReason;

    @ApiModelProperty(value = "离职日期")
    private LocalDateTime leaveTime;

    @ApiModelProperty(value = "离职申请日期")
    private LocalDateTime leaveApplicationTime;

    @ApiModelProperty(value = "员工填报状态")
    private String approveStatus;

    @ApiModelProperty(value = "面谈时间")
    private LocalDateTime interviewsTime;

    @ApiModelProperty(value = "面谈地点")
    private String interviewsPlace;

    @ApiModelProperty(value = "入职引导人")
    private String entryLeader;

    @ApiModelProperty(value = "面谈主持人")
    private String interviewsHost;

    @ApiModelProperty(value = "面谈参与人")
    private String interviewsParticipants;

    @ApiModelProperty(value = "记录人")
    private String recordMan;

    @ApiModelProperty(value = "离职所属期")
    private String leaveBelongsPeriod;

    @ApiModelProperty(value = "离职问题1回答")
    private String leaveQuestionAns1;

    @ApiModelProperty(value = "离职问题2回答")
    private String leaveQuestionAns2;

    @ApiModelProperty(value = "离职问题3回答")
    private String leaveQuestionAns3;

    @ApiModelProperty(value = "离职问题4回答")
    private String leaveQuestionAns4;

    @ApiModelProperty(value = "离职问题5回答")
    private String leaveQuestionAns5;

    @ApiModelProperty(value = "离职问题6回答")
    private String leaveQuestionAns6;

    @ApiModelProperty(value = "离职问题7回答")
    private String leaveQuestionAns7;

    @ApiModelProperty(value = "离职问题8回答")
    private String leaveQuestionAns8;

    @ApiModelProperty(value = "离职问题其他说明")
    private String leaveQuestionAnsOther;

    @ApiModelProperty(value = "改善建议")
    private String improveProposal;

    @ApiModelProperty(value = "面谈总结")
    private String interviewSummary;

    @ApiModelProperty(value = "是否任免")
    private Boolean isAppoint;

    @ApiModelProperty(value = " 是否离任审计")
    private Boolean isLeaveAuditing;

    @ApiModelProperty(value = "离职交接人id")
    private Long userAgentId;

    private String userAgentOid;

    @ApiModelProperty(value = "离职单类型")
    private String leavePostType;

    @ApiModelProperty(value = "新建人工号")
    private String creatorCode;

    @ApiModelProperty(value = "新建人")
    private String creatorName;

    @ApiModelProperty(value = "新建时间")
    private LocalDateTime creatorTime;

    @ApiModelProperty(value = "修改人工号")
    private String modifierCode;

    @ApiModelProperty(value = "修改人姓名")
    private String modifierName;

    @ApiModelProperty(value = "修改人时间")
    private LocalDateTime modifierTime;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "岗位组织关系id")
    private Long postOrgId;

    @TableLogic
    private Boolean delFlag;
}
