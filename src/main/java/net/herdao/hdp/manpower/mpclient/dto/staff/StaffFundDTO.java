package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "公积金信息")
public class StaffFundDTO {

    private Long id;

    @ApiModelProperty(value="是否交公积金")
    private Boolean funding;

    @ApiModelProperty(value="实际公积金支付单位")
    private String payFundUnit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="公积金封存时间")
    private LocalDateTime fundArchiveTime;

    @ApiModelProperty(value="公积金银行名称")
    private String fundArchiveBankName;

    @ApiModelProperty(value="公积金银行帐号")
    private String fundArchiveBankAccount;

    @ApiModelProperty(value="备注")
    private String remark;
}
