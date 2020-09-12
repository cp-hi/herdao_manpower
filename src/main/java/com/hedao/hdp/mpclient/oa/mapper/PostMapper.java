package com.hedao.hdp.mpclient.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hedao.hdp.mpclient.oa.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
    Boolean chkDuplicatePostName(Post post);
    Boolean chkDuplicatePostCode(Post post);
}
