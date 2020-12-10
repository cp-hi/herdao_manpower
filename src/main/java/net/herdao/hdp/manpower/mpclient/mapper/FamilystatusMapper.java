
package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFamilyDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.FamilyStatusListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param searchText
     * @return
     */
    List<FamilyStatusListDTO> findFamilyStatusPage(Map<String, Object> map);


    /**
     * 员工家庭情况
     * @param searchText
     * @return
     */
    List<FamilyStatusVO> findFamilyStatus(@Param("query") FamilyStatusListDTO familyStatusListDTO, @Param("searchText") String searchText);

    /**
     * 花名册-家庭情况
     * @param staffid
     * @return
     */
    List<StaffFamilyDTO> findStaffFamilyStatus(String staffid);
}
