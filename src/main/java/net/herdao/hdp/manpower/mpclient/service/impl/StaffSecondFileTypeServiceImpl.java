
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFileTypeDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import net.herdao.hdp.manpower.mpclient.mapper.StaffSecondFileTypeMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffSecondFileTypeService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工附件二级分类
 *
 * @author andy
 * @date 2020-09-29 11:17:03
 */
@Service
public class StaffSecondFileTypeServiceImpl extends ServiceImpl<StaffSecondFileTypeMapper, StaffSecondFileType> implements StaffSecondFileTypeService {

    @Override
    public Page<StaffFileTypeDTO> findStaffFileTypePage(Page<StaffFileTypeDTO> page, StaffFileTypeDTO entity) {
        Page<StaffFileTypeDTO> pageResult = this.baseMapper.findStaffFileTypePage(page, entity);
        return pageResult;
    }

    @Override
    @OperationEntity(operation = "保存员工附件二级分类", clazz = StaffSecondFileType.class)
    public boolean saveOrUpdate(StaffSecondFileType entity) {
        boolean status= super.saveOrUpdate(entity);
        return status;
    }

    @Override
    @OperationEntity(operation = "删除员工附件二级分类", clazz = StaffSecondFileType.class)
    public boolean delEntity(StaffSecondFileType entity) {
        boolean status= super.removeById(entity);
        return status;
    }

    @Override
    public List<StaffFileTypeDTO> findStaffFileType(StaffFileTypeDTO entity) {
        List<StaffFileTypeDTO> list = this.baseMapper.findStaffFileType(entity);
        return list;
    }




}
