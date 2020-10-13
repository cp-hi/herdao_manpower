package net.herdao.hdp.manpower.mpclient.dto.familyStatus;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "家庭情况分页Dto")
public class familyStatusListDto {

    /**
     *
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value="家庭成员姓名")
    private String name;
    /**
     * 关系
     */
    @ApiModelProperty(value="关系")
    private String relations;
    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private Integer age;
    /**
     * 职业
     */
    @ApiModelProperty(value="职业")
    private String professional;
    /**
     * 工作单位/就读学校
     */
    @ApiModelProperty(value="工作单位/就读学校")
    private String workUnit;


    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="操作时间")
    private LocalDateTime modifiedTime;

    /**
     * 所在地址
     */
    @ApiModelProperty(value="所在地址")
    private String address;

    /**
     * 修改人(操作人姓名)
     */
    @ApiModelProperty(value="操作人")
    private String modifierName;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @TableField(exist = false)
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @TableField(exist = false)
    private String staffCode;

}
