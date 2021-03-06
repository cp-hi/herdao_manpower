
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFamilyDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.FamilyStatusListDTO;
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
	IPage findFamilyStatusPage(Page<FamilyStatusListDTO> page,FamilyStatusListDTO familyStatusListDTO, String searchText);

    /**
     * 员工家庭情况
     * @param searchText
     * @return
     */
    List<FamilyStatusVO> findFamilyStatus(FamilyStatusListDTO familyStatusListDTO,String searchText);

    /**
     * 花名册-家庭情况
     * @param staffid
     * @return
     */
    List<StaffFamilyDTO> findStaffFamilyStatus(String staffid);

    @Override
    boolean saveOrUpdate(Familystatus familystatus);

}
