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

    @ApiModelProperty(value = "员工姓名(员工工号)", name = "staffNameAndCode", example = "张三")
    private String staffNameAndCode;

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
    private Long nowPostId;

    @ApiModelProperty(value = "调动前岗位名称", name = "nowOrgName", example = "岗位 a")
    private String nowPostName;

    @ApiModelProperty(value = "调动前职级", name = "nowJobLevel", example = "123")
    private Dictionary nowJobLevel;

    @ApiModelProperty(value = "调动后部门 id", name = "transOrgId", example = "4321")
    private Long transOrgId;

    @ApiModelProperty(value = "调动后部门名称", name = "transOrgName", example = "部门 ")
    private String transOrgName;

    @ApiModelProperty(value = "调动后岗位 id", name = "transPostId", example = "5432")
    private Long transPostId;

    @ApiModelProperty(value = "调动后岗位名称", name = "transPostName", example = "岗位 b")
    private String transPostName;

    @ApiModelProperty(value = "调动后职级", name = "transJobLevel", example = "123")
    private Dictionary transJobLevel;

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
    private List<String> appendixIds;

    @Data
    // 字典
    public static class Dictionary {
        // 名称
        private String label;
        // id
        private Long value;
    }
}
