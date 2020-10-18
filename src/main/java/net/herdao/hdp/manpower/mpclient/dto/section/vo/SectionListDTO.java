package net.herdao.hdp.manpower.mpclient.dto.section.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName SectionListDTO
 * @Description SectionListDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 20:41
 * @Version 1.0
 */
@Data
@ApiModel(value = "板块管理-列表")
public class SectionListDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("板块名称")
    private String sectionName;

    @ApiModelProperty("板块编码")
    private String sectionCode;

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
