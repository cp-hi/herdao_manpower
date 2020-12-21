package net.herdao.hdp.manpower.mpclient.dto.entryApprove;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 审批录用DTO
 * @author Andy
 */
@Data
@ApiModel(value = "审批录用")
public class EntryApproveDTO   {

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
     * 工号
     */
    @ApiModelProperty(value="工号")
    @ExcelProperty(value = "工号")
    private String staffId;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
    @ExcelProperty(value = "手机号码")
    private String mobileNo;

    /**
     * 录用组织
     */
    @ApiModelProperty(value="录用组织")
    @ExcelProperty(value = "录用组织")
    private String orgName;

    /**
     * 录用组织ID
     */
    @ApiModelProperty(value="录用组织ID")
    @ExcelProperty(value = "录用组织ID")
    private String orgId;

    /**
     * 录用岗位
     */
    @ApiModelProperty(value="录用岗位")
    @ExcelProperty(value = "录用岗位")
    private String postName;

    /**
     * 录用岗位ID
     */
    @ApiModelProperty(value="录用岗位ID")
    @ExcelProperty(value = "录用岗位ID")
    private String postId;

    /**
     * 人员性质
     */
    @ApiModelProperty(value="人员性质")
    @ExcelProperty(value = "人员性质")
    private String personnelNature;

    /**
     * 任职类型
     */
    @ApiModelProperty(value="任职类型")
    @ExcelProperty(value = "任职类型")
    private String officeType;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态：1 填报中，2 审批中，3 已审批")
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 创建情况
     */
    @ApiModelProperty(value="创建情况")
    @ExcelProperty(value = "创建情况")
    private String createDesc;

    /**
     * 创建情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    private String updateDesc;

}
