package com.hedao.hdp.ehrclient.post.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName Post
 * @Description Post
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/10 19:20
 * @Version 1.0
 */

@Data
@TableName("MP_Post")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "")
public class Post extends Model<Post> {
    private static final long serialVersionUID = 1L;

    private Integer id;
    POST_CODE
}
