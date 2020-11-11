

package net.herdao.hdp.manpower.mpclient.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.herdao.hdp.manpower.mpclient.dto.organization.OrgModifyRecordDTO;
import net.herdao.hdp.manpower.mpclient.entity.OrgModifyRecord;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
@Mapper
public interface OrgModifyRecordMapper extends BaseMapper<OrgModifyRecord> {
	
	/**
	 * 组织变更详情列表
	 * 
	 * @param orgCode
	 * @param searchText
	 * @return
	 */
	Page<OrgModifyRecordDTO> getPage(Page<OrgModifyRecordDTO> page, @Param("orgCode")String orgCode, @Param("searchText")String searchText);

}
