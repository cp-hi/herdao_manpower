package com.hedao.hdp.mpclient.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

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
    private BigDecimal yearPayRatio;
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
    private String jobLevel;
    private String postSequence;
    private String postClan;
    private String gradeName;
    private String newPostName;
    private String newPostCode;
}
