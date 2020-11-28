package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SavaStaffPromoteDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPromoteApprove;
import net.herdao.hdp.manpower.mpclient.mapper.StaffPromoteApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffPromoteService;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromotePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/28 5:59 下午
 */
@Service
public class StaffPromoteServiceImpl extends ServiceImpl<StaffPromoteApproveMapper, StaffPromoteApprove> implements StaffPromoteService {

    @Autowired
    private StaffPromoteApproveMapper mapper;

    @Override
    public Long affirmStart(Long id, SavaStaffPromoteDTO dto) throws Exception {
        return null;
    }

    @Override
    public Long updateInfo(Long id, SavaStaffPromoteDTO dto) throws Exception {
        return null;
    }

    @Override
    public Long saveInfo(SavaStaffPromoteDTO dto) throws Exception {
        return null;
    }

    @Override
    public StaffCallInInfoVO getDetail(Long id) {
        return null;
    }

    @Override
    public Page<StaffPromotePageVO> pageStaffPromote(Page page, String searchText, Long orgId, String status) {
        return mapper.findStaffPromotePage(page, searchText, orgId, status);
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
