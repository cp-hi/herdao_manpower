
package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.StaffFileDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 员工附件表
 *
 * @author liang
 * @date 2020-09-30 10:39:45
 */
@Mapper
public interface StaffFileMapper extends BaseMapper<StaffFile> {
    /**
     * 员工附件分页
     * @param page
     * @param entity
     * @return
     */
    Page<StaffFileDTO> findStaffFilePage(Page<StaffFileDTO> page, @Param("entity") StaffFileDTO entity);
}
