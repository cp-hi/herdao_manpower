package net.herdao.hdp.manpower.mpclient.service;

import io.swagger.models.auth.In;
import net.herdao.hdp.manpower.mpclient.entity.EmailSendInfo;

import java.util.List;

public interface MsgService {
    /**
     * 发送邮件
     */
    Boolean sendEmail(EmailSendInfo emailSendInfo);

    /**
     * 消息重发
     * @param ids
     * @return
     */
    Boolean msgResend(List<Integer> ids);
}
