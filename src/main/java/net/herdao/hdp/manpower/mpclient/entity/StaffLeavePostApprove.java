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

    @ApiModelProperty(value = "申请类型")
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

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns1;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns2;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns3;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns4;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns5;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns6;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns7;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAns8;

    @ApiModelProperty(value = " id")
    private String leaveQuestionAnsOther;

    @ApiModelProperty(value = " id")
    private String interviewSummary;

    @ApiModelProperty(value = " id")
    private Boolean isAppoint;

    @ApiModelProperty(value = " id")
    private Boolean isLeaveAuditing;

    @ApiModelProperty(value = " id")
    private Long userAgentId;

    @ApiModelProperty(value = " id")
    private String userAgentOid;

    @ApiModelProperty(value = " id")
    private String leavePostType;

    @ApiModelProperty(value = " id")
    private String creatorCode;

    @ApiModelProperty(value = " id")
    private String creatorName;

    @ApiModelProperty(value = " id")
    private LocalDateTime creatorTime;

    @ApiModelProperty(value = " id")
    private String modifierCode;

    @ApiModelProperty(value = " id")
    private String modifierName;

    @ApiModelProperty(value = " id")
    private LocalDateTime modifierTime;

    @ApiModelProperty(value = " id")
    private Long tenantId;

    @ApiModelProperty(value = " id")
    private Long postOrgId;

    @TableLogic
    private Boolean delFlag;
}
