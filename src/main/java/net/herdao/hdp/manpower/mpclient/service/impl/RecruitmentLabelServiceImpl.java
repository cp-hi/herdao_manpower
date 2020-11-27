
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentLabel;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentLabelMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentLabelService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 人才标签
 *
 * @author Andy
 * @date 2020-11-25 14:40:54
 */
@Service
public class RecruitmentLabelServiceImpl extends ServiceImpl<RecruitmentLabelMapper, RecruitmentLabel> implements RecruitmentLabelService {

    @Override
    @OperationEntity(operation = "人才标签-新增保存",module="人才简历", clazz = RecruitmentLabel.class)
    public boolean save(RecruitmentLabel entity) {
        SysUser sysUser = SysUserUtils.getSysUser();
        entity.setCreatorTime(LocalDateTime.now());
        entity.setCreatorCode(sysUser.getUsername());
        entity.setCreatorName(sysUser.getAliasName());
        super.save(entity);
        return false;
    }
}
