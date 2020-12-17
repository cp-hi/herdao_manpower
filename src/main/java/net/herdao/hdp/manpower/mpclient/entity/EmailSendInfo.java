package net.herdao.hdp.manpower.mpclient.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Andy
 */
@Data
@ApiModel(value = "邮件发送")
public class EmailSendInfo {

    @ApiModelProperty(value = "邮件接收人-业务表主键id数组")
    private List<Long> ids;

    @ApiModelProperty(value = "模板配置-邮件发送内容")
    private String template;

    @ApiModelProperty(value = "邮件类型：")
    private String type;
}
