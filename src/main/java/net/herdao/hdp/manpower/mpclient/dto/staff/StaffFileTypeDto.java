package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "员工附件二级分类")
public class StaffFileTypeDto {
    @ApiModelProperty(value="主键ID")
    private Long id;

    @ApiModelProperty(value="员工工号")
    private Long staffCode;

    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ApiModelProperty(value="附件二级分类")
    private String type;

    @ApiModelProperty(value="附件一级分类")
    private String superType;

    @ApiModelProperty(value="启用状态")
    private String enableStatus;

    @ApiModelProperty(value="修改人")
    private String modifierName;

    @ApiModelProperty(value="修改时间")
    private String modifyTimec;

    @ApiModelProperty(value="创建人")
    private String createName;

    @ApiModelProperty(value="创建时间")
    private String createTime;
}
