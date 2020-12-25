package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 入职VO
 * @author Andy
 */
@Data
@ApiModel(value = "入职VO")
public class EntryJobVO {

    /**
     * 人才ID
     */
    @ApiModelProperty(value="人才ID")
    private Long recruitmentId;

    /**
     * 审批录用表主键id
     */
    @ApiModelProperty(value="审批录用表主键id")
    private Long approveId;

    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private String certificateType;

    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    private String certificateNo;
}
