package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.Pipeline;
import net.herdao.hdp.mpclient.entity.PostSeq;
import net.herdao.hdp.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.mpclient.mapper.PostSeqMapper;
import net.herdao.hdp.mpclient.service.PipelineService;
import net.herdao.hdp.mpclient.service.PostSeqService;
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

    public List<Map> postSeqList() {
        return baseMapper.postSeqList();
    }

    public IPage<PostSeq> page(Page<PostSeq> page, String searchTxt) {
        if (StringUtils.isBlank(searchTxt)) searchTxt = "";
        return baseMapper.query(page, searchTxt);
    }

    @Override
    public boolean saveOrUpdate(PostSeq postSeq) {
        if (baseMapper.chkDuplicatePostSeqCode(postSeq))
            throw new RuntimeException("岗位序列编码重复了");
        if (baseMapper.chkDuplicatePostSeqName(postSeq))
            throw new RuntimeException("岗位序列名称重复了");
        return super.saveOrUpdate(postSeq);
    }
}
