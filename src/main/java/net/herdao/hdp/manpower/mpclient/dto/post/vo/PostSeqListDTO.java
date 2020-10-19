package net.herdao.hdp.manpower.mpclient.dto.post.vo;

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
public class PostSeqListDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(value = "是否最末节点")
    private Boolean isLeaf;

    @ExcelProperty("岗位序列名称")
    @ApiModelProperty("岗位序列名称")
    private String postSeqName;

    @ExcelProperty("岗位序列编码")
    @ApiModelProperty("岗位序列编码")
    private String postSeqCode;

    @ExcelProperty("描述")
    @ApiModelProperty(value = "描述")
    private String description;

    @ExcelProperty("岗位数")
    @ApiModelProperty(value = "岗位数")
    private Integer postCount;

    @ExcelProperty("创建情况")
    @ApiModelProperty(value = "创建情况")
    @DtoField(joinFields = {"creatorName", "createdTime"}, symbol = " ", suffix = "创建")
    private String createdInfo;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty(value = "最近更新情况")
    @DtoField(joinFields = {"modifierName", "modifiedTime"}, symbol = " ", suffix = "更新")
    private String lastUpdateInfo;
}
