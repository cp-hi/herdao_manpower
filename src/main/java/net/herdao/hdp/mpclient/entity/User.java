

package net.herdao.hdp.mpclient.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.mpclient.common.Utils.DataConverter;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author andy
 * @date 2020-09-15 17:59:33
 */
@Data
@TableName("mp_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "")
public class User extends Model<User> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private String oid;
    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String userName;
    /**
     * 登录帐号
     */
    @ApiModelProperty(value="登录帐号")
    private String loginCode;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;
    /**
     * 密码2
     */
    @ApiModelProperty(value="密码2")
    private String password2;
    /**
     * 所属组织外键
     */
    @ApiModelProperty(value="所属组织外键")
    @ExcelProperty(value = "所属组织外键", index = 1,converter = DataConverter.class)
    private Long orgOid;
    /**
     * 
     */
    @ApiModelProperty(value="")
    @ExcelIgnore
    private Long orgId;
    /**
     * 所属部门外键
     */
    @ApiModelProperty(value="所属部门外键")
    private String orgDeptOid;
    /**
     * 
     */
    @ApiModelProperty(value="")
    @ExcelIgnore
    private Long orgDeptId;
    /**
     * 是否停用
     */
    @ApiModelProperty(value="是否停用")
    @ExcelIgnore
    private Long isStop;
    /**
     * 最后登陆时间
     */
    @ApiModelProperty(value="最后登陆时间")
    @ExcelIgnore
    private LocalDateTime lastLoginTime;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 
     */
    @ApiModelProperty(value="")
    @ExcelIgnore
    private LocalDateTime leaveTime;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    @ExcelIgnore
    private Long tenantId;
    }
