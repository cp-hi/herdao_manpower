package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.constant.MsgType;
import net.herdao.hdp.admin.api.dto.SysMsgDTO;
import net.herdao.hdp.admin.api.entity.SysMsg;
import net.herdao.hdp.admin.api.feign.RemoteMsgService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.EmailSendInfo;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.entity.StaffEntrypostApprove;
import net.herdao.hdp.manpower.mpclient.service.MsgService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import net.herdao.hdp.manpower.sys.utils.RemoteCallUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andy
 */
@Service
@AllArgsConstructor
public class MsgServiceImpl implements MsgService {

    private final RemoteMsgService remoteMsgService;

    private final RecruitmentService recruitmentService;

    private final StaffEntrypostApproveService approveService;

    /**
     * 发送邮件
     * @param emailSendInfo
     * @return
     */
    @Override
    @SneakyThrows
    public Boolean sendEmail(EmailSendInfo emailSendInfo) {
        try {
            if (ObjectUtil.isNotEmpty(emailSendInfo.getId())){
                String[] idArray = emailSendInfo.getId().split(",");
                List<SysMsgDTO> sysMsgs = new ArrayList<>();

                if (ObjectUtil.isNotEmpty(idArray)){
                    //人才管理发送简历更新邮件
                    if("人才".equals(emailSendInfo.getType())){
                        List<Recruitment> recruitmentList=recruitmentService.listByIds(Arrays.asList(idArray));
                        if (ObjectUtil.isNotEmpty(recruitmentList)){
                            recruitmentList.forEach(e->{
                                SysMsgDTO sysMsg = new SysMsgDTO();
                                sysMsg.setReceptEmail(e.getEmail());
                                sysMsg.setContent(emailSendInfo.getContent());
                                sysMsg.setTitle("人才管理");
                                sysMsg.setReceptPhone(e.getHomePhone());
                                sysMsgs.add(sysMsg);
                            });
                        }
                    }

                    //入职邀请发送入职邀请邮件
                    if("入职".equals(emailSendInfo.getType())){
                        List<StaffEntrypostApprove> approveList = approveService.listByIds(Arrays.asList(idArray));
                        if (CollectionUtil.isNotEmpty(approveList)){
                            List<Long> recruitmentIdArray=new ArrayList<>();
                            for (StaffEntrypostApprove approve : approveList) {
                                Long recruitmentId = approve.getRecruitmentId();
                                recruitmentIdArray.add(recruitmentId);
                            }
                            if (CollectionUtil.isNotEmpty(recruitmentIdArray)){
                                List<Recruitment> recruitmentList=recruitmentService.listByIds(recruitmentIdArray);
                                if (ObjectUtil.isNotEmpty(recruitmentList)){
                                    recruitmentList.forEach(e->{
                                        SysMsgDTO sysMsg = new SysMsgDTO();
                                        sysMsg.setReceptEmail(e.getEmail());
                                        sysMsg.setContent(emailSendInfo.getContent());
                                        sysMsg.setTitle("入职邀请");
                                        sysMsg.setReceptPhone(e.getHomePhone());
                                        sysMsgs.add(sysMsg);
                                    });
                                }
                            }
                        }
                    }

                    if(CollectionUtil.isNotEmpty(sysMsgs)){
                        R<Boolean> r = remoteMsgService.sendMailBatch(sysMsgs);
                        RemoteCallUtils.checkData(r);
                    }
                }
            }
        }catch (Exception ex){
            return Boolean.FALSE;
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
