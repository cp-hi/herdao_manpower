/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemovalItem;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 2:41 下午
 */
@Mapper
public interface StaffAppointmentAndRemovalItemMapper extends BaseMapper<StaffAppointmentAndRemovalItem> {
}
