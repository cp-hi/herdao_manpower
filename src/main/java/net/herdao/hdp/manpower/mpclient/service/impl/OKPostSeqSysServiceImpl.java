package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.post.OKPostSeqDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.OKPostSeqSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.OKPostSeqSysMapper;
import net.herdao.hdp.manpower.mpclient.service.OKPostSeqSysService;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void okCreatePostSeq(Long okPostSeqSysId, Long groupId)
            throws IllegalAccessException {
        OKPostSeqSysDTO postSeqSys = findDetail(okPostSeqSysId);
        for (OKPostSeqDTO okPostSeq : postSeqSys.getOkPostSeqDTOList()) {
            PostSeq postSeq = okPostSeq;
            postSeq.setId(null);
            postSeq.setGroupId(groupId);
            postSeqService.saveVerify(postSeq);
            postSeqService.saveEntity(postSeq);
            List<? extends Post> posts = okPostSeq.getOkPostDTOList();
            for (Post post : posts) {
                post.setId(null);
                post.setGroupId(groupId);
                post.setPostSeqId(postSeq.getId());
                postService.saveVerify(post);
                postService.saveEntity(post);
            }
        }
    }
}
