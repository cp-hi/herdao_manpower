package net.herdao.hdp.manpower.mpclient.vo.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName PostShortDTO
 * @Description PostShortDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 10:46
 * @Version 1.0
 */

@Data
@ApiModel(value = "岗位管理-下拉列表")
public class PostShortVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位名称")
    private String postName;

    @ApiModelProperty("岗位编码")
    private String postCode;

    @ApiModelProperty("所属集团")
    private Long groupId;

    @ApiModelProperty("版块")
    private String sectionName;

    @ApiModelProperty("管线")
    private String pipelineName;

    @ApiModelProperty("职等")
    private String jobGradeName;

    @ApiModelProperty("岗位序列")
    @DtoField(objField = {"postSeqDTO.parent.parent.postSeqName",
            "postSeqDTO.parent.postSeqName", "postSeqDTO.postSeqName"}, separator = "-")
    private String postSeqName;
}
