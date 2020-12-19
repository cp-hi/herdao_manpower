package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.EmailSendInfo;
import net.herdao.hdp.manpower.mpclient.service.MsgService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
@Api(value = "msg", tags = "消息管理")
@AllArgsConstructor
public class MsgController {
    private final MsgService msgService;
    @ApiOperation(value = "邮件模板", notes = "邮件模板")
    @GetMapping("/email/template")
    public R<String> getEmailTemplate(String type) {
        return R.ok("邮件模板");
    }

    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @PostMapping("/email/send")
    public R<Boolean> sendEmail(@RequestBody EmailSendInfo emailSendInfo) {
        msgService.sendEmail(emailSendInfo);
        return null;
    }

    @ApiOperation(value = "消息重发", notes = "消息重发")
    @PostMapping("/msg/resend")
    public R<Boolean> msgResend(List<Integer> ids) {
//        msgService.sendEmail(ids);
        return null;
    }
}
