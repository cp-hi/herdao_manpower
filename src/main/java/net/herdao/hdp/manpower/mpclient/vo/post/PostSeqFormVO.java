package net.herdao.hdp.manpower.mpclient.vo.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

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
public class PostSeqFormVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位序列名称")
    private String postSeqName;

    @ApiModelProperty("岗位序列编码")
    private String postSeqCode;

    @ApiModelProperty("所属集团")
    private Long groupId;

    @ApiModelProperty("集团")
//    @DtoField(objField = {"group.groupName"})
    private String groupName;

    @ApiModelProperty("父级节点")
    private Long parentId;

    @ApiModelProperty("父级名称")
    @DtoField(objField = {"parent.postSeqName"})
    private String parentName;

    @ApiModelProperty("描述")
    private String description;
}
