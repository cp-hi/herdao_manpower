package net.herdao.hdp.manpower.mpclient.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

import java.util.Date;

/**
 * @ClassName PipelineVO
 * @Description PipelineVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 21:04
 * @Version 1.0
 */
public class PipelineVO {

    @ApiModelProperty("id")
    private Long id;

    private String pipelineCode;

    private String pipelineName;

    @ApiModelProperty("所属集团")
    @DtoField(objField = "group.groupName")
    private String groupName;

    private Integer sortNo;


    @ApiModelProperty(value = "创建人名称")
    private String creatorName;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime;
    @ApiModelProperty(value = "修改ID")
    private Long modifierId;
    @ApiModelProperty(value = "修改人名称")
    private String modifierName;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;
}
