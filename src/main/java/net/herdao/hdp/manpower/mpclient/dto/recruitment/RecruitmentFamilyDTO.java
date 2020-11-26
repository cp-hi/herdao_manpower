
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才家庭情况DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才家庭情况DTO")
public class RecruitmentFamilyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;

    /**
     * 家庭成员姓名
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
    private BigDecimal age;

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
     * 所在地址
     */
    @ApiModelProperty(value="所在地址")
    private String address;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    private String updateDesc;
}
