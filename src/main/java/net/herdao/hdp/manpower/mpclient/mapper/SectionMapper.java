package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionListVO;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ljan
 */
@Mapper
public interface SectionMapper extends EntityMapper<Section> {
    List<Map> sectionList(Long groupId);

    List<String> getSectionCodesByGroupId(Long groupId);

}
