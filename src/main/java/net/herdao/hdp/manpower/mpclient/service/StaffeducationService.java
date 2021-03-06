
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEducationDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;

import java.util.List;

/**
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:22:28
 */
public interface StaffeducationService extends HdpService<Staffeducation> {

    /**
     * 新增员工教育经历
     * @param staffeducation
     * @return
     */
     Boolean saveEdu(Staffeducation staffeducation);

    /**
     * 更新员工教育经历
     * @param staffeducation
     * @return
     */
     boolean updateEdu(Staffeducation staffeducation);


    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @return
     */
     IPage findStaffEducationPage(Page<StaffEducationDTO> page,StaffEducationDTO staffEducationDTO, String searchText);

    /**
     * 员工教育经历
     * @param searchText 关键字搜索
     * @param staffId 员工工号
     * @return
     */
    List<StaffEducationDTO> findStaffEducation(StaffEducationDTO staffEducationDTO, String searchText);

}
