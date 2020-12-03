package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.herdao.hdp.admin.api.vo.TreeUtil;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqTree;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.mapper.PostSeqMapper;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqBatchVO;
import net.herdao.hdp.manpower.sys.cache.GroupCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        chkParent(postSeq.getParentId(), buffer);
        super.saveVerify(postSeq, buffer);
        PostSeq parent = baseMapper.selectById(postSeq.getParentId());
        if (null != parent && !postSeq.getGroupId().equals(parent.getGroupId()))
            buffer.append("；“" + postSeq.getPostSeqName() + "”与父序列“" + parent.getPostSeqName() + "”不在同一个集团");
        throwErrMsg(buffer);
    }

    @Override
    public void batchAddVerify(PostSeq postSeq, Object excelObj, StringBuffer buffer) {
        PostSeqBatchVO excel = (PostSeqBatchVO) excelObj;
        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
        if (null != group) postSeq.setGroupId(group.getId());
        chkEntityExists(excel.getPostSeqName(), group.getId(), false, buffer);
        PostSeq parent = this.getParent(excel.getParentName(), group.getId(), false, buffer);
        if (null != parent) {
            chkParent(parent.getId(), buffer);
            postSeq.setParentId(parent.getId());
        }
    }

    @Override
    public void batchUpdateVerify(PostSeq postSeq, Object excelObj, StringBuffer buffer) {
        PostSeqBatchVO excel = (PostSeqBatchVO) excelObj;

        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
        if (null != group) postSeq.setGroupId(group.getId());
        PostSeq tmp = chkEntityExists(excel.getPostSeqName(), group.getId(), true, buffer);
        PostSeq parent = this.getParent(excel.getParentName(), group.getId(), false, buffer);

        if (null != parent) {
            chkParent(parent.getId(), buffer);
            postSeq.setParentId(parent.getId());
        }
        if (StringUtils.isBlank(buffer))
            postSeq.setId(tmp.getId());
    }

    private PostSeq getParent(String name, Long groupId, boolean need, StringBuffer buffer) {
        if (StringUtils.isBlank(name)) {
            if (need) buffer.append("；岗位序列父节点名称不能为空");
            return null;
        }
        PostSeq t = baseMapper.getEntityByName(name, groupId);
        String errMsg = "";
        if (need && null == t)  //需要它但它为空
            errMsg = "；该集团下不存在此父节点：" + name;
        buffer.append(errMsg);
        return t;
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

    @Override
    public List<PostSeqTree> getTree(Long groupId, Long parentId, Boolean lazy) {
        Long parent = parentId == null ? -1l : parentId;
        if (lazy != null && lazy) {
            if (parentId == null) {
                return TreeUtil.build(this.list(Wrappers.<PostSeq>lambdaQuery().eq(PostSeq::getGroupId, groupId)
                        .isNull(PostSeq::getParentId)).stream().map(PostSeqTree::new).collect(Collectors.toList()), parent);
            }
            return TreeUtil.build(this.list(Wrappers.<PostSeq>lambdaQuery().eq(PostSeq::getGroupId, groupId)
                    .eq(PostSeq::getParentId, parentId)).stream().map(PostSeqTree::new).collect(Collectors.toList()), parent);
        }
        return TreeUtil.build(this.list(Wrappers.<PostSeq>lambdaQuery().eq(PostSeq::getGroupId, groupId))
                .stream().map(PostSeqTree::new).collect(Collectors.toList()), parent);
    }

}
