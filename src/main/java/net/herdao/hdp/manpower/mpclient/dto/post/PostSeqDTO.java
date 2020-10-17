package net.herdao.hdp.manpower.mpclient.dto.post;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class PostSeqDTO  extends PostSeq {
    private Integer postCount;
    @TableField(exist = false)
    @ApiModelProperty(value = "父序列")
    private PostSeq parent;
}
