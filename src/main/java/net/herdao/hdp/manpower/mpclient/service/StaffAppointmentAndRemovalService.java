/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveAppointAndRemovalDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemoval;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 2:36 下午
 */

public interface StaffAppointmentAndRemovalService extends HdpService<StaffAppointmentAndRemoval> {
    Page<AppointmentAndRemovalPageVO> pageAppointmentAndRemoval(String searchText, Page page, Long orgId, String status, String type);

    AppointmentAndRemovalInfoVO getAppointmentAndRemovalInfo(Long id) throws Exception;

    Long insert(SaveAppointAndRemovalDTO dto);

    Long update(Long id, SaveAppointAndRemovalDTO dto) throws Exception;
}
