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

    @ApiModelProperty(value = "岗位序列名称",required = true)
    private String postSeqName;

    @ApiModelProperty(value = "岗位序列编码",hidden = true)
    private String postSeqCode;

    @ApiModelProperty(value = "所属集团",required = true)
    private Long groupId;

    @ApiModelProperty("集团")
    private String groupName;

    @ApiModelProperty("上级岗位序列")
    private Long parentId;

    @ApiModelProperty("父级名称")
    @DtoField(objField = {"parent.postSeqName"})
    private String parentName;

    @ApiModelProperty("描述")
    private String description;
}
