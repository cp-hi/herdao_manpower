
package net.herdao.hdp.manpower.mpclient.dto.staffTrain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 员工培训批量新增DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工培训批量新增DTO")
@ColumnWidth(20)
@HeadFontStyle
public class StaffTrainAddDTO  {
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private Long staffId;

    @ExcelProperty(index =0 ,value = "员工姓名")
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffName;

    @ExcelProperty(index =1 ,value = "员工工号")
    @HeadFontStyle(color = 10)
    @Valid
    @NotBlank(message = "不能为空")
    private String staffCode;

    /**
     * 开始时间
     */
    @ExcelProperty(index =2 ,value = "开始时间")
    @HeadFontStyle(color = 10)
    @Valid
    @NotBlank(message = "不能为空")
    private String beginTime;

    /**
     * 结束时间
     */
    @ExcelProperty(index =3 ,value = "结束时间")
    @HeadFontStyle(color = 10)
    @Valid
    @NotBlank(message = "不能为空")
    private String endTime;

    /**
     * 培训时长
     */
    @ExcelProperty(index =4,value = "培训时长")
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String trainPeriod;

    /**
     * 培训总学时
     */
    @ExcelProperty(index =5,value = "培训总学时长")
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String trainLearnPeriod;

    /**
     * 培训总学分
     */
    @ExcelProperty(index =6,value = "培训总学分")
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String trainLearnScore;

    /**
     * 培训类型 内部培训；外部培训 下拉选择
     */
    @ExcelProperty(index =7,value = "培训类型")
    private String trainType;

    /**
     * 培训单位
     */
    @ExcelProperty(index =8,value = "培训单位")
    private String trainCompany;

    /**
     * 培训成绩
     */
    @ExcelProperty(index =9,value = "培训成绩")
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String score;

    /**
     * 证书名称
     */
    @ExcelProperty(index =10,value = "证书名称")
    private String certificateName;

    /**
     * 证书编号
     */
    @ExcelProperty(index =11,value = "证书编号")
    private String certificateCode;

    /**
     * 备注
     */
    @ExcelProperty(index =12,value = "备注")
    private String remarks;
}
