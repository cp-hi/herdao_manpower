package net.herdao.hdp.manpower.mpclient.dto.entryApprove;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 审批录用新增DTO
 * @author Andy
 */
@Data
@ApiModel(value = "审批录用新增DTO")
public class EntryApproveAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    @ExcelProperty(value = "姓名")
    private String userName;

    /**
     * 原占编员工id
     */
    @ApiModelProperty(value="原占编员工id")
    private Long replaceUserId;

    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private String certificateType;

    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    private String certificateNo;

    /**
     * 人员性质
     */
    @ApiModelProperty(value="人员性质")
    private String personnelNature;

    /**
     * 人员归属范围
     */
    @ApiModelProperty(value="人员归属范围")
    private String staffStatus;

    /**
     * 任职类型
     */
    @ApiModelProperty(value="任职类型")
    private String officeType;

    /**
     * 是否需要任命
     */
    @ApiModelProperty(value="是否需要任命")
    private Integer isAppointment;

    /**
     * 录用组织
     */
    @ApiModelProperty(value="录用组织")
    private Long orgId;

    /**
     * 录用岗位
     */
    @ApiModelProperty(value="录用岗位")
    private Long postId;

    /**
     * 岗位年度编制
     */
    @ApiModelProperty(value="岗位年度编制")
    private Integer yearPostPrepareCount;

    /**
     * 岗位月度编制
     */
    @ApiModelProperty(value="岗位月度编制")
    private Integer monthPostPrepareCount;

    /**
     * 已到岗人数
     */
    @ApiModelProperty(value="已到岗人数")
    private Integer postHasCount;

    /**
     * 劳动合同签订年限
     */
    @ApiModelProperty(value="劳动合同签订年限")
    private String contractPeriod;

    /**
     * 试用期（月）
     */
    @ApiModelProperty(value="试用期（月）")
    private String probation;

    /**
     * 劳动合同签订主体ID
     */
    @ApiModelProperty(value="劳动合同签订主体ID")
    private Long contractCompanyId;

    /**
     * 社保购买单位
     */
    @ApiModelProperty(value="社保购买单位ID")
    private Long securityUnitsId;

    /**
     * 工资发放单位id
     */
    @ApiModelProperty(value="工资发放单位ID")
    private Long paidUnitsId;

    /**
     * 公积金购买单位id
     */
    @ApiModelProperty(value="公积金购买单位ID")
    private Long fundUnitsId;

    /**
     * 经办人意见
     */
    @ApiModelProperty(value="经办人意见")
    private String remark;

}
