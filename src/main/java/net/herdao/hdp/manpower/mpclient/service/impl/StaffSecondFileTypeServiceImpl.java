
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.StaffFileTypeDto;
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import net.herdao.hdp.manpower.mpclient.mapper.StaffSecondFileTypeMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffSecondFileTypeService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 员工附件二级分类
 *
 * @author andy
 * @date 2020-09-29 11:17:03
 */
@Service
public class StaffSecondFileTypeServiceImpl extends ServiceImpl<StaffSecondFileTypeMapper, StaffSecondFileType> implements StaffSecondFileTypeService {


    @Override
    public Page<StaffFileTypeDto> findStaffFileTypePage(Page<StaffFileTypeDto> page, StaffFileTypeDto entity) {
        Page<StaffFileTypeDto> pageResult = this.baseMapper.findStaffFileTypePage(page, entity);
        return pageResult;
    }

    @Override
    public Boolean saveStaffFileType(StaffSecondFileType entity) {
        entity.setCreatorCode(UserUtils.getUserId().toString());
        entity.setCreatedTime(DateUtils.getNowLocalDateTime());
        boolean status = super.save(entity);
        return status;
    }

    @Override
    public Boolean updateStaffFileType(StaffSecondFileType entity) {
        entity.setModifierCode(UserUtils.getUserId().toString());
        entity.setModifiedTime(DateUtils.getNowLocalDateTime());
        boolean status = super.updateById(entity);
        return status;
    }
}
