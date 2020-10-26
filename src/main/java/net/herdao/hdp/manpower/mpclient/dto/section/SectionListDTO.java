package net.herdao.hdp.manpower.mpclient.dto.section;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
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
@ExcelIgnoreUnannotated
@ApiModel(value = "板块管理-列表")
public class SectionListDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ExcelProperty("板块名称")
    @ApiModelProperty("板块名称")
    private String sectionName;

    @ExcelProperty("板块编码")
    @ApiModelProperty("板块编码")
    private String sectionCode;

    @ExcelProperty("所属集团")
    @ApiModelProperty("所属集团")
    @DtoField(objField = "group.groupName")
    private String groupName;

    @ApiModelProperty("所属集团id")
    private Long groupId;

    @ExcelProperty("是否停用")
    @ApiModelProperty("是否停用")
    private Boolean stop;

    @ExcelProperty("排序")
    @ApiModelProperty("排序")
    private Integer sortNo;

    @ExcelProperty("创建情况")
    @ApiModelProperty( "创建情况")
    @DtoField(objField = {"creatorName", "createdTime"}, mapFix =  "{1:\"于\",3:\"创建\"}")
    private String createdInfo;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty( "最近更新情况")
    @DtoField(objField = {"modifierName", "modifiedTime"}, mapFix =  "{1:\"于\",3:\"更新\"}")
    private String lastUpdatedInfo;
}
