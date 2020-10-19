package net.herdao.hdp.manpower.mpclient.dto.post.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PostSeqFormDTO
 * @Description PostSeqFormDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 10:32
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位序列管理-表单页，用于新增")
public class PostSeqFormDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位序列名称")
    private String postSeqName;

    @ApiModelProperty("岗位序列编码")
    private String postSeqCode;

    @ApiModelProperty("集团ID")
    private Long groupId;

    @ApiModelProperty("父ID")
    private Long parentId;

    @ApiModelProperty(value = "描述")
    private String description;
}
