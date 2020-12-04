package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffLeavePostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffLeavePostApprove;
import net.herdao.hdp.manpower.mpclient.mapper.StaffLeavePostMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffLeaveService;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/3 4:59 下午
 */
@Service
public class StaffLeavePostServiceImpl extends ServiceImpl<StaffLeavePostMapper, StaffLeavePostApprove> implements StaffLeaveService {

    @Autowired
    private StaffLeavePostMapper mapper;
    /**
     * TODO:: 员工的就职信息应该从数据库中读数，然后和传参进行匹配（或者直接用从数据库中读出来的数据传入）
     */

    @Override
    public Page<StaffLeavePostPageVO> pageStaffLeave(Page page, String searchText, Long orgId, String status) {
        return null;
    }

    @Override
    public Long affirmStart(Long id, SaveStaffTransferInfoDTO dto) {
        return null;
    }

    @Override
    public Long updateStaffLeave(Long id, SaveStaffLeavePostDTO dto) {
        return null;
    }

    @Override
    public StaffLeavePostInfoVO getStaffLeave(Long id) {
        return null;
    }

    @Override
    public Long insert(SaveStaffLeavePostDTO dto) {
        StaffLeavePostApprove entity = new StaffLeavePostApprove();
        BeanUtils.copyProperties(dto, entity);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }

}
