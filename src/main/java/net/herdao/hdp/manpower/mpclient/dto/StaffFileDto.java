package net.herdao.hdp.manpower.mpclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "员工附件")
public class StaffFileDto {
    @ApiModelProperty(value="ID")
    private Long id;

    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ApiModelProperty(value="员工工号")
    private String staffCode;

    @ApiModelProperty(value="文件类型")
    private String fileType;

    @ApiModelProperty(value="文件名")
    private String fileName;

    @ApiModelProperty(value="修改人")
    private String modifierName;


    @ApiModelProperty(value="修改时间")
    private String modifyTime;

    @ApiModelProperty(value="操作人")
    private String createName;

    @ApiModelProperty(value="操作时间")
    private String createTime;
}
