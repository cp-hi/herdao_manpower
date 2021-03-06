
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * 人才简历-录用情况
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-录用情况")
public class RecruitmentEmployeeDTO   {

    /**
     * 审批表id
     */
    @ApiModelProperty(value="审批表id")
    private Long id;

    /**
     * 工作年限
     */
    @ApiModelProperty(value="招聘结果")
    private String result;

    /**
     * 录用单位
     */
    @ApiModelProperty(value="录用单位")
    private String orgName;

    /**
     * 录用岗位
     */
    @ApiModelProperty(value="录用岗位")
    private String postName;

    /**
     * 所属管线
     */
    @ApiModelProperty(value="所属管线")
    private String pipelineName;

    /**
     * 可到岗日期-LocalDateTime
     */
    @ApiModelProperty(value="可到岗日期-LocalDateTime",hidden = true)
    private LocalDateTime entryPostTimeLocal;

    /**
     * 可到岗日期
     */
    @ApiModelProperty(value="可到岗日期")
    private Long entryPostTime;

    /**
     * 是否到岗
     */
    @ApiModelProperty(value="是否到岗")
    private String dutyStatus;
}
