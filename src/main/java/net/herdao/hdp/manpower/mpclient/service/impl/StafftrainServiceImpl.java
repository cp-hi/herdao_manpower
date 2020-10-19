 
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.mapper.StafftrainMapper;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Service
@AllArgsConstructor
public class StafftrainServiceImpl extends ServiceImpl<StafftrainMapper, Stafftrain> implements StafftrainService {

    @Override
    public Page<StafftrainDTO> findStaffTrainPage(Page<StafftrainDTO> page, String searchText) {
        Page<StafftrainDTO> list = this.baseMapper.findStaffTrainPage(page, searchText);
        return list;
    }

    @Override
    public List<StafftrainDTO> findStaffTrain(String searchText) {
        List<StafftrainDTO> list = this.baseMapper.findStaffTrain(searchText);
        return list;
    }
}
