package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqListVO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostSeqMapper extends EntityMapper<PostSeq> {

    List<Map> postSeqList(Long groupId);

    PostSeqDTO getPostSeqDTO(Long id);

    String selectEntityName(Long id);
}
