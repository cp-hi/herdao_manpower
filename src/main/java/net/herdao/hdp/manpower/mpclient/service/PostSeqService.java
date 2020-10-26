package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.entity.PostSeq;

import java.util.List;
import java.util.Map;

public interface PostSeqService extends EntityService<PostSeq> {

    List<Map> postSeqList(Long groupId);

}
