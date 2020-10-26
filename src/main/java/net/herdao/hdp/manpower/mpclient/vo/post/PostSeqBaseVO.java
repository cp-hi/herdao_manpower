package net.herdao.hdp.manpower.mpclient.vo.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PostSeqBaseDTO
 * @Description PostSeqBaseDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 18:44
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位序列管理-基础信息")
@Deprecated
public class PostSeqBaseVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位序列名称")
    private String postSeqName;

    @ApiModelProperty("岗位序列编码")
    private String postSeqCode;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("岗位数")
    private Integer postCount;

    @ApiModelProperty("创建情况")
    private String createdInfo;

    @ApiModelProperty("最近更新情况")
    private String lastUpdatedInfo;
}
