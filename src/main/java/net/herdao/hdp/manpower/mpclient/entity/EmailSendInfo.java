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

    @ApiModelProperty(value = "id数组字符串")
    private String id;

    @ApiModelProperty(value = "模板配置-邮件发送内容")
    private String content;

    @ApiModelProperty(value = "邮件类型：人才|入职")
    private String type;
}
