package net.herdao.hdp.manpower.mpclient.vo.post;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName PostSeqListDTO
 * @Description PostSeqListDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 18:17
 * @Version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "岗位序列管理-列表")
public class PostSeqListVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("是否最末节点")
    private Boolean isLeaf;

    @ExcelProperty("岗位序列名称")
    @ApiModelProperty("岗位序列名称")
    private String postSeqName;

    @ApiModelProperty("上级岗位序列id")
    private String parentSeqId;

    @ApiModelProperty("上级岗位序列名称")
    private String parentSeqName;

    @ExcelProperty("岗位序列编码")
    @ApiModelProperty("岗位序列编码")
    private String postSeqCode;

    @ExcelProperty("描述")
    @ApiModelProperty("描述")
    private String description;

    @ExcelProperty("岗位数")
    @ApiModelProperty("岗位数")
    private Integer postCount;

    @ExcelProperty("创建情况")
    @ApiModelProperty("创建情况")
    @DtoField(objField = {"creatorName", "createdTime"}, mapFix = "{1:\"于\",3:\"创建\"}")
    private String createdInfo;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty("最近更新情况")
    @DtoField(objField = {"modifierName", "modifiedTime"}, mapFix = "{1:\"于\",3:\"更新\"}")
    private String lastUpdatedInfo;
}
