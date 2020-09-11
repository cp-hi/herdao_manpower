package com.hedao.hdp.mpclient.post.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.hedao.hdp.mpclient.entity.BaseEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
public class Post extends BaseEntity<Post> {

    private String postCode;
    private String postName;
    private String pipelineCode;
    private String postLevel;
    private String welfareLevel;
    private String administrativeLevel;
    private String yearPayRatio;
    private Boolean salesPosition;
    private Boolean salesManager;
    private Integer sortNo;
    @TableField("IS_STOP")
    private Boolean stop;
    private String orgType;
    private String sectionCode;
    private String postDescr;
    private String remark;
    private String postProperties;
    private String perforSalaryRatio;
    private String rank;
    private String postSequence;
    private String postClan;
    private String gradeName;
    private String newPostName;
    private String newPostCode;
}
