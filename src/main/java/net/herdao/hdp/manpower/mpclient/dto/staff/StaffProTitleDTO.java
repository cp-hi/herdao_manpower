package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@ApiModel(value = "员工职称及职业资料")
public class StaffProTitleDTO {
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 职称
     */
    @ApiModelProperty(value="职称")
    private String titleName;

    /**
     * 职业资格
     */
    @ApiModelProperty(value="职业资格")
    private String assessmentUnit;

    /**
     * 评定单位
     */
    @ApiModelProperty(value="评定单位")
    private String professionalQualifications;

    /**
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String titleCode;

    /**
     * 发证时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="发证时间")
    private LocalDate certificateTime;

    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String qualitificationUnit;

    /**
     * 操作人
     */
    @ApiModelProperty(value="操作人")
    private String modifierName;

    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    private String modifiedTime;
    
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
     * 员工信息id
     */
    @ApiModelProperty(value="员工信息id")
    private Long staffId;
}
