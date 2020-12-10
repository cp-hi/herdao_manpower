

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@Data
@TableName("mp_userpost")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户岗位表管理")
public class Userpost extends BaseModel<Userpost> {
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value="主键")
    private Long id;
    /**
     * 用户岗位OID
     */
    //@ApiModelProperty(value="用户岗位OID")
    private String oid;
    /**
     * 用户外键
     */
   // @ApiModelProperty(value="用户外键")
    private String userOid;
    /**
     * 岗位外键
     */
    //@ApiModelProperty(value="岗位外键")
    private String postOid;
    /**
     * 所属组织外键
     */
   // @ApiModelProperty(value="所属组织外键")
    private String orgOid;
    /**
     * 所属部门外键
     */
    //@ApiModelProperty(value="所属部门外键")
    private String orgDeptOid;
    /**
     * 用户外键
     */
    @ApiModelProperty(value="用户外键")
    private Long userId;

    /**
     * 岗位外键
     */
    @ApiModelProperty(value="岗位外键")
    private Long postId;

    /**
     * 所属组织外键
     */
    @ApiModelProperty(value="所属组织外键")
    private Long orgId;

    @ApiModelProperty(value = "职级id")
    private Long jobLevelId;

    /**
     * 所属部门外键
     */
    @ApiModelProperty(value="所属部门外键")
    private Long orgDeptId;

    /**
     * 是否主岗位
     */
    @ApiModelProperty(value="是否主岗位")
    private Boolean mainPost;

    /**
     * 实际职务
     */
    @ApiModelProperty(value="实际职务")
    private String actualJob;

    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorCode;

    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
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
     * 是否虚拟岗位（默认为否）
     */
    @ApiModelProperty(value="")
    private Boolean isVirtual;

    /**
     * 
     */
    @ApiModelProperty(value="")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;

    /**
     * 
     */
    @ApiModelProperty(value="")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;

    /**
     * 代码表行政级别
     */
    @ApiModelProperty(value="代码表行政级别")
    private String administrativeLevel;

    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;

    @ApiModelProperty(value="分页大小")
    @TableField(exist = false)
    private List<Long> orgDeptIds;

    /**
     * 是否定制岗位
     */
    @ApiModelProperty(value="是否定制岗位")
    private String officePostType;
}
