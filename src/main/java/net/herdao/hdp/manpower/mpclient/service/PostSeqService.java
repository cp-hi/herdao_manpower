package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;

import java.util.List;
import java.util.Map;

public interface PostSeqService extends EntityService<PostSeq> {
    IPage<PostSeqDTO> page(Page<PostSeqDTO> page, PostSeq postSeq);

    List<Map> postSeqList(Long groupId);

}
