
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.StaffeducationListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffEducationDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.vo.StaffeducationVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:22:28
 */
public interface StaffeducationService extends EasyExcelService<Staffeducation> {

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
     * @param staffId 员工工号
     * @return
     */
    Page<StaffEducationDTO> findStaffEducationPage(Page<StaffEducationDTO> page, String searchText, String staffId);

    /**
     * 员工教育经历
     * @param searchText 关键字搜索
     * @param staffId 员工工号
     * @return
     */
    List<StaffEducationDTO> findStaffEducation(String searchText,String staffId);

}
