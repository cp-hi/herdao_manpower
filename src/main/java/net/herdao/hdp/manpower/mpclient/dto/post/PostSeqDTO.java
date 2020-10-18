package net.herdao.hdp.manpower.mpclient.dto.post;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;

/**
 * @ClassName PostSeqDTO
 * @Description PostSeqDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 21:26
 * @Version 1.0
 */
@ApiModel(value = "岗位序列DTO")
public class PostSeqDTO extends PostSeq {

    @ApiModelProperty(value = "岗位数")
    private Integer postCount;

    @ApiModelProperty(value = "父序列")
    private PostSeq parent;

    @ApiModelProperty(value = "是否最末节点")
    private Boolean isLeaf;
}
