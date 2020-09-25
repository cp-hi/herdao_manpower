package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
public class PostSeq  extends BaseEntity<PostSeq> {
    private String postSeqName;
    private String postSeqCode;
    private String description;
    private Long groupId;
    private Long parentId;
}
