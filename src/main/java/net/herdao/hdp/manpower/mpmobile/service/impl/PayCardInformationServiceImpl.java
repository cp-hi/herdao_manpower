package net.herdao.hdp.manpower.mpmobile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpmobile.dto.PayCardInformationDTO;
import net.herdao.hdp.manpower.mpmobile.entity.PayCardInformation;
import net.herdao.hdp.manpower.mpmobile.mapper.PayCardInformationMapper;
import net.herdao.hdp.manpower.mpmobile.service.PayCardInformationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PayCardInformationServiceImpl extends ServiceImpl<PayCardInformationMapper, PayCardInformation> implements PayCardInformationService {


    /*    *
     * 通过id查询工资卡信息表
     * @param id id   业务表ID
     * @return R
     *
     * */
    @Override
    public PayCardInformation getCardById(Long id) {
        PayCardInformation payCardInformation = this.baseMapper.selectOne(new QueryWrapper<PayCardInformation>().select("cardholder", "location_of_bank",
                "branch_name_of_receiving_bank", "payroll_bank_name", "salary_bank_account").
                lambda().eq(PayCardInformation::getBizId, id));
        return payCardInformation;
    }

    /*
     *
     * 新增工资卡信息表
     * @param payCardInformation 工资卡信息表
     * @return R
     * */
    @Override
    public Long insertPayCard(PayCardInformationDTO dto) {
        PayCardInformation payCardInformation = new PayCardInformation();
        BeanUtils.copyProperties(dto,payCardInformation);
        payCardInformation.setDelFlag(false);
        this.baseMapper.insert(payCardInformation);
        return payCardInformation.getId();
    }
}