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
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveRemovalDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemoval;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemovalItem;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.mapper.StaffAppointmentAndRemovalItemMapper;
import net.herdao.hdp.manpower.mpclient.mapper.StaffAppointmentAndRemovalMapper;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.mpclient.service.PostOrgService;
import net.herdao.hdp.manpower.mpclient.service.StaffRemovalService;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.WaitingRemovalPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 6:20 下午
 */
@Service
public class StaffRemovalServiceImpl extends ServiceImpl<StaffAppointmentAndRemovalItemMapper, StaffAppointmentAndRemovalItem> implements StaffRemovalService {

    @Autowired
    private UserpostService userpostService;
    @Autowired
    private OrganizationService orgService;
    @Autowired
    private PostOrgService postOrgService;

    @Autowired
    private StaffAppointmentAndRemovalMapper mapper;
    @Autowired
    private StaffAppointmentAndRemovalItemMapper itemMapper;

    /**
     *  step1：在副表中根据 appointmentAdnRemovalId 获取主表对象
     *  step2：根据主表中的 userId 去 mp_userpost 表中获取当前用户的任职列表
     *         在 mp_userpost 这张表中
     *         找出该 userId 的 MAIN_POST 与 IS_VIRTUAL 均为 false 的数据返回
     *
     * @Author
     * @Date
     * @param appointmentAdnRemovalId
     * @param page
     * @return {@link Page< WaitingRemovalPageVO>}
     **/
    @Override
    public Page<WaitingRemovalPageVO> pageWaitingRemoval(Long appointmentAdnRemovalId, Page page) {
        // step1
        QueryWrapper<StaffAppointmentAndRemoval> appointmentAndRemovalQueryWrapper = new QueryWrapper<>();
        StaffAppointmentAndRemoval staffAppointmentAndRemoval = mapper.selectOne(appointmentAndRemovalQueryWrapper.eq("id", appointmentAdnRemovalId));

        // step2
        Page<Userpost> fromPage = userpostService.pageUserAllAppointmentsByUserId(page, staffAppointmentAndRemoval.getUserId());
        Page<WaitingRemovalPageVO> voPage = new Page<>();
        List<WaitingRemovalPageVO> records = new ArrayList<>();
        for (Userpost record : fromPage.getRecords()) {
            WaitingRemovalPageVO vo = new WaitingRemovalPageVO();
            BeanUtils.copyProperties(record, vo);
            vo.setOrgName(orgService.getOrgName(record.getOrgId()));
            vo.setPostOrgName(postOrgService.getPostOrgName(record.getPostOrgId()));
            records.add(vo);
        }
        BeanUtils.copyProperties(fromPage, voPage);
        voPage.setRecords(records);
        return voPage;
    }

    @Override
    public Page<RemovalPageVO> pageRemoval(Long appointmentAdnRemovalId, Page page) {

        QueryWrapper<StaffAppointmentAndRemovalItem> wrapper = new QueryWrapper();
        Page<StaffAppointmentAndRemovalItem> itemPage = itemMapper.selectPage(page,
                wrapper.eq("staff_appointment_removal_id", appointmentAdnRemovalId)
                        .eq("appoint_type", AppointmentAndRemovalConstants.REMOVAL_TYPE));
        Page<RemovalPageVO> voPage = new Page<>();
        List<RemovalPageVO> records = getAppointmentPageVO(itemPage.getRecords());
        BeanUtils.copyProperties(itemPage, voPage);
        voPage.setRecords(records);
        return voPage;
    }

    private List<RemovalPageVO> getAppointmentPageVO(List<StaffAppointmentAndRemovalItem> items) {
        List<RemovalPageVO> to = new ArrayList<>();
        for (StaffAppointmentAndRemovalItem item : items) {
            RemovalPageVO vo = new RemovalPageVO();
            vo.setId(item.getId());
            vo.setEndDate(LocalDateTimeUtils.convert2Long(item.getEndDate()));
            vo.setOrgName(orgService.getOrgName(item.getOrgId()));
            vo.setPostOrgName(postOrgService.getPostOrgName(item.getPostOrgId()));
            to.add(vo);
        }

        return to;
    }

    @Override
    public RemovalInfoVO getRemovalInfo(Long id) {
        StaffAppointmentAndRemovalItem item = itemMapper.selectById(id);
        if (item != null) {
            RemovalInfoVO vo = new RemovalInfoVO();
            BeanUtils.copyProperties(item, vo);
            vo.setOrgName(orgService.getOrgName(item.getOrgId()));
            vo.setPostOrgName(postOrgService.getPostOrgName(item.getPostOrgId()));
            vo.setEndDate(LocalDateTimeUtils.convert2Long(item.getEndDate()));
            return vo;
        }
        return null;
    }

    @Override
    public Long insert(SaveRemovalDTO dto) {
        StaffAppointmentAndRemovalItem entity = initRemovalData(dto);
        itemMapper.insert(entity);
        return entity.getId();
    }

    private StaffAppointmentAndRemovalItem initRemovalData(SaveRemovalDTO dto) {

        StaffAppointmentAndRemovalItem entity = new StaffAppointmentAndRemovalItem();
        BeanUtils.copyProperties(dto, entity);
        entity.setAppointType(AppointmentAndRemovalConstants.REMOVAL_TYPE);
        entity.setEndDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getEndDate()));
        entity.setUserpostId(dto.getUserpostId());
        entity.setDelFlag(false);
        return entity;
    }

    @Override
    public Long update(Long id, SaveRemovalDTO dto) throws Exception {
        StaffAppointmentAndRemovalItem entity = itemMapper.selectById(id);
        if(entity == null) {
            throw new Exception("该记录不存在，请联系管理员");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setId(id);
        entity.setStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getEndDate()));
        itemMapper.updateById(entity);
        return id;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
