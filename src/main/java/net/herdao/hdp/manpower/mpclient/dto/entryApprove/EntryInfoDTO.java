package net.herdao.hdp.manpower.mpclient.dto.entryApprove;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 入职管理-办理入职
 * @author Andy
 */
@Data
@ApiModel(value = "入职管理-办理入职 ")
public class EntryInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入职管理-办理入职-个人信息
     */
    @ApiModelProperty(value="入职管理-办理入职-个人信息")
    private EntryPersonInfoDTO entryPersonInfoDTO;

    /**
     * 入职管理-办理入职-个人信息
     */
    @ApiModelProperty(value="入职管理-办理入职-个人信息")
    private EntryJobDTO entryJobDTO;
}
