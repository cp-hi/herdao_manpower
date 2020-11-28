package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesStatusConstants;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesType;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffChanges;
import net.herdao.hdp.manpower.mpclient.mapper.StaffChangesMapper;
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
public class StaffCallInAndCallOutServiceImpl extends ServiceImpl<StaffChangesMapper, StaffChanges> implements StaffCallInAndCallOutService {

    @Autowired
    private StaffChangesMapper mapper;

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }

    @Override
    public Long affirmStart(Long id) throws Exception {
        QueryWrapper<StaffChanges> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesStatusConstants.FILLING_IN)
                .eq("transfer_type", StaffChangesType.CALL_IN_AND_CALL_OUT);
        StaffChanges changes = mapper.selectOne(queryWrapper);
        if (changes == null) {
            throw new Exception("该记录不可修改");
        }
        changes.setStatus(StaffChangesStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    public Page<StaffCallInAndCallOutPageVO> pageStasffCallInAndCallOut(Page page, String searchText, Long orgId, String status) {
        return this.baseMapper.findStaffCallInAndCallOutPage(page, searchText, orgId, status);
    }
}
