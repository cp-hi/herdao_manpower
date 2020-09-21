

package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 组织概况报表
 * @author andy
 * @date 2020-09-21 15:10:10
 */
@Data
/*@TableName("org_situation")*/
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "")
public class OrgSituation extends Model<OrgSituation> {
    private static final long serialVersionUID = 1L;

    /**
     * 全职员工数
     */
    @TableId
    @ApiModelProperty(value="全职员工数")
    private Integer fullTimeJobCount;
    /**
     * 在职员工数
     */
    @ApiModelProperty(value="在职员工数")
    private Integer onJobCount;
    /**
     * 岗位数
     */
    @ApiModelProperty(value="岗位数")
    private Integer postCount;
    /**
     * 试用期员工数
     */
    @ApiModelProperty(value="试用期员工数")
    private Integer trailCount;
    /**
     * 编制人数
     */
    @ApiModelProperty(value="编制人数")
    private Integer establishCount;
    /**
     * 全职员工占比
     */
    @ApiModelProperty(value="全职员工占比")
    private String fullTimeJobZb;
    /**
     * 实习生
     */
    @ApiModelProperty(value="实习生")
    private Integer traineeJobCount;
    /**
     * 实习生员工占比
     */
    @ApiModelProperty(value="实习生员工占比")
    private String traineeJobZb;
    /**
     * 兼职
     */
    @ApiModelProperty(value="兼职")
    private Integer partTimeJobCount;
    /**
     * 兼职员工占比
     */
    @ApiModelProperty(value="兼职员工占比")
    private String partTimeJobZb;
    /**
     * 劳务派遣
     */
    @ApiModelProperty(value="劳务派遣")
    private Integer laborJobCount;
    /**
     * 劳务派遣员工占比
     */
    @ApiModelProperty(value="劳务派遣员工占比")
    private String laborJobZb;
    /**
     * 外包
     */
    @ApiModelProperty(value="外包")
    private Integer outerJobCount;
    /**
     * 外包员工占比
     */
    @ApiModelProperty(value="外包员工占比")
    private String outerJobZb;
    /**
     * 退休返聘
     */
    @ApiModelProperty(value="退休返聘")
    private Integer retireJobCount;
    /**
     * 退休返聘员工占比
     */
    @ApiModelProperty(value="退休返聘员工占比")
    private String retireJobZb;

    /**
     * 组织编码 传参
     */
    @ApiModelProperty(value="组织编码 传参")
    @TableField(exist = false)
    private String orgCode;
}



