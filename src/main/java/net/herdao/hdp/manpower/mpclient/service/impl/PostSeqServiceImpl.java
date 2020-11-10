package net.herdao.hdp.manpower.mpclient.service.impl;

import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.mapper.PostSeqMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqBatchVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
    @Autowired
    GroupService groupService;

    @Override
    public List<Map> postSeqList(Long groupId) {
        return baseMapper.postSeqList(groupId);
    }

    @Override
    public void saveVerify(PostSeq postSeq) {
        StringBuffer buffer = new StringBuffer();
        chkParent(postSeq.getParentId(), buffer);
        super.saveVerify(postSeq, buffer);
        throwErrMsg(buffer);
    }

    @Override
    public void addEntity(PostSeq postSeq, Object excelObj,StringBuffer buffer) {
        PostSeqBatchVO excel = (PostSeqBatchVO) excelObj;
        Group group = groupService.selectByName(excel.getGroupName(), true);
        if(null != group)  postSeq.setGroupId(group.getId());
        chkEntityExists(excel.getPostSeqName(), group.getId(), false,buffer);
        PostSeq parent = this.chkEntityExists(excel.getParentName(), group.getId(), true, buffer);
        if (null != parent) {
            chkParent(parent.getId(), buffer);
            postSeq.setParentId(parent.getId());
        }
    }

    @Override
    public void updateEntity(PostSeq postSeq, Object excelObj,  StringBuffer buffer) {
        PostSeqBatchVO excel = (PostSeqBatchVO) excelObj;

        Group group = groupService.selectByName(excel.getGroupName(), true);
        if(null != group)  postSeq.setGroupId(group.getId());
        PostSeq tmp = chkEntityExists(excel.getPostSeqName(), group.getId(), true, buffer);
        PostSeq parent = this.chkEntityExists(excel.getParentName(), group.getId(), true, buffer);

        if (null != parent) {
            chkParent(parent.getId(), buffer);
            postSeq.setParentId(parent.getId());
        }
        if (StringUtils.isNotBlank(buffer))
            postSeq.setId(tmp.getId());
    }

    private void chkParent(Long parentId, StringBuffer buffer) {
        if (null == parentId) return;
        PostSeqDTO dto = baseMapper.getPostSeqDTO(parentId);
        if (null != dto && null != dto.getParent() && null != dto.getParent().getParent())
            buffer.append("；" + dto.getPostSeqName() + "为3级岗位序列，不能再创建子级");
    }

    @Override
    public Function<PostSeq, String> getNameMapper() {
        return PostSeq::getPostSeqName;
    }

    @Override
    public Function<PostSeq, Long> getGroupIdMapper() {
        return PostSeq::getGroupId;
    }
}
