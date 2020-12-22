package net.herdao.hdp.manpower.mpclient.dto.entryApprove;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 入职管理-办理入职-个人信息DTO
 * @author Andy
 */
@Data
@ApiModel(value = "个人信息")
public class EntryPersonInfoDTO {

    /**
     * 人才主键ID
     */
    @ApiModelProperty(value="人才主键ID")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    @ExcelProperty(value = "姓名")
    private String talentName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
    @ExcelProperty(value = "手机号码")
    private String mobileNo;

    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    @ExcelProperty(value = "证件类型")
    private String certificateType;

    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    @ExcelProperty(value = "证件号码")
    private String certificateNo;


}
