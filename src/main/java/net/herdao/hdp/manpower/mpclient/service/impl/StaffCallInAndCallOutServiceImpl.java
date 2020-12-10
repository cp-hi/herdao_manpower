package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.mapper.StaffTransferApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffCallInAndCallOutService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
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
    public Long affirm(Long id) throws Exception {
        QueryWrapper<StaffTransferApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffTransferApprove changes = mapper.selectOne(queryWrapper);
        if (changes == null) {
            throw new Exception("该调入、调出审批记录不可修改");
        }
        changes.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    public Page<StaffCallInAndCallOutPageVO> pageStaffCallInAndCallOut(Page page, String searchText, Long orgId, String status, String type) {
        Page<StaffCallInAndCallOutPage> staffCallInAndCallOutPage = this.baseMapper.findStaffCallInAndCallOutPage(page, searchText, orgId, status, type);
        return convert2PageVO(staffCallInAndCallOutPage);
    }

    /**
     * 适配数据库中获取的原始数据为传给前端的 vo
     *
     * @param page
     * @return
     */
    private Page<StaffCallInAndCallOutPageVO> convert2PageVO(Page<StaffCallInAndCallOutPage> page) {
        Page<StaffCallInAndCallOutPageVO> pageVO = new Page<>();
        List<StaffCallInAndCallOutPageVO> list = new ArrayList<>();
        for (StaffCallInAndCallOutPage record : page.getRecords()) {
            StaffCallInAndCallOutPageVO vo = new StaffCallInAndCallOutPageVO();
            BeanUtils.copyProperties(record, vo);
            if (record.getTransStartDate() != null) {
                vo.setTransStartDate(LocalDateTimeUtils.convert2Long(record.getTransStartDate()));
            }
            String updatedAt = LocalDateTimeUtils.convert2String(record.getModifierTime());
            vo.setUpdateInfo(MessageFormat.format("{0} 于 {1} 更新", record.getModifierName(), updatedAt));
            list.add(vo);
        }
        pageVO.setRecords(list);
        return pageVO;
    }
}
