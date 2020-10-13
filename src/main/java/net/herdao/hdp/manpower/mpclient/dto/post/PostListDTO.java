package net.herdao.hdp.manpower.mpclient.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PostListDTO
 * @Description PostListDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/13 19:44
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位列表DTO")
public class PostListDTO {
    @ApiModelProperty(value = "岗位名称", required = true)
    private String postName;

    @ApiModelProperty(value = "岗位编码", required = true)
    private String postCode;

    @ApiModelProperty(value = "岗位序列", required = true)
    private String postSeqName;

    @ApiModelProperty(value = "所属集团")
    private String groupName;

    @ApiModelProperty(value = "板块")
    private String sectionName;

    @ApiModelProperty(value = "管线")
    private String pipelineName;


}
