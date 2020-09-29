

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工附件二级分类
 *
 * @author andy
 * @date 2020-09-29 11:17:03
 */
@Data
@TableName("mp_staff_second_file_type")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工附件二级分类")
public class StaffSecondFileType extends BaseModel<StaffSecondFileType> {
private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value="分类名称")
    private String name;
    /**
     * 一级分类id （关联字典表的员工附件一级分类）
     */
    @ApiModelProperty(value="一级分类id （关联字典表的员工附件一级分类）")
    private String supeerId;
    /**
     * 员工外键
     */
    @ApiModelProperty(value="员工外键")
    private Long staffId;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 新建用户

     */
    @ApiModelProperty(value="新建用户")
    private String creatorCode;
    /**
     * 新建时间

     */
    @ApiModelProperty(value="新建时间 ")
    private LocalDateTime createdTime;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;
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
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;

    /**
     * 是否启用（0:禁用 , 1:启用）
     */
    @ApiModelProperty(value="是否启用（0 ： 禁用 ，1 ：启用）")
    private Boolean status;
}
