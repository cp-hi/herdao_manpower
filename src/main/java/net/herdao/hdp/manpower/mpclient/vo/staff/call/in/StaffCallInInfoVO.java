package net.herdao.hdp.manpower.mpclient.vo.staff.call.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:59 下午
 */
@Data
@ApiModel(value = "调入详情页")
public class StaffCallInInfoVO {
    @ApiModelProperty(value = "人事调入 id", name = "id", example = "1")
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

    @ApiModelProperty(value = "岗位年度编制", name = "yearPostPrepareCount", example = "5")
    private Integer yearPostPrepareCount;

    @ApiModelProperty(value = "岗位月度编制", name = "monthPostPrepareCount", example = "5")
    private Integer monthPostPrepareCount;

    @ApiModelProperty(value = "已到岗人数", name = "postHasCount", example = "5")
    private Integer postHasCount;

    @ApiModelProperty(value = "合同年限", name = "contractPeriod")
    private Double contractPeriod;

    @ApiModelProperty(value = "试用期", name = "probation")
    private Double probation;

    @ApiModelProperty(value = "本公司工龄", name = "companySeniority")
    private Double companySeniority;

    @ApiModelProperty(value = "合生珠江系工龄", name = "threeGroupSeniority")
    private Double threeGroupSeniority;

    @ApiModelProperty(value = "工资发放单位", name = "payUnit")
    private StaffTransferInfoVO.Dictionary payUnit;

    @ApiModelProperty(value = "工资发放单位", name = "payUnit")
    private StaffTransferInfoVO.Dictionary fundUnit;

    @ApiModelProperty(value = "社保购买单位", name = "securityUnit")
    private StaffTransferInfoVO.Dictionary securityUnit;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;

    @Data
    // 公司字典
    public static class Dictionary {
        // 公司名
        private String label;
        // 公司 id
        private Long value;
    }

}
