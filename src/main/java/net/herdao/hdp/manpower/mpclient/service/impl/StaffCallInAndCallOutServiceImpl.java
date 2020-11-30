package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveTypeConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.mapper.StaffTransferApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffCallInAndCallOutService;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 2:37 下午
 */
@Service
public class StaffCallInAndCallOutServiceImpl extends ServiceImpl<StaffTransferApproveMapper, StaffTransferApprove> implements StaffCallInAndCallOutService {

    @Autowired
    private StaffTransferApproveMapper mapper;

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }

    @Override
    public Long affirmStart(Long id) throws Exception {
        QueryWrapper<StaffTransferApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN)
                .eq("transfer_type", StaffChangesApproveTypeConstants.CALL_IN_AND_CALL_OUT);
        StaffTransferApprove changes = mapper.selectOne(queryWrapper);
        if (changes == null) {
            throw new Exception("该记录不可修改");
        }
        changes.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    public Page<StaffCallInAndCallOutPageVO> pageStaffCallInAndCallOut(Page page, String searchText, Long orgId, String status) {
        return this.baseMapper.findStaffCallInAndCallOutPage(page, searchText, orgId, status);
    }
}
