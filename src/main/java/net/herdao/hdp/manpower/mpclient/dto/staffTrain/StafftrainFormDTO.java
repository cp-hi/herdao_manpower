
package net.herdao.hdp.manpower.mpclient.dto.staffTrain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 员工培训表单 DTO
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工培训表单 DTO")
public class StafftrainFormDTO {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "错误信息")
    @ColumnWidth(100)
    private String errMsg;

    /**
     * 新平台新增
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    /**
     * 员工ID
     */
    @ApiModelProperty(value="员工ID")
    @ExcelProperty(value = "员工ID")
    private String staffId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    @ExcelProperty(value = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    @ExcelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    /**
     * 培训时长
     */
    @ApiModelProperty(value="培训时长")
    @ExcelProperty(value = "培训时长")
    private String trainPeriod;

    /**
     * 培训总学时
     */
    @ApiModelProperty(value="培训总学时")
    @ExcelProperty(value = "培训总学时")
    private String trainLearnPeriod;

    /**
     * 培训总学分
     */
    @ApiModelProperty(value="培训总学分")
    @ExcelProperty(value = "培训总学分")
    private String trainLearnScore;

    /**
     * 培训类型 内部培训；外部培训 下拉选择
     */
    @ApiModelProperty(value="培训类型")
    @ExcelProperty(value = "培训类型")
    private String trainType;

    /**
     * 培训单位
     */
    @ApiModelProperty(value="培训单位")
    @ExcelProperty(value = "培训单位")
    private String trainCompany;

    /**
     * 培训成绩
     */
    @ApiModelProperty(value="培训成绩")
    @ExcelProperty(value = "培训成绩")
    private String score;


    /**
     * 证书名称
     */
    @ApiModelProperty(value="证书名称")
    @ExcelProperty(value = "证书名称")
    private String certificateName;

    /**
     * 证书编号
     */
    @ApiModelProperty(value="证书编号")
    @ExcelProperty(value = "证书编号")
    private String certificateCode;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    @ExcelProperty(value = "备注")
    private String remarks;




}
