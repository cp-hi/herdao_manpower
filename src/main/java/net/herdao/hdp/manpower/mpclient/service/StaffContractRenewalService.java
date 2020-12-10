package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffContractRenewalDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffContractRenewal;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/12/7 9:25 上午
 */
public interface StaffContractRenewalService extends IService<StaffContractRenewal> {
    Page<StaffContractRenewalPageVO> pageStaffRenewContract(Page page, String searchText, Long orgId, String status);

    Long affirmStart(Long id, SaveStaffContractRenewalDTO dto) throws Exception;

    Long affirm(Long id) throws Exception;

    Long add(SaveStaffContractRenewalDTO dto);

    Long updateInfo(Long id, SaveStaffContractRenewalDTO dto) throws Exception;

    StaffContractRenewalInfoVO getDetail(Long id);
}
