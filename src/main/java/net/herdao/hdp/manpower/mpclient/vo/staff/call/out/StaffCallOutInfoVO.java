package net.herdao.hdp.manpower.mpclient.vo.staff.call.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 6:59 下午
 */
@Data
@ApiModel(value = "调出详情页")
public class StaffCallOutInfoVO {
    @ApiModelProperty(value = "人事调出 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "集团 id", name = "groupId", example = "1")
    private Long groupId;

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

    @ApiModelProperty(value = "调动前部门 id", name = "nowOrgId", example = "1234")
    private Long nowOrgId;

    @ApiModelProperty(value = "调动前部门名称", name = "nowOrgName", example = "部门 A")
    private String nowOrgName;

    @ApiModelProperty(value = "调动前岗位 id", name = "nowPostId", example = "2345")
    private Long nowPostOrgId;

    @ApiModelProperty(value = "调动前岗位名称", name = "nowOrgName", example = "岗位 a")
    private String nowPostOrgName;

    @ApiModelProperty(value = "原职级 id", name = "jobLevelId", example = "123")
    private Long nowJobLevelId;

    @ApiModelProperty(value = "原职级名称", name = "jobLevelName", example = "M3-6")
    private String nowJobLevelName;

    @ApiModelProperty(value = "调动后部门 id", name = "transOrgId", example = "4321")
    private Long transOrgId;

    @ApiModelProperty(value = "调动后部门名称", name = "transOrgName", example = "部门 ")
    private String transOrgName;

    @ApiModelProperty(value = "调动后岗位 id", name = "transPostOrgId", example = "5432")
    private Long transPostOrgId;

    @ApiModelProperty(value = "调动后岗位名称", name = "transPostOrgName", example = "岗位 b")
    private String transPostOrgName;

    @ApiModelProperty(value = "调动后职级Id", name = "transJobLevelId", example = "123")
    private Long transJobLevelId;

    @ApiModelProperty(value = "调动后职级名称", name = "transJobLevelName")
    private String transJobLevelName;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901")
    private Long transStartDate;

    @ApiModelProperty(value = "合同年限", name = "contractPeriod")
    private Double contractPeriod;

    @ApiModelProperty(value = "本公司工龄", name = "companySeniority")
    private Double companySeniority;

    @ApiModelProperty(value = "合生珠江系工龄", name = "threeGroupSeniority")
    private Double threeGroupSeniority;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;
}
