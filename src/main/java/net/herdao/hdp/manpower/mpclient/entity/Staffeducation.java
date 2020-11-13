package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:59:39
 */
@Data
@TableName("mp_staffeducation")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工教育经历")
public class Staffeducation extends BaseModel<Staffeducation> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * oid
     */
    @ApiModelProperty(value="oid")
    private String oid;
    /**
     * 起止时间
     */
    @ApiModelProperty(value="起止时间")
    private String period;
    /**
     * 毕业院校
     */
    @ApiModelProperty(value="毕业院校")
    private String schoolName;
    /**
     * 专业
     */
    @ApiModelProperty(value="专业")
    private String professional;
    /**
     * 学位
     */
    @ApiModelProperty(value="学位")
    private String educationDegree;
    /**
     * 学习形式
     */
    @ApiModelProperty(value="学习形式")
    private String learnForm;
    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorCode;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private Date createdTime;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private Date modifiedTime;
    /**
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private Long staffId;
    /**
     * 毕业日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    @ApiModelProperty(value="毕业日期")
    private Date endDate;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    private String field1;
    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    private String field2;
    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    private String field3;
    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    private String field4;
    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    private String field5;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="入学日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date beginDate;
    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;
    /**
     * 学历
     */
    @ApiModelProperty(value="学历")
    private String educationQua;

    /**
     * 修改人(操作人姓名)
     */
    @ApiModelProperty(value="修改人(操作人姓名)")
    private String modifierName;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID" ,hidden = true)
    private Long creatorId;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称" ,hidden = true)
    private String creatorName;

    /**
     * 修改ID
     */
    @ApiModelProperty(value = "修改ID" ,hidden = true)
    private Long modifierId;

}
