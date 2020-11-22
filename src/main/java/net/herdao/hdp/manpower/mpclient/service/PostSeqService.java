package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqTree;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;

import java.util.List;
import java.util.Map;

public interface PostSeqService extends EntityService<PostSeq> {

    List<Map> postSeqList(Long groupId);
    /**
     * 查询岗位序列树
     */
    List<PostSeqTree> getTree(Long groupId,Long parentId,Boolean lazy);
}
