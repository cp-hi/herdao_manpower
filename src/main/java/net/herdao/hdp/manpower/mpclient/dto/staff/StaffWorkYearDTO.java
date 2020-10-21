package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@ApiModel(value = "工作年限")
public class StaffWorkYearDTO {
	/**
     * 参加工作日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="参加工作日期")
    private LocalDate workDate;
    /**
     * 参加工作工龄
     */
    @ApiModelProperty(value="参加工作工龄")
    private BigDecimal workSeniority;
    /**
     * 入职本公司日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="入职本公司日期")
    private LocalDate entryTime;
    /**
     * 本公司工龄
     */
    @ApiModelProperty(value="本公司工龄")
    private BigDecimal companySeniority;
    /**
     * 入职集团日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="入职集团日期")
    private LocalDate entryThreeGroupsTime;
    /**
     * 集团工龄
     */
    @ApiModelProperty(value="集团工龄")
    private BigDecimal threeGroupsSeniority;
    /**
     * 非连续工作时间（不计算年假的年限）
     */
    @ApiModelProperty(value="非连续工作时长")
    private BigDecimal noWorkingSeniority;
    /**
     * 实际工作工龄
     */
    @ApiModelProperty(value="实际工作工龄")
    private BigDecimal realityWorkingSeniority;

    /**
     * 员工信息id
     */
    @ApiModelProperty(value="员工信息id")
    private Long staffid;
}
