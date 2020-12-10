package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffLeavePostDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffLeavePostApprove;
import net.herdao.hdp.manpower.mpclient.mapper.StaffLeavePostMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffLeaveService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
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
    public Page<StaffLeavePostPageVO> pageStaffLeavePost(Page page, String searchText, Long orgId, String status) {

        Page<StaffLeavePostPage> leavePostPage = this.baseMapper.findStaffLeavePostPage(page, searchText, orgId, status);
        return convert2PageVO(leavePostPage);
    }

    private Page<StaffLeavePostPageVO> convert2PageVO(Page<StaffLeavePostPage> page) {
        Page<StaffLeavePostPageVO> pageVO = new Page<>();
        List<StaffLeavePostPageVO> list = new ArrayList<>();
        for (StaffLeavePostPage record : page.getRecords()) {
            StaffLeavePostPageVO vo = new StaffLeavePostPageVO();
            BeanUtils.copyProperties(record, vo);
            if (record.getEntryTime() != null) {
                vo.setEntryTime(LocalDateTimeUtils.convert2Long(record.getEntryTime()));
            }
            if (record.getLeaveTime() != null) {
                vo.setLeaveTime(LocalDateTimeUtils.convert2Long(record.getLeaveTime()));
            }
            String updatedAt = LocalDateTimeUtils.convert2String(record.getModifierTime());
            vo.setUpdateInfo(MessageFormat.format("{0} 于 {1} 更新", record.getModifierName(), updatedAt));
            list.add(vo);
        }
        pageVO.setRecords(list);
        return pageVO;
    }

    @Override
    @Transactional
    public Long affirmStart(Long id, SaveStaffLeavePostDTO dto) throws Exception {
        if (id != null) {
            updateStaffLeave(id, dto);
        } else {
            id = insert(dto);
        }
        return affirm(id);
    }

    @Override
    public Long affirm(Long id) throws Exception {
        QueryWrapper<StaffLeavePostApprove> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffLeavePostApprove entity = mapper.selectOne(wrapper);
        if(entity == null) {
            throw new Exception("该离职审批记录已发起审批，请勿重复操作");
        }
        entity.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(entity);
        return id;
    }

    @Override
    public Long updateStaffLeave(Long id, SaveStaffLeavePostDTO dto) throws Exception {
        QueryWrapper<StaffLeavePostApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffLeavePostApprove entity = mapper.selectOne(queryWrapper);
        if (entity == null) {
            throw new Exception("该审批记录不可编辑");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setInterviewsTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getInterviewsTime()));
        entity.setLeaveTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getLeaveTime()));
        entity.setLeaveApplicationTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getLeaveApplicationTime()));
        mapper.updateById(entity);
        return id;
    }

    @Override
    public StaffLeavePostInfoVO getStaffLeave(Long id) {
        StaffLeavePostApprove entity = mapper.selectById(id);
        if (entity != null) {
            StaffLeavePostInfoVO vo = new StaffLeavePostInfoVO();
            BeanUtils.copyProperties(entity, vo);
            vo.setInterviewsTime(LocalDateTimeUtils.convert2Long(entity.getInterviewsTime()));
            vo.setLeaveTime(LocalDateTimeUtils.convert2Long(entity.getLeaveTime()));
            vo.setLeaveApplicationTime(LocalDateTimeUtils.convert2Long(entity.getLeaveApplicationTime()));
            return vo;
        }
        return null;
    }

    @Override
    public Long insert(SaveStaffLeavePostDTO dto) {
        StaffLeavePostApprove entity = new StaffLeavePostApprove();
        BeanUtils.copyProperties(dto, entity);

        entity.setInterviewsTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getInterviewsTime()));
        entity.setLeaveTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getLeaveTime()));
        entity.setLeaveApplicationTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getLeaveApplicationTime()));
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }

}
