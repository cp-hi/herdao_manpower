package net.herdao.hdp.manpower.mpclient.dto.entryApprove;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 入职DTO
 * @author Andy
 */
@Data
@ApiModel(value = "入职DTO")
public class EntryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    @ExcelProperty(value = "姓名")
    private String userName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
    @ExcelProperty(value = "手机号码")
    private String mobileNo;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * 所属组织
     */
    @ApiModelProperty(value="所属组织")
    @ExcelProperty(value = "所属组织")
    private String orgName;

    /**
     * 岗位
     */
    @ApiModelProperty(value="岗位")
    @ExcelProperty(value = "岗位")
    private String postName;

    /**
     * 岗位
     */
    @ApiModelProperty(value="岗位")
    @ExcelProperty(value = "岗位")
    private String jobLevelName;

    /**
     * 入职日期
     */
    @ApiModelProperty(value="入职日期")
    @ExcelProperty(value = "入职日期")
    private String entryPostTime;

    /**
     * 入职登记状态
     */
    @ApiModelProperty(value="入职登记状态")
    @ExcelProperty(value = "入职登记状态")
    private String entryLoginStatus;

    /**
     * 邀请状态
     */
    @ApiModelProperty(value="邀请状态")
    @ExcelProperty(value = "邀请状态")
    private String inviteStatus;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    private String updateDesc;

    /**
     * 人员归属范围
     */
    @ApiModelProperty(value="人员归属范围")
    @ExcelProperty(value = "人员归属范围")
    private String staffStatus;

    /**
     * 人员归属范围
     */
    @ApiModelProperty(value="人员归属范围")
    @ExcelProperty(value = "人员归属范围")
    private String officeType;

    /**
     * 试用期（月）
     */
    @ApiModelProperty(value="试用期")
    @ExcelProperty(value = "试用期")
    private String probation;

    /**
     * 就职次数
     */
    @ApiModelProperty(value="就职次数")
    @ExcelProperty(value = "就职次数")
    private String jobCount;

}
