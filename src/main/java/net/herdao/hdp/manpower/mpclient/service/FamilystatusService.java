
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.familyStatus.FamilyStatusListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;

import java.util.List;

/**
 * 员工家庭成员
 *
 * @author andy
 * @date 2020-09-23 10:53:08
 */
public interface FamilystatusService extends HdpService<Familystatus> {
    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    Page<FamilyStatusListDTO> findFamilyStatusPage(Page<FamilyStatusListDTO> page, String searchText);

    /**
     * 员工家庭情况
     * @param searchText
     * @return
     */
    List<FamilyStatusVO> findFamilyStatus(String searchText);

    @Override
    boolean saveOrUpdate(Familystatus familystatus);

    /**
     *  获取批量新增模板备注信息
     * @return
     */
    String getAddRemarks();

    /**
     *  获取批量编辑模板备注信息
     * @return
     */
    String getUpdateRemarks();
}
