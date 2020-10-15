package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(value = "员工附件一级分类")
public class StaffFileSuperTypeDTO {

    @ApiModelProperty(value="分类名")
    private String label;

    @ApiModelProperty(value="分类值")
    private String staffName;


}
