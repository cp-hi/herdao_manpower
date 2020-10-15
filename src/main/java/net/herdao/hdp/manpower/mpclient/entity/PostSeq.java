package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * @ClassName PostSeq
 * @Description PostSeq
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/17 19:17
 * @Version 1.0
 */
@Data
@TableName("MP_Post_Seq")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "岗位序列")
public class PostSeq extends BaseEntity<PostSeq> {

    @ApiModelProperty(value = "岗位序列名称", required = true)
    private String postSeqName;

    @ApiModelProperty(value = "岗位序列编码")
    private String postSeqCode;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "集团ID")
    private Long groupId;

    @ApiModelProperty(value = "父ID")
    private Long parentId;

    @TableField(exist = false)
    @ApiModelProperty(value = "父序列")
    private PostSeq parent;
}
