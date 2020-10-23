package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "劳动合同")
public class StaffContractDetailDTO {

    private Long id;

    @ApiModelProperty(value="员工外键")
    private Long staffId;

    @ApiModelProperty(value="合同开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value="合同结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value="合同签订主体")
    private String companyCode;

    @ApiModelProperty(value="合同编号")
    private String contractId;

    @ApiModelProperty(value="合同是否生效")
    private Boolean newest;

    @ApiModelProperty(value="合同期限类型")
    private String contractType;

    @ApiModelProperty(value="合同期限")
    private String contractPeriod;

    @ApiModelProperty(value="试用期")
    private Long probationMonth;

    @ApiModelProperty(value="最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value="最后修改人")
    private String modifierCode;
}
