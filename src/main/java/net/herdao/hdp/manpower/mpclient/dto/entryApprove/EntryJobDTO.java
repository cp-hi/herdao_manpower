package net.herdao.hdp.manpower.mpclient.dto.entryApprove;

import com.alibaba.excel.annotation.ExcelIgnore;
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
@ApiModel(value = "入职信息")
public class EntryJobDTO {

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
     * 职级
     */
    @ApiModelProperty(value="职级")
    private String jobLevelName;

    /**
     * 入职时间
     */
    @ApiModelProperty(value="入职时间")
    private Long entryPostTime;

    /**
     * 试用期
     */
    @ApiModelProperty(value="试用期")
    private String probation;

    /**
     * 所属组织id
     */
    @ApiModelProperty(value="所属组织id")
    @ExcelIgnore
    private Long orgId;

    /**
     * 所属岗位id
     */
    @ApiModelProperty(value = "所属岗位id")
    @ExcelIgnore
    private Long postId;

    /**
     * 入职日期-LocalDateTime
     */
    @ApiModelProperty(value="入职日期-LocalDateTime",hidden = true)
    private LocalDateTime entryPostTimeLocal;

}
