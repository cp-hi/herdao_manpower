

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFileTypeDto;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工附件二级分类
 *
 * @author andy
 * @date 2020-09-29 11:17:03
 */
@Mapper
public interface StaffSecondFileTypeMapper extends BaseMapper<StaffSecondFileType> {
    /**
     * 员工附件分类分页
     * @param page
     * @param entity
     * @return
     */
    Page<StaffFileTypeDto> findStaffFileTypePage(Page<StaffFileTypeDto> page, @Param("entity") StaffFileTypeDto entity);

    /**
     * 员工附件分类
     * @param entity
     * @return
     */
    List<StaffFileTypeDto> findStaffFileType(@Param("entity") StaffFileTypeDto entity);
}
