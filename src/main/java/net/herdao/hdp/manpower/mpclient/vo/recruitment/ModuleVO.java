package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 模板VO
 * @author Andy
 */
@Data
@ApiModel(value = "模板VO")
public class ModuleVO {


    /**
     * 模板内容
     */
    @ApiModelProperty(value="模板内容")
    private String content;




}
