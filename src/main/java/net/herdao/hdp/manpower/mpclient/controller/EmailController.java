package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.EmailSendInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@Api(value = "email", tags = "邮件")
public class EmailController {
    @ApiOperation(value = "邮件模板", notes = "邮件模板")
    @GetMapping("/template")
    public R<String> getEmailTemplate() {
        return null;
    }
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @PostMapping("/send")
    public R<Boolean> sendEmail(@RequestBody EmailSendInfo emailSendInfo) {
        return null;
    }
}
