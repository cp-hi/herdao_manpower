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
     * 标题
     */
    @ApiModelProperty(value="标题")
    private String title;

    /**
     * 模板内容
     */
    @ApiModelProperty(value="模板内容")
    private String content;

    /**
     * 二维码BASE64编码
     */
    @ApiModelProperty(value="二维码BASE64编码")
    private String code;

    /**
     * 姓名-邮箱
     */
    @ApiModelProperty(value="姓名-邮箱")
    private List<Map<String,String>> nameEmailList;


}
