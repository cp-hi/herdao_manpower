/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveRemovalDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemovalItem;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.WaitingRemovalPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 2:37 下午
 */
public interface StaffRemovalService extends HdpService<StaffAppointmentAndRemovalItem> {

    Page<WaitingRemovalPageVO> pageWaitingRemoval(Long appointmentAdnRemovalId, Page page);

    Page<RemovalPageVO> pageRemoval(Long appointmentAdnRemovalId, Page page);

    RemovalInfoVO getRemovalInfo(Long id);


    Long insert(SaveRemovalDTO dto);

    Long update(Long id, SaveRemovalDTO dto) throws Exception;
}
