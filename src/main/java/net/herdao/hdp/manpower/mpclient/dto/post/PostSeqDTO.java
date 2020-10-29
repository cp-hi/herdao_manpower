package net.herdao.hdp.manpower.mpclient.dto.post;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;

/**
 * @ClassName PostSeqDTO
 * @Description PostSeqDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 21:26
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位序列DTO")
public class PostSeqDTO extends PostSeq {

    @ApiModelProperty("岗位数")
    private Integer postCount;

    @ApiModelProperty("父序列")
    private PostSeqDTO parent;

    @ApiModelProperty("集团")
    private Group group;

    @ApiModelProperty("是否最末节点")
    private Boolean isLeaf;
}
