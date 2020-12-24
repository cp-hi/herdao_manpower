/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffAppointmentAndRemoval;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 6:39 下午
 */
@Mapper
public interface StaffAppointmentAndRemovalMapper extends BaseMapper<StaffAppointmentAndRemoval> {
    Page<AppointmentAndRemovalPage> selectAppointmentAndRemovalPage(Page page,
                                                                    @Param("searchText") String searchText,
                                                                    @Param("orgId") Long orgId,
                                                                    @Param("status") String status);
}
