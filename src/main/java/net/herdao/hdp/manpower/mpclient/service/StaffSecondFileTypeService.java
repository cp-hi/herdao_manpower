

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFileTypeDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;

import java.util.List;

/**
 * 员工附件二级分类
 *
 * @author andy
 * @date 2020-09-29 11:17:03
 */
public interface StaffSecondFileTypeService extends IService<StaffSecondFileType> {
    /**
     * 员工附件分类分页
     * @param page
     * @param entity
     * @return
     */
    Page<StaffFileTypeDTO> findStaffFileTypePage(Page<StaffFileTypeDTO> page, StaffFileTypeDTO entity);

    /**
     * 新增员工附件分类
     * @param entity
     * @return
     */
    @Override
    boolean saveOrUpdate(StaffSecondFileType entity);

    /**
     * 删除员工附件分类
     * @param entity
     * @return
     */
    boolean delEntity(StaffSecondFileType entity);


    /**
     * 员工附件分类
     * @param entity
     * @return
     */
    List<StaffFileTypeDTO> findStaffFileType(String type,Long bizId);

}
