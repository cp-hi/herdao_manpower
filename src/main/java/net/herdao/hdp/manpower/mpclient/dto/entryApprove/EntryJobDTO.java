package net.herdao.hdp.manpower.mpclient.dto.entryApprove;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 入职管理-办理入职-入职信息DTO
 * @author Andy
 */
@Data
@ApiModel(value = "入职管理-办理入职-入职信息DTO")
public class EntryJobDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审批表主键ID
     */
    @ApiModelProperty(value="id")
    private Long id;

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
     * 人员性质
     */
    @ApiModelProperty(value="人员性质")
    private String personnelNature;

    /**
     * 入职组织
     */
    @ApiModelProperty(value="入职组织")
    private String orgName;

    /**
     * 入职岗位
     */
    @ApiModelProperty(value="入职岗位")
    private String postName;

    /**
     * 职级
     */
    @ApiModelProperty(value="职级")
    private String jobLevelName;

    /**
     * 入职时间
     */
    @ApiModelProperty(value="入职时间")
    private String entryPostTime;

    /**
     * 试用期
     */
    @ApiModelProperty(value="试用期")
    private String probation;

}
