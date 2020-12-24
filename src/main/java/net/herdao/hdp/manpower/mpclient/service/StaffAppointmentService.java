/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveAppointmentDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemovalItem;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 2:37 下午
 */
public interface StaffAppointmentService extends HdpService<StaffAppointmentAndRemovalItem> {

    Page<AppointmentPageVO> pageAppointment(Long appointmentAdnRemovalId,
                                            Page page);

    AppointmentInfoVO getAppointmentInfoVO(Long id);

    Long insert(Long appointmentAdnRemovalId, SaveAppointmentDTO dto);

    Long update(Long id, SaveAppointmentDTO dto) throws Exception;
}
