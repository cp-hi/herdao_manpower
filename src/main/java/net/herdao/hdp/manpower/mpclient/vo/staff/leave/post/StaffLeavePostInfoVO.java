package net.herdao.hdp.manpower.mpclient.vo.staff.leave.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/3 10:54 上午
 */
@Data
@ApiModel(value = "离职详情")
public class StaffLeavePostInfoVO {
    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    /**
     *
     * 该字段对应 staffNameAndCode，但是为了适配前端，需要改成不同的名字
     */
    @ApiModelProperty(value = "员工姓名(员工工号)", name = "staffNameInfo", example = "张三")
    private String staffNameInfo;

    @ApiModelProperty(value = "人员归属范围", name = "staffScope", example = "在职人员")
    private String staffScope;

    @ApiModelProperty(value = "人员任职类型", name = "staffJobType", example = "在职人员")
    private String staffJobType;

    @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
    private Long entryTime;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="年龄")
    private int age;

    @ApiModelProperty(value="籍贯")
    private String birthplace;

    @ApiModelProperty(value = "离职申请日期")
    private Long leaveApplicationTime;

    @ApiModelProperty(value = "离职日期")
    private Long leaveTime;

    @ApiModelProperty(value = "是否任免")
    private Boolean isAppoint;

    @ApiModelProperty(value = "是否离职审计")
    private Boolean isLeaveAuditing;

    @ApiModelProperty(value = "交接人")
    private String userAgentName;

    @ApiModelProperty(value = "离职原因")
    private String leaveReason;

    @ApiModelProperty(value = "离职面谈时间", example = "1606967526")
    private Long interviewsTime;

    @ApiModelProperty(value = "离职面谈地点")
    private String interviewsPlace;

    @ApiModelProperty(value = "入职引领人")
    private String entryLeader;

    @ApiModelProperty(value = "面谈主持人")
    private String interviewsHost;

    @ApiModelProperty(value = "面谈参与人")
    private String interviewsParticipants;

    @ApiModelProperty(value = "记录人")
    private String recordMan;

    @ApiModelProperty(value = "离职所属期")
    private String leaveBelongsPeriod;

    @ApiModelProperty(value = "离职类型")
    private String leaveType;

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
    @NotNull
    private String interviewSummary;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;
}
