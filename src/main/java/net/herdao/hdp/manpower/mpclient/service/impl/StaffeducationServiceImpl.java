
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffeducationListDto;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.mapper.StaffeducationMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffeducationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:22:28
 */
@Service
@AllArgsConstructor
public class StaffeducationServiceImpl extends ServiceImpl<StaffeducationMapper, Staffeducation> implements StaffeducationService {
    private RemoteUserService remoteUserService;

    @Override
    public Page<Staffeducation> findStaffEducationPage(Page<Staffeducation> page, String orgId, String staffName, String staffCode) {
        Page<Staffeducation> pageResult = this.baseMapper.findStaffEducationPage(page, orgId, staffName, staffCode);
        return pageResult;
    }

    @Override
    public Boolean saveEdu(Staffeducation staffeducation) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        staffeducation.setCreatorCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        staffeducation.setCreatedTime(now);
        boolean status = super.save(staffeducation);
        return status;
    }

    @Override
    public boolean updateEdu(Staffeducation staffeducation) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        staffeducation.setModifierCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        staffeducation.setModifiedTime(now);
        boolean status = super.updateById(staffeducation);
        return status;
    }

    @Override
    public List<StaffeducationListDto> findStaffEducation(String orgId, String staffName, String staffCode) {
        List<StaffeducationListDto> list = this.baseMapper.findStaffEducation(orgId, staffName, staffCode);
        return list;
    }
}
