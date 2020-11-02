package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.mapper.PostMapper;
import net.herdao.hdp.manpower.mpclient.mapper.PostSeqMapper;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqBatchVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PostSeqServiceImpl extends EntityServiceImpl<PostSeqMapper, PostSeq> implements PostSeqService {
    @Override
    public List<Map> postSeqList(Long groupId) {
        return baseMapper.postSeqList(groupId);
    }


    @Override
    public void saveVerify(PostSeq postSeq) {
        StringBuffer buffer = new StringBuffer();
        chkParent(postSeq.getId(), buffer);
        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());
        super.saveVerify(postSeq);
    }

    @Override
    public void addEntity(PostSeq postSeq, Object excelObj) {
        PostSeqBatchVO excel = (PostSeqBatchVO) excelObj;
        StringBuffer buffer = new StringBuffer();
        chkEntityExists("POST_SEQ_NAME", excel.getPostSeqName(), false, buffer);
        PostSeq parent = this.chkEntityExists("POST_SEQ_NAME", excel.getParentName(), true, buffer);
        if (null != parent) {
            chkParent(parent.getId(), buffer);
            postSeq.setParentId(parent.getId());
        }
        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());
    }

    @Override
    public void updateEntity(PostSeq postSeq, Object excelObj) {
        PostSeqBatchVO excel = (PostSeqBatchVO) excelObj;
        StringBuffer buffer = new StringBuffer();
        PostSeq tmp = chkEntityExists("POST_SEQ_NAME", excel.getPostSeqName(), true, buffer);
        PostSeq parent = this.chkEntityExists("POST_SEQ_NAME", excel.getParentName(), true, buffer);

        if (null != parent) {
            chkParent(parent.getId(), buffer);
            postSeq.setParentId(parent.getId());
        }
        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());

        postSeq.setId(tmp.getId());
    }

    private void chkParent(Long parentId, StringBuffer buffer) {
        PostSeqDTO dto = baseMapper.getPostSeqDTO(parentId);
        if (null != dto.getParent() && null != dto.getParent().getParent())
            buffer.append("；" + dto.getPostSeqName() + "为3级岗位序列，不能再创建子级");
    }
}
