package com.hedao.hdp.mpclient.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hedao.hdp.mpclient.oa.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
   List<Map> postList();
    Boolean chkDuplicatePostName(Post post);
    Boolean chkDuplicatePostCode(Post post);
}
