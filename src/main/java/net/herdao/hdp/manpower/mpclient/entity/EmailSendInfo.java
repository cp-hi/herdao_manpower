package net.herdao.hdp.manpower.mpclient.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "邮件发送")
public class EmailSendInfo {
    @ApiModelProperty(value = "邮件接收人")
    private List<EmailReceiveInfo> receiveInfos;
    @ApiModelProperty(value = "模板")
    private String template;
}
