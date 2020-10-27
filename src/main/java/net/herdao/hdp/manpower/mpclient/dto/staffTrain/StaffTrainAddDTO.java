
package net.herdao.hdp.manpower.mpclient.dto.staffTrain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工培训批量导入DTO")
public class StaffTrainAddDTO  {
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private Long staffId;

    @ExcelProperty(index =0 ,value = "员工姓名")
    @NotBlank(message = "不能为空")
    private String staffName;

    @ExcelProperty(index =1 ,value = "员工工号")
    @NotBlank(message = "不能为空")
    private String staffCode;

    /**
     * 开始时间
     */
    @ExcelProperty(index =2 ,value = "开始时间")
    private String beginTime;

    /**
     * 结束时间
     */
    @ExcelProperty(index =3 ,value = "结束时间")
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
    @ExcelProperty(index =5,value = "培训总学时")
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
