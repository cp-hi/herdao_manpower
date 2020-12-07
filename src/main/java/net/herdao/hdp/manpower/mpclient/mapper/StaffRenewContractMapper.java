package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffContractRenewal;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Liu Chang
 * @Date 2020/12/7 9:29 上午
 */
@Mapper
public interface StaffRenewContractMapper extends BaseMapper<StaffContractRenewal> {

    Page<StaffContractRenewalPageVO> findStaffContractRenewalPage(Page page,
                                                                @Param("searchText") String searchText,
                                                                @Param("orgId") Long orgId,
                                                                @Param("status") String status);
}
