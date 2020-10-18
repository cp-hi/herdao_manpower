package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostSeqMapper extends BaseMapper<PostSeq> {

    List<Map> postSeqList(Long groupId);

    IPage<PostSeqDTO> page(Page<PostSeqDTO> page, @Param("searchTxt") String searchTxt);

    Boolean chkDuplicatePostSeqName(PostSeq postSeq);

    Boolean chkDuplicatePostSeqCode(PostSeq postSeq);
}
