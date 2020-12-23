
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才简历-其他个人信息-编辑DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-其他个人信息-编辑DTO")
public class RecruitmentEditOtherInfoDTO {

    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    private Long id;

    /**
     * 是否有亲戚朋友在本公司
     */
    @ApiModelProperty(value="是否有亲戚朋友在本公司")
    private Boolean isRelativeCompany;

    /**
     * 是否接受外派
     */
    @ApiModelProperty(value="是否接受外派")
    private Boolean isAcceptAssignment;

    /**
     * 亲戚朋友其职位与姓名
     */
    @ApiModelProperty(value="亲戚朋友其职位与姓名")
    private String relativePostName;

    /**
     * 可接受外派地点
     */
    @ApiModelProperty(value="可接受外派地点")
    private String acceptAssignmentLocation;

    /**
     * 申请人签名
     */
    @ApiModelProperty(value="申请人签名")
    private String applicantSign;

    /**
     * 简历入库时间
     */
    @ApiModelProperty(value="简历入库时间")
    private Long resumeAccessTime;

    /**
     * 简历入库时间-LocalDateTime
     */
    @ApiModelProperty(value="简历入库时间-LocalDateTime",hidden = true)
    private LocalDateTime resumeAccessTimeLocal;

    /**
     * 获得招聘信息途径
     */
    @ApiModelProperty(value="获得招聘信息途径")
    private String resumeAccess;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

}
