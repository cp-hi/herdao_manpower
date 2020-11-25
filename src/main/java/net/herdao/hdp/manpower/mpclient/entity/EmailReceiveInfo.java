package net.herdao.hdp.manpower.mpclient.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "邮件接收人信息")
public class EmailReceiveInfo {
    @ApiModelProperty(value = "收件人姓名")
    private String name;
    @ApiModelProperty(value = "收件人邮箱地址")
    private String email;
}
