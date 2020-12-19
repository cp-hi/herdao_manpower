package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 手机端-个人信息-填写进度VO
 * @author Andy
 */
@Data
@ApiModel(value = "手机端-个人信息-填写进度VO")
public class RecruitmentMobileProgressVO {

    /**
     * 字段总数
     */
    @ApiModelProperty(value="字段总数")
    private Integer total;

    /**
     * 已填写字段总数
     */
    @ApiModelProperty(value="已填写字段总数")
    private Integer finishCount;

    /**
     * 填写进度（百分比）
     */
    @ApiModelProperty(value="填写进度（百分比）")
    private String progress;

    /**
     * 填写完成状态：已完成，未完成
     */
    @ApiModelProperty(value="填写完成状态：已完成，未完成")
    private String status;

}
