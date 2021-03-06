
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
 * 员工培训list DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工培训list DTO")
public class StafftrainDTO  {

    private static final long serialVersionUID = 1L;

    /**
     * 新平台新增
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    @ExcelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String endTime;

    /**
     * 培训时长
     */
    @ApiModelProperty(value="培训时长")
    @ExcelProperty(value = "培训时长")
    private String trainPeriod;

    /**
     * 培训总学时
     */
    @ApiModelProperty(value="培训总学时长")
    @ExcelProperty(value = "培训总学时长")
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

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    @ExcelIgnore
    private String updateDesc;
    /**
     * 集团id
     */
    @ApiModelProperty(value="集团id")
    @ExcelIgnore
    private String groupId;
    /**
     * 集团名
     */
    @ApiModelProperty(value="集团名")
    @ExcelProperty(value = "集团名")
    private String groupName;
    /**
     * 所在组织id
     */
    @ApiModelProperty(value = "所在组织id")
    private Long orgId;

}
