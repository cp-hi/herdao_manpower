/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.AppointmentAndRemovalConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveAppointmentDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.PostOrg;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemoval;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemovalItem;
import net.herdao.hdp.manpower.mpclient.mapper.StaffAppointmentAndRemovalItemMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 2:39 下午
 */
@Service
public class StaffAppointmentServiceImpl extends ServiceImpl<StaffAppointmentAndRemovalItemMapper, StaffAppointmentAndRemovalItem> implements StaffAppointmentService {

    @Autowired
    private StaffAppointmentAndRemovalItemMapper itemMapper;

    @Autowired
    private OrganizationService orgService;
    @Autowired
    private PostOrgService postOrgService;


    @Override
    public Page<AppointmentPageVO> pageAppointment(Long appointmentAdnRemovalId,
                                                   Page page) {
        QueryWrapper<StaffAppointmentAndRemovalItem> wrapper = new QueryWrapper();
        Page<StaffAppointmentAndRemovalItem> itemPage = itemMapper.selectPage(page,
                wrapper.eq("staff_appointment_removal_id", appointmentAdnRemovalId)
                        .eq("appoint_type", AppointmentAndRemovalConstants.APPOINTMENT_TYPE));
        Page<AppointmentPageVO> voPage = new Page<>();
        List<AppointmentPageVO> records = getAppointmentPageVO(itemPage.getRecords());
        BeanUtils.copyProperties(itemPage, voPage);
        voPage.setRecords(records);
        return voPage;
    }

    private List<AppointmentPageVO> getAppointmentPageVO(List<StaffAppointmentAndRemovalItem> items) {
        List<AppointmentPageVO> to = new ArrayList<>();
        for (StaffAppointmentAndRemovalItem item : items) {
            AppointmentPageVO vo = new AppointmentPageVO();
            vo.setId(item.getId());
            vo.setStartDate(LocalDateTimeUtils.convert2Long(item.getStartDate()));
            vo.setOrgName(orgService.getOrgName(item.getOrgId()));
            vo.setPostOrgName(postOrgService.getPostOrgName(item.getPostOrgId()));
            to.add(vo);
        }

        return to;
    }

    @Override
    public AppointmentInfoVO getAppointmentInfoVO(Long id) {

        StaffAppointmentAndRemovalItem item = itemMapper.selectById(id);
        if (item != null) {
            AppointmentInfoVO vo = new AppointmentInfoVO();
            BeanUtils.copyProperties(item, vo);
            vo.setOrgName(orgService.getOrgName(item.getOrgId()));
            vo.setPostOrgName(postOrgService.getPostOrgName(item.getPostOrgId()));
            vo.setStartDate(LocalDateTimeUtils.convert2Long(item.getStartDate()));
            return vo;
        }
        return null;
    }

    @Override
    public Long insert(Long appointmentAdnRemovalId, SaveAppointmentDTO dto) {
        StaffAppointmentAndRemovalItem entity = initAppointmentData(appointmentAdnRemovalId, dto);
        itemMapper.insert(entity);
        return entity.getId();
    }

    private StaffAppointmentAndRemovalItem initAppointmentData(Long appointmentAdnRemovalId, SaveAppointmentDTO dto) {
        StaffAppointmentAndRemovalItem entity = new StaffAppointmentAndRemovalItem();
        BeanUtils.copyProperties(dto, entity);
        entity.setAppointType(AppointmentAndRemovalConstants.APPOINTMENT_TYPE);
        entity.setStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getStartDate()));
        // 兼职任免表中，兼职默认非主岗
        entity.setMainPost(AppointmentAndRemovalConstants.IS_NOT_MAIN_POST);
        entity.setStaffAppointmentRemovalId(appointmentAdnRemovalId);
        return entity;
    }

    @Override
    public Long update(Long id, SaveAppointmentDTO dto) throws Exception {
        StaffAppointmentAndRemovalItem entity = itemMapper.selectById(id);
        if(entity == null) {
            throw new Exception("该记录不存在，请联系管理员");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setId(id);
        entity.setStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getStartDate()));
        itemMapper.updateById(entity);
        return id;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
