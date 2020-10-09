package net.herdao.hdp.manpower.mpclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "员工职称及职业资料")
public class StaffProDto {
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 职称
     */
    @ApiModelProperty(value="职称")
    private String proName;

    /**
     * 职业资格
     */
    @ApiModelProperty(value="职业资格")
    private String proCase;

    /**
     * 评定单位
     */
    @ApiModelProperty(value="评定单位")
    private String evaluateUnit;

    /**
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String proNo;

    /**
     * 发证时间
     */
    @ApiModelProperty(value="发证时间")
    private String certificateTime;

    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String depandUnit;

    @ApiModelProperty(value="修改人")
    private String modifierName;

    @ApiModelProperty(value="修改时间")
    private String modifiedTime;

    @ApiModelProperty(value="操作人")
    private String createName;

    @ApiModelProperty(value="操作时间")
    private String createdTime;
}
