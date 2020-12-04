package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.constant.MsgType;
import net.herdao.hdp.admin.api.entity.SysMsg;
import net.herdao.hdp.admin.api.feign.RemoteMsgService;
import net.herdao.hdp.manpower.mpclient.entity.EmailSendInfo;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.service.MsgService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.sys.utils.RemoteCallUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
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
        List<SysMsg> sysMsgs = new ArrayList<>();
        //人才管理发送简历更新邮件
        if("1".equals(emailSendInfo.getType())){
            List<Recruitment> recruitments = recruitmentService.listByIds(emailSendInfo.getIds());
            recruitments.forEach(e->{
                SysMsg sysMsg = new SysMsg();
                //sysMsg.setEmail(e.getEmail());
                sysMsg.setEmail("734458184@qq.com");
                sysMsg.setContent(emailSendInfo.getTemplate());
                sysMsg.setTitle("人才管理");
                sysMsg.setPhone(e.getHomePhone());
                sysMsgs.add(sysMsg);
            });
        }
        if(CollectionUtil.isNotEmpty(sysMsgs)){
            //remoteMsgService.sendMailBatch(sysMsgs);
            MultipartFile mfile=new MockMultipartFile("附件1",new FileInputStream(new File("D:\\download\\%E5%85%AC%E5%8F%B8.xlsx")));
            SysMsg[] sysMsgs1 = sysMsgs.toArray(new SysMsg[]{});
            String [] ss=new String[]{"aa","bb"};
            List<String> ls=new ArrayList<>();
            ls.add("aa");
            ls.add("bb");
            remoteMsgService.sendMail(sysMsgs1[0],mfile);
            //remoteMsgService.sendAttachMailBatch(sysMsgs,mfile);
        }
        return null;
    }

    @Override
    public Boolean msgResend(List<Integer> ids) {
        List<SysMsg> sysMsgs = RemoteCallUtils.checkData(remoteMsgService.getListbyIds(ids));
        sysMsgs.forEach(e->{
            //邮件模板消息
            if(MsgType.MAIL_TEMPLATE_MSG.equals(e.getType())){
                //设置附件
            }
       });
        MultiValueMap m= new LinkedMultiValueMap();

        return null;
    }
}
