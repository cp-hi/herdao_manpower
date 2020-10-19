package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostStaffDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.mapper.PostMapper;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName PostServiceImpl
 * @Description PostServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    final PipelineMapper pipelineMapper;
    final SectionMapper sectionMapper;

    @Override
    public IPage<PostDTO> page(Page<PostDTO> page, Post post) {
        IPage<PostDTO> p = baseMapper.page(page, post);
        return p;
    }

    @Override
    public List<Map> postList(Long groupId) {
        return baseMapper.postList(groupId);
    }


    @Override
    public void saveVerify(Post post) {
        if (baseMapper.chkDuplicatePostCode(post)) {
            throw new RuntimeException("岗位编码重复了");
        }
        if (baseMapper.chkDuplicatePostName(post)) {
            throw new RuntimeException("岗位名称重复了");
        }
    }

    @Override
    public Map getPostStaffInfo(Long postId) {
        Map map = new LinkedHashMap();
        Integer onJobs = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and p.id =" + postId);
        Integer fullTimes = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '1' and p.id = " + postId);
        Integer partTimes = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '2' and p.id = " + postId);
        Integer exchanges = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '4' and p.id = " + postId);

        //TODO 未实现以下3个统计
        Integer interns = 0; //实习
        Integer probation = 0;//试用
        Integer authorized = 0;//编制

//        map.put("onJobs", new HashMap() {{  put("name", "在职");   put("value", onJobs);  }});//在职
//        map.put("interns", new HashMap() {{  put("name", "实习");   put("value", interns);  }});//实习
//        map.put("fullTimes", new HashMap() {{  put("name", "全职");   put("value", fullTimes);  }});//全职
//        map.put("partTimes", new HashMap() {{  put("name", "兼职");   put("value", partTimes);  }});//兼职
//        map.put("exchanges", new HashMap() {{  put("name", "交流");   put("value", exchanges);  }});//交流
//        map.put("probation", new HashMap() {{  put("name", "试用");   put("value", probation);  }});//试用
//        map.put("authorized", new HashMap() {{  put("name", "编制");   put("value", authorized);  }});//编制

        List<Map> staffInfo = new ArrayList<>();
        staffInfo.add(new HashMap() {{  put("name", "在职");   put("value", onJobs);  }});
        staffInfo.add(new HashMap() {{  put("name", "实习");   put("value", interns);  }});
        staffInfo.add(new HashMap() {{  put("name", "全职");   put("value", fullTimes);  }});
        staffInfo.add(new HashMap() {{  put("name", "兼职");   put("value", partTimes);  }});
        staffInfo.add(new HashMap() {{  put("name", "交流");   put("value", exchanges);  }});
        staffInfo.add(new HashMap() {{  put("name", "试用");   put("value", probation);  }});
        staffInfo.add(new HashMap() {{  put("name", "编制");   put("value", authorized);  }});

        map.put("staffInfo",staffInfo);
        
        //年龄分布
        List<Map<String, BigDecimal>> ages = baseMapper.getPostStaffAges(postId);
        map.put("age1", ages.size() > 0 ? ages.get(0).get("age1") : 0);
        map.put("age2", ages.size() > 0 ? ages.get(0).get("age2") : 0);
        map.put("age3", ages.size() > 0 ? ages.get(0).get("age3") : 0);
        map.put("age4", ages.size() > 0 ? ages.get(0).get("age4") : 0);
        map.put("age5", ages.size() > 0 ? ages.get(0).get("age5") : 0);
        map.put("age6", ages.size() > 0 ? ages.get(0).get("age6") : 0);

        return map;
    }

    @Override
    public List<PostDetailDTO> getPostDetails(Long postId, String operation, String size) {
        String limit = "";
        if (!"download".equals(operation)) {
            if (StringUtils.isBlank(size)) {
                size = "10";
            }
            limit = "limit " + size;
        }
        return baseMapper.getPostDetails(postId, limit);
    }

    @Override
    public List<PostStaffDTO> getPostStaffs(Long postId, String operation, String size) {
        String limit = "";
        if (!"download".equals(operation)) {
            if (StringUtils.isBlank(size)) {
                size = "10";
            }
            limit = "limit " + size;
        }
        return baseMapper.getPostStaffs(postId, limit);
    }
}
