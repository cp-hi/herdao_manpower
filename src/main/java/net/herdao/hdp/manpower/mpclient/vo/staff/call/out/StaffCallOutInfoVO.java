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
    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "调动前部门 id", name = "nowOrgId", example = "1234")
    private Long nowOrgId;

    @ApiModelProperty(value = "调动前岗位 id", name = "nowPostId", example = "2345")
    private Long nowPostId;

    @ApiModelProperty(value = "原职级 id", name = "jobLevelId", example = "123")
    private Long nowJobLevelId;

    @ApiModelProperty(value = "调动后部门 id", name = "transOrgId", example = "4321")
    private Long transOrgId;

    @ApiModelProperty(value = "调动后岗位 id", name = "transPostId", example = "5432")
    private Long transPostId;

    @ApiModelProperty(value = "调动后职级 id", name = "jobLevelId", example = "123")
    private Long transJobLevelId;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901")
    private Long transStartDate;

    @ApiModelProperty(value = "合同年限", name = "contractPeriod")
    private Double contractPeriod;

    @ApiModelProperty(value = "本公司工龄", name = "probation")
    private Double companySeniority;

    @ApiModelProperty(value = "合生珠江系工龄", name = "threeGroupSeniority")
    private Double threeGroupSeniority;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;
}
