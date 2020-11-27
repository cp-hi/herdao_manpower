package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffChanges;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 4:22 下午
 */
@Mapper
public interface StaffChangesMapper extends BaseMapper<StaffChanges> {
    public Page<StaffTransferPageVO> findStaffTransferPage(Page page,
                                                           @Param("searchText") String searchText,
                                                           @Param("searchText")Long orgId,
                                                           @Param("status") String status);
}
