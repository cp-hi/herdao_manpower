
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.StaffFileDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffFile;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import net.herdao.hdp.manpower.mpclient.mapper.StaffFileMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffFileService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.stereotype.Service;

/**
 * 员工附件表
 *
 * @author liang
 * @date 2020-09-30 10:39:45
 */
@Service
public class StaffFileServiceImpl extends ServiceImpl<StaffFileMapper, StaffFile> implements StaffFileService {

    @Override
    public Page<StaffFileDTO> findStaffFilePage(Page<StaffFileDTO> page, StaffFileDTO entity) {
        Page<StaffFileDTO> pageResult = this.baseMapper.findStaffFilePage(page, entity);
        return pageResult;
    }

    @Override
    @OperationEntity(operation = "保存或新增", clazz = StaffFile.class)
    public boolean saveOrUpdate(StaffFile entity) {
        boolean status =  super.saveOrUpdate(entity);
        return status;
    }

    @Override
    @OperationEntity(operation = "删除", clazz = StaffSecondFileType.class)
    public boolean delEntity(StaffFile entity) {
        boolean status =  super.removeById(entity);
        return status;
    }


}
