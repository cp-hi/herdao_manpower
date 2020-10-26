package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqListDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostSeqMapper extends BaseMapper<PostSeq> {

    List<Map> postSeqList(Long groupId);

    IPage<PostSeqListDTO> page(Page<PostSeqListDTO> page, @Param("postSeq")PostSeq postSeq);

    Boolean chkDuplicatePostSeqName(PostSeq postSeq);

    Boolean chkDuplicatePostSeqCode(PostSeq postSeq);
}
