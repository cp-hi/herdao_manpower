
package net.herdao.hdp.manpower.mpclient.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工培训VO")
public class StafftrainErrMsg extends Stafftrain implements ExcelErrMsg {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "错误信息")
    @ColumnWidth(100)
    private String errMsg;

    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名")
    private String staffName;

    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号")
    private String staffCode;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    @ExcelProperty(value = "开始时间")
    private String beginTimeStr;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    @ExcelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String endTimeStr;

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
