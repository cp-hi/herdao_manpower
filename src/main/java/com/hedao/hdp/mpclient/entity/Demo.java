package com.hedao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * demo 表
 *
 * @author liang
 * @date 2020-08-06 15:18:40
 */
@Data
@TableName("demo")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "demo 表")
public class Demo extends Model<Demo> {
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value="主键")
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;
    }
