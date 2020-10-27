package net.herdao.hdp.manpower.mpclient.vo.pipeline;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
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
@ExcelIgnoreUnannotated
@ApiModel(value = "管线管理-列表")
public class PipelineListVO {

    @ApiModelProperty("ID")
    private Long id;

    @ExcelProperty("管线名称")
    @ApiModelProperty("管线名称")
    private String pipelineName;

    @ExcelProperty("管线编码")
    @ApiModelProperty("管线编码")
    private String pipelineCode;

    @ExcelProperty("所属集团")
    @ApiModelProperty("所属集团")
    @DtoField(objField = "group.groupName")
    private String groupName;

    @ApiModelProperty("所属集团id")
    private Long groupId;

    @ExcelProperty("是否停用")
    @ApiModelProperty("是否停用")
    @DtoField(objField = "stop",converter = "{true:\"已停用\",false:\"已启用\"}")
    private String stop;

    @ExcelProperty("排序")
    @ApiModelProperty("排序")
    private Integer sortNo;

    @ExcelProperty("创建情况")
    @ApiModelProperty(value = "创建情况")
    @DtoField(objField = {"creatorName", "createdTime"},   mapFix =  "{1:\"于\",3:\"创建\"}")
    private String createdInfo;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty( "最近更新情况")
    @DtoField(objField = {"modifierName", "modifiedTime"},   mapFix =  "{1:\"于\",3:\"更新\"}")
    private String lastUpdatedInfo;

}
