package net.herdao.hdp.manpower.mpclient.mapper;

import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostSeqMapper extends EntityMapper<PostSeq> {

    List<Map> postSeqList(Long groupId);

    PostSeqDTO getPostSeqDTO(Long id);
}
