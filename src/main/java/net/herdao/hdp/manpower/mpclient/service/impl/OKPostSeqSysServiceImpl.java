package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.OKJobLevleSysDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.OKPostSeqSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.OKJobLevleSysMapper;
import net.herdao.hdp.manpower.mpclient.mapper.OKPostSeqSysMapper;
import net.herdao.hdp.manpower.mpclient.service.OKJobLevleSysService;
import net.herdao.hdp.manpower.mpclient.service.OKPostSeqSysService;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OKPostSeqSysServiceImpl
 * @Description OKPostSeqSysServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 16:34
 * @Version 1.0
 */
@Service
public class OKPostSeqSysServiceImpl extends ServiceImpl<OKPostSeqSysMapper, OKPostSeqSys> implements OKPostSeqSysService {

    @Autowired
    PostSeqService postSeqService;
    @Autowired
    PostService postService;

    @Override
    public OKPostSeqSysDTO findDetail(Long okPostSeqSysId) {
        return baseMapper.findDetail(okPostSeqSysId);
    }

    @Override
    public void okCreatePostSeq(Long okPostSeqSysId) {
        OKPostSeqSysDTO postSeqSys  = findDetail(okPostSeqSysId);
        postSeqSys.getOkPostSeqDTOList().forEach(okPostSeq  -> {
            PostSeq postSeq = okPostSeq;
            postSeq.setId(null);
            postSeqService.saveVerify(postSeq);//TODO 校验
            postSeqService.save(postSeq);
            List<? extends Post> posts = okPostSeq.getOkPostDTOList();
            posts.forEach(post -> {
                post.setId(null);
                post.setPostSeqId(postSeq.getId());
                postService.saveVerify(post);  // TODO 校验
                postService.saveOrUpdate(post);
            });
        });
    }
}
