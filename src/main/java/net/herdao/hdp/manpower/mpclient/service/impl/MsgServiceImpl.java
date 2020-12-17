package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.constant.MsgType;
import net.herdao.hdp.admin.api.dto.SysMsgDTO;
import net.herdao.hdp.admin.api.entity.SysMsg;
import net.herdao.hdp.admin.api.feign.RemoteMsgService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.EmailSendInfo;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.service.MsgService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.sys.utils.RemoteCallUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MsgServiceImpl implements MsgService {

    private final RemoteMsgService remoteMsgService;
    private final RecruitmentService recruitmentService;
    /**
     * 发送邮件
     * @param emailSendInfo
     * @return
     */
    @Override
    @SneakyThrows
    public Boolean sendEmail(EmailSendInfo emailSendInfo) {
        List<SysMsgDTO> sysMsgs = new ArrayList<>();
        //人才管理发送简历更新邮件
        if("1".equals(emailSendInfo.getType())){
            List<Recruitment> recruitments = recruitmentService.listByIds(emailSendInfo.getIds());
            recruitments.forEach(e->{
                SysMsgDTO sysMsg = new SysMsgDTO();
                sysMsg.setReceptEmail(e.getEmail());
                sysMsg.setContent(emailSendInfo.getTemplate());
                sysMsg.setTitle("人才管理");
                sysMsg.setReceptPhone(e.getHomePhone());
                sysMsgs.add(sysMsg);
            });
        }
        //入职邀请发送入职邀请邮件
        if("2".equals(emailSendInfo.getType())){
            List<Recruitment> recruitments = recruitmentService.listByIds(emailSendInfo.getIds());
            recruitments.forEach(e->{
                SysMsgDTO sysMsg = new SysMsgDTO();
                sysMsg.setReceptEmail(e.getEmail());
                sysMsg.setContent(emailSendInfo.getTemplate());
                sysMsg.setTitle("人才管理");
                sysMsg.setReceptPhone(e.getHomePhone());
                sysMsgs.add(sysMsg);
            });
        }
        if(CollectionUtil.isNotEmpty(sysMsgs)){
            R<Boolean> r = remoteMsgService.sendMailBatch(sysMsgs);
            RemoteCallUtils.checkData(r);
        }
        return Boolean.TRUE;
    }

    @Override
    @SneakyThrows
    public Boolean msgResend(List<Integer> ids) {
        List<SysMsg> sysMsgs = RemoteCallUtils.checkData(remoteMsgService.getListbyIds(ids));
        sysMsgs.forEach(e->{
            if(MsgType.WEB_TEMPLATE_MSG.equals(e.getType())){
                //发送网页消息
            }
            if(MsgType.MAIL_ONTIME_MSG.equals(e.getType())){
                remoteMsgService.reSend(e.getId());
            }
            if(MsgType.MAIL_TEMPLATE_MSG.equals(e.getType())){

            }
            if(MsgType.WX_TEMPLATE_MSG.equals(e.getType())){

            }
            if(MsgType.QY_WX_TEMPLATE_MSG.equals(e.getType())){
                //发送启业微信消息
            }
        });
        return Boolean.TRUE;
    }
}
