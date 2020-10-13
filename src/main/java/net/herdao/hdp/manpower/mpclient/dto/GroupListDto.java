package net.herdao.hdp.manpower.mpclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "集团列表")
public class GroupListDto {

    private Long id;

    @ApiModelProperty(value="集团名称")
    private String groupName;

    @ApiModelProperty(value="集团编码")
    private String groupCode;

    @ApiModelProperty(value="对应组织")
    private String orgCode;

    @ApiModelProperty(value="排序")
    private Integer sortNo;

    @ApiModelProperty(value = "修改人名称" )
    private String modifierName;

    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;

    @ApiModelProperty(value = "创建人名称" )
    private String creatorName;

    @ApiModelProperty(value = "创建时间" )
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime;
}
