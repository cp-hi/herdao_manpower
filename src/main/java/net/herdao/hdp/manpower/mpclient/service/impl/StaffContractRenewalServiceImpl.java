package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffContractRenewalDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffContractRenewal;
import net.herdao.hdp.manpower.mpclient.mapper.StaffRenewContractMapper;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import net.herdao.hdp.manpower.mpclient.service.StaffContractRenewalService;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Liu Chang
 * @Date 2020/12/7 9:26 上午
 */
@Service
public class StaffContractRenewalServiceImpl extends ServiceImpl<StaffRenewContractMapper, StaffContractRenewal> implements StaffContractRenewalService {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StaffRenewContractMapper mapper;

    @Override
    public Page<StaffContractRenewalPageVO> pageStaffRenewContract(Page page, String searchText, Long orgId, String status) {
        return mapper.findStaffContractRenewalPage(page, searchText, orgId, status);
    }

    @Override
    public Long affirm(Long id, SaveStaffContractRenewalDTO dto) throws Exception {
        if (id != null) {
            updateInfo(id, dto);
        } else {
            add(dto);
        }
        StaffContractRenewal entity = mapper.selectById(id);
        entity.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        return id;
    }

    @Override
    public Long add(SaveStaffContractRenewalDTO dto) {
        StaffContractRenewal entity = new StaffContractRenewal();
        BeanUtils.copyProperties(dto, entity);
        entity.setDelFlag(false);
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public Long updateInfo(Long id, SaveStaffContractRenewalDTO dto) throws Exception {
        QueryWrapper<StaffContractRenewal> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffContractRenewal entity = mapper.selectOne(wrapper);
        if (entity != null) {
            BeanUtils.copyProperties(dto, entity);
            mapper.updateById(entity);
            return id;
        } else {
            throw new Exception("该记录不可更新");
        }
    }

    @Override
    public StaffContractRenewalInfoVO getDetail(Long id) {
        StaffContractRenewal staffContractRenewal = mapper.selectById(id);
        StaffContractRenewalInfoVO vo = new StaffContractRenewalInfoVO();
        BeanUtils.copyProperties(staffContractRenewal, vo);
        String renewalCompanyName = companyService.getById(vo.getRenewalCompanyId()).getCompanyName();
        vo.setRenewalCompanyName(renewalCompanyName);
        return null;
    }
}
