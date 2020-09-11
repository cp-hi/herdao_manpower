package com.hedao.hdp.mpclient.post.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.hedao.hdp.mpclient.entity.BaseEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
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

//    @TableField("POST_CODE")
    private String postCode;
//    @TableField("POST_NAME")
    private String postName;
//    @TableField("PIPELINE_CODE")
    private String pipelineCode;
//    @TableField("POST_LEVEL")
    private String postLevel;
//    @TableField("WELFARE_LEVEL")
    private String welfareLevel;
//    @TableField("ADMINISTRATIVE_LEVEL")
    private String administrativeLevel;
//    @TableField("YEAR_PAY_RATIO")
    private BigDecimal yearPayRatio;
//    @TableField("SALES_POSITION")
    private Boolean salesPosition;
//    @TableField("SALES_MANAGER")
    private Boolean salesManager;
//    @TableField("SORT_NO")
    private Integer sortNo;
    @TableField("IS_STOP")
    private Boolean stop;
//    @TableField("ORG_TYPE")
    private String orgType;

//    @TableField("SECTION_CODE")
    private String sectionCode;
//    @TableField("POST_DESCR")
    private String postDescr;
//    @TableField("REMARK")
    private String remark;
//    @TableField("POST_PROPERTIES")
    private String postProperties;
//    @TableField("PERFOR_SALARY_RATIO")
    private String perforSalaryRatio;
//    @TableField("JOB_LEVEL")
    private String jobLevel;
//    @TableField("POST_SEQUENCE")
    private String postSequence;
//    @TableField("POST_CLAN")
    private String postClan;
//    @TableField("GRADE_NAME")
    private String gradeName;
//    @TableField("NEW_POST_NAME")
    private String newPostName;
//    @TableField("NEW_POST_CODE")
    private String newPostCode;
}
