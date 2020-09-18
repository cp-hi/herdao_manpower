package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.Pipeline;
import net.herdao.hdp.mpclient.entity.PostSeq;

import java.util.List;
import java.util.Map;

public interface PostSeqService extends IService<PostSeq> {
    IPage<PostSeq> page(Page<PostSeq> page, String searchTxt);

    List<Map> postSeqList();

    boolean saveOrUpdate(PostSeq postSeq);
}
