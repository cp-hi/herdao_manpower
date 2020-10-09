package net.herdao.hdp.manpower.mpclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "页面DTO")
public class FormDto {
    @ApiModelProperty(value="id")
    private String id;

    @ApiModelProperty(value="名称")
    private String name;

    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value="操作")
    private Map<String,Object> operateMap;



}
