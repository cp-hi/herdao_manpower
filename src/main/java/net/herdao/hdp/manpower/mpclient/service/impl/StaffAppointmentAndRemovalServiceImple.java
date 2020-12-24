/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveAppointAndRemovalDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemoval;
import net.herdao.hdp.manpower.mpclient.mapper.StaffAppointmentAndRemovalMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.vo.staff.StaffBasicVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalPageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 7:38 下午
 */
@Service
public class StaffAppointmentAndRemovalServiceImple extends ServiceImpl<StaffAppointmentAndRemovalMapper, StaffAppointmentAndRemoval> implements StaffAppointmentAndRemovalService {

    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffAppointmentService appointmentService;
    @Autowired
    private StaffRemovalService removalService;

    @Autowired
    private StaffAppointmentAndRemovalMapper mapper;

    /**
     *  1. 获取和数据库字段一致的查询后的 page 中的主岗信息
     *  2. 根据主表中的 id，到副表中查询其兼职列表并封装兼职岗位字段
     *  3. 根据主表中的 id，到副表中查询其任免列表并封装任免岗位字段
     *  4. 封装其他前端要的字段
     *
     * @Author
     * @Date
     * @param searchText
     * @param page
     * @param orgId
     * @param status
     * @param type
     * @return {@link Page< AppointmentAndRemovalPageVO>}
     **/
    @Override
    public Page<AppointmentAndRemovalPageVO> pageAppointmentAndRemoval(String searchText, Page page, Long orgId, String status, String type) {
        Page<AppointmentAndRemovalPage> fromPage = baseMapper.selectAppointmentAndRemovalPage(page, searchText, orgId, status);
        Page<AppointmentAndRemovalPageVO> toPage = new Page<>();

        Page queryPage = new Page();
        queryPage.setCurrent(1);
        queryPage.setSize(9999);
        List<AppointmentAndRemovalPageVO> records = new LinkedList<>();
        for (AppointmentAndRemovalPage record : fromPage.getRecords()) {
            AppointmentAndRemovalPageVO vo = new AppointmentAndRemovalPageVO();
            BeanUtils.copyProperties(record, vo);
            vo.setAppointmentPostOrgNames(joinAppointmentPostOrgNames(record.getId(), page));
            vo.setRemovalPostOrgNames(joinRemovalPostOrgNames(record.getId(), page));
            vo.setUpdateInfo(MessageFormat.format("{0} 于 {1} 更新", record.getModifierName(), record.getModifierTime()));
            records.add(vo);
        }
        BeanUtils.copyProperties(fromPage, toPage);
        toPage.setRecords(records);

        return toPage;
    }

    private String joinRemovalPostOrgNames(Long appointmentAdnRemovalId, Page page) {
        Page<RemovalPageVO> removalPage = removalService.pageRemoval(appointmentAdnRemovalId, page);

        List<String> removals = removalPage.getRecords()
                .stream()
                .map(RemovalPageVO::getPostOrgName)
                .collect(Collectors.toList());

        String join = StringUtils.join(removals, "，");
        if (StringUtils.isNotBlank(join)) {
            return join;
        }
        return null;
    }

    private String joinAppointmentPostOrgNames(Long appointmentAdnRemovalId, Page page) {
        Page<AppointmentPageVO> appointmentPage = appointmentService.pageAppointment(appointmentAdnRemovalId, page);

        List<String> appointments  = appointmentPage.getRecords()
                .stream()
                .map(AppointmentPageVO::getPostOrgName)
                .collect(Collectors.toList());

        String join = StringUtils.join(appointments, "，");
        if (StringUtils.isNotBlank(join)) {
            return join;
        }
        return "";
    }

    @Override
    public AppointmentAndRemovalInfoVO getAppointmentAndRemovalInfo(Long id) throws Exception {
        StaffAppointmentAndRemoval data = mapper.selectById(id);
        if (data != null) {
           return convert2AppointmentAndRemovalInfoVO(data);
        }
        return null;
    }

    private AppointmentAndRemovalInfoVO convert2AppointmentAndRemovalInfoVO(StaffAppointmentAndRemoval from) throws Exception {
        AppointmentAndRemovalInfoVO to = new AppointmentAndRemovalInfoVO();
        BeanUtils.copyProperties(from, to);

        StaffBasicVO staffBasicVO = staffService.selectBasicByUserId(from.getUserId());
        if (staffBasicVO != null) {
            BeanUtils.copyProperties(staffBasicVO, to);
        }
        return to;
    }

    @Override
    public Long insert(SaveAppointAndRemovalDTO dto) {
        StaffAppointmentAndRemoval entity = new StaffAppointmentAndRemoval();

        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public Long update(Long id, SaveAppointAndRemovalDTO dto) throws Exception {
        StaffAppointmentAndRemoval entity = mapper.selectById(id);
        if(entity == null) {
            throw new Exception("该记录不存在，请联系管理员");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setId(id);

        mapper.updateById(entity);
        return id;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
