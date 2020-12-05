package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author Liu Chang
 * @Date 2020/12/5 3:54 下午
 */
@Data
@TableName("mp_staff_contract_renewal")
@ApiModel(value = "合同续签")
public class StaffContractRenewal {

    @ApiModelProperty(value = "合同续约 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String oid;

    @ApiModelProperty(value = "员工 id")
    @NotNull
    private Long userId;

    private String userOid;

    @ApiModelProperty(value = "员工合同 id")
    private Long staffContractId;

    private String staffContractOid;

    @ApiModelProperty(value = "续签部门 id")
    private Long orgId;

    private String orgOid;

    @ApiModelProperty(value = "续签岗位 id")
    private Long postId;

    private String postOid;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDateTime contractStartTime;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDateTime contractEntTime;

    @ApiModelProperty(value = "合同试用期")
    private Double contractProbation;

    @ApiModelProperty(value = "现岗位入职日期")
    private LocalDateTime nowStartDate;

    @ApiModelProperty(value = "劳动合同签定年限")
    private Double contractYears;

    @ApiModelProperty(value = "试用期月数")
    private Double renewalProbation;

    @ApiModelProperty(value = "试用期到期时间")
    private LocalDateTime renewalExpirationTime;

    @ApiModelProperty(value = "试用期月度工资")
    private Double renewalMonthlyWages;

    @ApiModelProperty(value = "合同签定基本工资")
    private Double signedBasicWage;

    @ApiModelProperty(value = "拟续签年数")
    private Double proposedSigningYears;

    @ApiModelProperty(value = "拟续月度工资")
    private Double proposedMonthlyWages;

    @ApiModelProperty(value = "拟续合同签定基本工资")
    private Double proposedBasicWage;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "经办人意见")
    private String remark;

    @ApiModelProperty(value = "续签开始时间")
    private LocalDateTime renewalStartTime;

    @ApiModelProperty(value = "续签结束时间")
    private LocalDateTime renewalEndTime;

    @ApiModelProperty(value = "续签类型")
    private String renewalType;

    @ApiModelProperty(value = "续签主体单位id")
    private Long renewalCompanyId;

    private String renewalCompanyOid;

    @ApiModelProperty(value = "新建人工号")
    @TableField(value = "creator_code", fill = FieldFill.INSERT)
    private String creatorCode;

    @ApiModelProperty(value = "新建人")
    @TableField(value = "creator_name", fill = FieldFill.INSERT)
    private String creatorName;

    @ApiModelProperty(value = "新建人")
    @TableField(value = "creator_time", fill = FieldFill.INSERT)
    private LocalDateTime creatorTime;

    @ApiModelProperty(value = "修改人工号")
    @TableField(value = "modifier_code", fill = FieldFill.UPDATE)
    private String modifierCode;

    @ApiModelProperty(value = "修改人名称")
    @TableField(value = "modifier_name", fill = FieldFill.UPDATE)
    private String modifierName;

    @ApiModelProperty(value = "修改人时间")
    @TableField(value = "modifier_time", fill = FieldFill.UPDATE)
    private LocalDateTime modifierTime;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean delFlag;
}

