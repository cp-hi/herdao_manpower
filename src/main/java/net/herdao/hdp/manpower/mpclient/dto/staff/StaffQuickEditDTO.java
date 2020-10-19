package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "花名册快速编辑")
public class StaffQuickEditDTO {

    private Long id;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="证件类型")
    private String idType;

    @ApiModelProperty(value="身份证号码")
    private String idNumber;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @ApiModelProperty(value="入职本公司日期")
    private LocalDate entryTime;
}
