package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
public interface StafftrainService extends HdpService<Stafftrain> {

    /**
     * 员工培训分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    Page<StafftrainDTO> findStaffTrainPage(Page<StafftrainDTO> page,StafftrainDTO stafftrainDTO, String searchText);


    /**
     * 员工培训
     * @param searchText
     * @return
     */
    List<StafftrainDTO> findStaffTrain(String searchText, StafftrainDTO stafftrainDTO);

}
