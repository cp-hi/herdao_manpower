package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffPromoteApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromotePageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Liu Chang
 * @Date 2020/11/28 6:00 下午
 */

@Mapper
public interface StaffPromoteApproveMapper extends BaseMapper<StaffPromoteApprove> {

    public Page<StaffPromotePageVO> findStaffPromotePage(Page page,
                                                         @Param("searchText") String searchText,
                                                         @Param("orgId")Long orgId,
                                                         @Param("status") String status);
}
