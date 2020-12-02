package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.service.impl.PostSeqServiceImpl;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

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
    @ApiModelProperty(value = "岗位序列名称")
    private String postSeqName;

    @ApiModelProperty(value = "岗位序列编码", hidden = true)
    private String postSeqCode;

    @ApiModelProperty(value = "描述", hidden = true)
    private String description;

    @ApiModelProperty(value = "集团", required = true)
    private Long groupId;

    @ApiModelProperty(value = "父序列", required = true)
    @DtoField(entityService = PostSeqServiceImpl.class, targetField = "parentName")
    private Long parentId;

    @ApiModelProperty(value = "是否停用")
    private Boolean isStop;
}
