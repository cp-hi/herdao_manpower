package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffLeavePostApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Liu Chang
 * @Date 2020/12/3 5:02 下午
 */
@Mapper
public interface StaffLeavePostMapper extends BaseMapper<StaffLeavePostApprove> {
    public Page<StaffLeavePostPageVO> findStaffLeavePostPage(Page page, String searchText, Long orgId, String status);
}
