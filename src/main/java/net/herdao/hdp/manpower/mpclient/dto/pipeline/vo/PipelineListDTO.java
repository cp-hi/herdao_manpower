package net.herdao.hdp.manpower.mpclient.dto.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName PipelineListDTO
 * @Description PipelineListDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 21:16
 * @Version 1.0
 */
@Data
@ApiModel(value = "管线管理-列表")
public class PipelineListDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("管线名称")
    private String pipelineName;

    @ApiModelProperty("管线编码")
    private String pipelineCode;

    @ApiModelProperty("所属集团")
    @DtoField(objField = "group.groupName")
    private String groupName;

    @ApiModelProperty("是否停用")
//    @DtoField(boolField = "stop")
    private Boolean stop;

    @ApiModelProperty("排序")
    private Integer sortNo;

    @ApiModelProperty(value = "创建情况")
    @DtoField(joinFields = {"creatorName", "createdTime"}, symbol = " ", suffix = "创建")
    private String createdInfo;

    @ApiModelProperty(value = "最近更新情况")
    @DtoField(joinFields = {"modifierName", "modifiedTime"}, symbol = " ", suffix = "更新")
    private String lastUpdateInfo;

}
