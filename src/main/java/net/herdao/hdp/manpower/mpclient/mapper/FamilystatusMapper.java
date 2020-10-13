
package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 员工家庭成员
 *
 * @author andy
 * @date 2020-09-23 10:53:08
 */
@Mapper
public interface FamilystatusMapper extends BaseMapper<Familystatus> {
    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    Page<Familystatus> findFamilyStatusPage(Page<Familystatus> page, @Param("orgId")  String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);
}
