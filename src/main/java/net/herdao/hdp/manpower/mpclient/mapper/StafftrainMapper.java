

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Mapper
public interface StafftrainMapper extends BaseMapper<Stafftrain> {
    /**
     * 员工培训分页
     * @param page 分页对象
     * @param stafftrainDTO 员工培训DTO
     * @param searchText 关键字
     * @return
     */
    List<StafftrainDTO> findStaffTrainPage(Map<String, Object> map);


    /**
     * 员工培训
     * @param searchText
     * @return
     */
    List<StafftrainDTO> findStaffTrain(@Param("searchText") String searchText,@Param("query") StafftrainDTO stafftrainDTO);

}
