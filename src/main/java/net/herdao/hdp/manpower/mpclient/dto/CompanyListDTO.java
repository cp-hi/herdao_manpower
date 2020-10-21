package net.herdao.hdp.manpower.mpclient.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "公司列表")
public class CompanyListDTO {

    private Long id;

    @ApiModelProperty(value="公司名称")
    private String companyName;

    @ApiModelProperty(value="公司编码")
    private String companyCode;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ApiModelProperty(value="所在城市ID")
    private Long cityId;

    @ApiModelProperty(value="公积金标准ID")
    private Long cityFundId;

    @ApiModelProperty(value = "修改人名称" )
    private String modifierName;

    @ExcelIgnore
    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;

    @ApiModelProperty(value = "创建人名称" )
    private String creatorName;

    @ExcelIgnore
    @ApiModelProperty(value = "创建时间" )
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime;

}
