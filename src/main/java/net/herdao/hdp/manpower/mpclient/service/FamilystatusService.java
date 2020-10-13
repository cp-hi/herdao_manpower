
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import org.apache.ibatis.annotations.Param;

/**
 * 员工家庭成员
 *
 * @author andy
 * @date 2020-09-23 10:53:08
 */
public interface FamilystatusService extends IService<Familystatus> {

    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    Page<Familystatus> findFamilyStatusPage(Page<Familystatus> page, String orgId,  String staffName,  String staffCode);
}
