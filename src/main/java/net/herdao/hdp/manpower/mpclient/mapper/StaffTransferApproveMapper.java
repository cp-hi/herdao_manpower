package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 4:22 下午
 */
@Mapper
public interface StaffTransferApproveMapper extends BaseMapper<StaffTransferApprove> {
    Page<StaffTransferPageVO> findStaffTransferPage(Page page,
                                                    @Param("searchText") String searchText,
                                                    @Param("orgId") Long orgId,
                                                    @Param("status") String status);

    Page<StaffCallInAndCallOutPageVO>findStaffCallInAndCallOutPage(Page page,
                                                                   @Param("searchText") String searchText,
                                                                   @Param("orgId") Long orgId,
                                                                   @Param("status") String status, @Param("type") String type);


}
