package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;
import org.joda.time.DateTime;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 10:04 上午
 */
@Data
@TableName("mp_staff_transfer_approve")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工异动（人事调动、调入调出）")
public class StaffChanges  extends BaseModel<StaffChanges> {

    @ApiModelProperty(value = "异动 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "旧人力oid")
    private String oid;

    @ApiModelProperty(value = "员工id")
    private Long userId;

    @ApiModelProperty(value = "员工oid")
    private String userOid;

    @ApiModelProperty(value = "原部门id")
    private Long nowOrgId;

    @ApiModelProperty(value = "原部门oid")
    private String nowOrgOid;

    @ApiModelProperty(value = "原岗位id")
    private Long nowPostId;

    @ApiModelProperty(value = "原岗位oid")
    private String nowPostOid;

    @ApiModelProperty(value = "原岗位入职日期")
    private DateTime nowStartDate;

    @ApiModelProperty(value = "调入部门id")
    private Long transOrgId;

    @ApiModelProperty(value = "调入部门oid")
    private String transOrgOid;

    @ApiModelProperty(value = "调入岗位id")
    private Long transPostId;

    @ApiModelProperty(value = "调入岗位oid")
    private String transPostOid;

    @ApiModelProperty(value = "调入日期")
    private DateTime transStartDate;

    @ApiModelProperty(value = "合同期限（年）")
    private Double contractPeriod;

    @ApiModelProperty(value = "试用期（月）")
    private Double probation;

    @ApiModelProperty(value = "本公司工龄")
    private Double companySeniority;

    @ApiModelProperty(value = "合生珠江系工龄")
    private Double threeGroupSeniority;

    @ApiModelProperty(value = "现月度总收入")
    private Double previousIncome;

    @ApiModelProperty(value = "其它")
    private Double otherSubsides;

    @ApiModelProperty(value = "年度岗位编制")
    private Integer yearPostPrepareCount;

    @ApiModelProperty(value = "月度岗位编制")
    private Integer monthPostPrepareCount;

    @ApiModelProperty(value = "已到岗人数")
    private Integer postHasCount;

    @ApiModelProperty(value = "经办人意见")
    private String remark;

    @ApiModelProperty(value = "调动类型")
    private String transferType;

    @ApiModelProperty(value = "是否编制置换")
    private Boolean prepareChange;

    @ApiModelProperty(value = "现年度岗位编制")
    private Integer nowYearPostPrepareCount;

    @ApiModelProperty(value = "现月度岗位编制")
    private Integer nowMonthPostPrepareCount;

    @ApiModelProperty(value = "现已到岗人数")
    private Integer nowPostHasCount;

    @ApiModelProperty(value = "工资发放单位id")
    private Long paidUnitsId;

    @ApiModelProperty(value = "工资发放单位oid")
    private String paidUnitsOid;

    @ApiModelProperty(value = "社保缴纳单位id")
    private Long securityUnitId;

    @ApiModelProperty(value = "社保缴纳单位oid")
    private String securityUnitOid;

    @ApiModelProperty(value = "公积金缴纳单位id")
    private Long fundUnitsId;

    @ApiModelProperty(value = "公积金缴纳单位oid")
    private String fundUnitsOid;

    @ApiModelProperty(value = "新建人工号")
    private String creatorCode;

    @ApiModelProperty(value = "新建人")
    private String creatorName;

    @ApiModelProperty(value = "新建时间")
    private DateTime creatorTime;

    @ApiModelProperty(value = "修改人工号")
    private String modifierCode;

    @ApiModelProperty(value = "修改人")
    private String modifierName;

    @ApiModelProperty(value = "修改人时间")
    private DateTime modifierTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "原职级id")
    private Long nowJobLevelId;

    @ApiModelProperty(value = "变更后职级id")
    private Long transJobLevelId;
}
