package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.comm.UserMsgDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffContractRenewalDTO;
import net.herdao.hdp.manpower.mpclient.entity.Company;
import net.herdao.hdp.manpower.mpclient.entity.StaffContractRenewal;
import net.herdao.hdp.manpower.mpclient.mapper.StaffRenewContractMapper;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import net.herdao.hdp.manpower.mpclient.service.StaffContractRenewalService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.StaffBasicVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/7 9:26 上午
 */
@Service
public class StaffContractRenewalServiceImpl extends ServiceImpl<StaffRenewContractMapper, StaffContractRenewal> implements StaffContractRenewalService {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRenewContractMapper mapper;

    @Override
    public Page<StaffContractRenewalPageVO> pageStaffRenewContract(Page page, String searchText, Long orgId, String status) {
        Page<StaffContractRenewalPage> contractRenewalPage = mapper.findStaffContractRenewalPage(page, searchText, orgId, status);
        return convert2Page(contractRenewalPage);
    }

    private Page<StaffContractRenewalPageVO> convert2Page(Page<StaffContractRenewalPage> page) {
        Page<StaffContractRenewalPageVO> pageVO = new Page<>();
        List<StaffContractRenewalPageVO> list = new ArrayList<>();
        for (StaffContractRenewalPage record : page.getRecords()) {
            StaffContractRenewalPageVO vo = new StaffContractRenewalPageVO();
            BeanUtils.copyProperties(record, vo);
            if (record.getRenewalStartTime() != null) {
                vo.setRenewalStartTime(LocalDateTimeUtils.convert2Long(record.getRenewalStartTime()));
            }
            if (record.getRenewalEndTime() != null) {
                vo.setRenewalEndTime(LocalDateTimeUtils.convert2Long(record.getRenewalEndTime()));
            }
            String updatedAt = LocalDateTimeUtils.convert2String(record.getModifierTime());
            vo.setUpdateInfo(MessageFormat.format("{0} 于 {1} 更新", record.getModifierName(), updatedAt));
            list.add(vo);
        }
        BeanUtils.copyProperties(page, pageVO);
        pageVO.setRecords(list);
        return pageVO;
    }

    @Override
    public Long affirmStart(Long id, SaveStaffContractRenewalDTO dto) throws Exception {
        if (id != null) {
            updateInfo(id, dto);
        } else {
            id = add(dto);
        }
       return affirm(id);
    }

    @Override
    public Long affirm(Long id) throws Exception {
        QueryWrapper<StaffContractRenewal> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffContractRenewal entity = mapper.selectOne(wrapper);
        if(entity == null) {
            throw new Exception("该合同续签审批记录已发起审批，请勿重复操作");
        }
        entity.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(entity);
        return id;
    }

    @Override
    public Long add(SaveStaffContractRenewalDTO dto) {
        StaffContractRenewal entity = initStaffContractRenewalData(dto);

        mapper.insert(entity);
        return entity.getId();
    }

    private StaffContractRenewal initStaffContractRenewalData(SaveStaffContractRenewalDTO dto) {
        StaffContractRenewal entity = new StaffContractRenewal();
        BeanUtils.copyProperties(dto, entity);

        entity.setContractStartTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getContractStartTime()));
        entity.setContractEndTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getContractEndTime()));
        entity.setRenewalStartTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getRenewalStartTime()));
        entity.setRenewalEndTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getRenewalEndTime()));
        entity.setDelFlag(false);
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        return entity;
    }

    @Override
    public Long updateInfo(Long id, SaveStaffContractRenewalDTO dto) throws Exception {
        QueryWrapper<StaffContractRenewal> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffContractRenewal entity = mapper.selectOne(wrapper);
        if (entity != null) {
            BeanUtils.copyProperties(dto, entity);

            entity.setContractStartTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getContractStartTime()));
            entity.setContractEndTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getContractEndTime()));
            entity.setRenewalStartTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getRenewalStartTime()));
            entity.setRenewalEndTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getRenewalEndTime()));

            mapper.updateById(entity);
            return id;
        } else {
            throw new Exception("该记录不可更新");
        }
    }

    @Override
    public StaffContractRenewalInfoVO getDetail(Long id) throws Exception {
        StaffContractRenewal entity = mapper.selectById(id);
        if (entity != null) {
            return convert2InfoVO(entity);
        }
        return null;
    }

    private StaffContractRenewalInfoVO convert2InfoVO(StaffContractRenewal from) throws Exception {
        StaffContractRenewalInfoVO to = new StaffContractRenewalInfoVO();
        BeanUtils.copyProperties(from, to);
        Company renewalCompany = companyService.getById(from.getRenewalCompanyId());
        if (renewalCompany != null) {
            String renewalCompanyName = renewalCompany.getCompanyName();
            to.setRenewalCompanyName(renewalCompanyName);
        }
        to.setContractStartTime(LocalDateTimeUtils.convert2Long(from.getContractStartTime()));
        to.setContractEndTime(LocalDateTimeUtils.convert2Long(from.getContractEndTime()));
        to.setRenewalStartTime(LocalDateTimeUtils.convert2Long(from.getRenewalStartTime()));
        to.setRenewalEndTime(LocalDateTimeUtils.convert2Long(from.getRenewalEndTime()));

        StaffBasicVO staffBasicVO = staffService.selectBasicByUserId(from.getUserId());
        if (staffBasicVO != null) {
            BeanUtils.copyProperties(staffBasicVO, to);
        }

        // 根据用户 id 获取到该用户的当前所在所在部门和岗位
        UserMsgDTO userMsg = userService.getUserMsg(from.getUserId());
        if (userMsg != null) {
            BeanUtils.copyProperties(userMsg, to);
        }
        return to;
    }
}
