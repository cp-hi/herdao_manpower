package net.herdao.hdp.manpower.mpclient.dto.post.vo;

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
@ApiModel(value = "岗位序列管理-列表")
public class PostSeqListDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(value = "是否最末节点")
    private Integer isLeaf;

    @ApiModelProperty("岗位序列名称")
    private String postSeqName;

    @ApiModelProperty("岗位序列编码")
    private String postSeqCode;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "岗位数")
    private Integer postCount;

    @ApiModelProperty(value = "创建情况")
    @DtoField(joinFields = {"creatorName", "createdTime"}, symbol = " ", suffix = "创建")
    private String createdInfo;

    @ApiModelProperty(value = "最近更新情况")
    @DtoField(joinFields = {"modifierName", "modifiedTime"}, symbol = " ", suffix = "更新")
    private String lastUpdateInfo;
}
