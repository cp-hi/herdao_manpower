package net.herdao.hdp.manpower.mpmobile.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpmobile.dto.PayCardInformationDTO;
import net.herdao.hdp.manpower.mpmobile.entity.PayCardInformation;
import net.herdao.hdp.manpower.mpmobile.mapper.PayCardInformationMapper;
import net.herdao.hdp.manpower.mpmobile.service.PayCardInformationService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PayCardInformationServiceImpl extends ServiceImpl<PayCardInformationMapper, PayCardInformation> implements PayCardInformationService {


    /*    *
     * 通过id查询工资卡信息表
     * @param id id 业务表ID (例如：人才表的主键ID)
     * @return R
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
    public Long insertPayCard(PayCardInformationDTO dto) throws Exception {
        PayCardInformation payCardInformation = new PayCardInformation();
        SysUser sysUser = SysUserUtils.getSysUser();
        BeanUtils.copyProperties(dto,payCardInformation);
        PayCardInformation payCardInformations = this.baseMapper.
                selectOne(new QueryWrapper<PayCardInformation>().lambda().
                        eq(PayCardInformation::getBizId, dto.getBizId()));
        //不为空 就修改
        if (payCardInformations != null){
            payCardInformation.setModifierTime(LocalDateTime.now());
            if (ObjectUtil.isNotNull(sysUser)) {
                payCardInformation.setModifierCode(sysUser.getUsername());
                payCardInformation.setModifierName(sysUser.getAliasName());
            }
            this.baseMapper.update(payCardInformation,new LambdaUpdateWrapper<PayCardInformation>().
                    eq(PayCardInformation::getBizId, dto.getBizId()));
            return payCardInformation.getId();
        }

        //否则新增
        payCardInformation.setCreatorTime(LocalDateTime.now());
        if (ObjectUtil.isNotNull(sysUser)) {
            payCardInformation.setCreatorCode(sysUser.getUsername());
            payCardInformation.setCreatorName(sysUser.getAliasName());
        }
        payCardInformation.setDelFlag(false);
        this.baseMapper.insert(payCardInformation);
        return payCardInformation.getId();
    }
}