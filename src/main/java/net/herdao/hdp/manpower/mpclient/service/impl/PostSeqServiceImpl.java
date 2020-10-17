package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.mapper.PostSeqMapper;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PipelineServiceImpl
 * @Description PipelineServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:21
 * @Version 1.0
 */
@Service
public class PostSeqServiceImpl extends ServiceImpl<PostSeqMapper, PostSeq> implements PostSeqService {
    @Override
    public List<Map> postSeqList(Long groupId) {
        return baseMapper.postSeqList(groupId);
    }

    @Override
    public IPage<PostSeqDTO> page(Page<PostSeqDTO> page, String searchTxt) {
        if (StringUtils.isBlank(searchTxt)) searchTxt = "";
        return baseMapper.page(page, searchTxt);
    }

    @Override
    public void saveVerify(PostSeq postSeq) {
        if (baseMapper.chkDuplicatePostSeqCode(postSeq))
            throw new RuntimeException("岗位序列编码重复了");
        if (baseMapper.chkDuplicatePostSeqName(postSeq))
            throw new RuntimeException("岗位序列名称重复了");
    }
}
