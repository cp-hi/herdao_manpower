package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.mpclient.entity.Post;
import net.herdao.hdp.mpclient.entity.PostSeq;
import net.herdao.hdp.mpclient.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostSeqMapper extends BaseMapper<PostSeq> {

    List<Map> postSeqList();

    IPage<PostSeq> query(Page<PostSeq> page, @Param("searchTxt") String searchTxt);

    Boolean chkDuplicatePostSeqName(PostSeq postSeq);

    Boolean chkDuplicatePostSeqCode(PostSeq postSeq);
}
