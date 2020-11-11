
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.manpower.mpclient.dto.organization.OrgModifyRecordDTO;
import net.herdao.hdp.manpower.mpclient.entity.OrgModifyRecord;
import net.herdao.hdp.manpower.mpclient.entity.Organization;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
public interface OrgModifyRecordService extends IService<OrgModifyRecord> {

	/**
	 * @description 获取组织变更记录
	 * 
	 * @author      shuling
	 * @param       newOrg
	 * @param       oldOrg
	 * @return
	 */
	OrgModifyRecord saveOrgModifyRecord(Organization newOrg, Organization oldOrg);
	
	/**
	 * 组织变更详情列表
	 * 
	 * @param orgCode
	 * @param searchText
	 * @return
	 */
	Page<OrgModifyRecordDTO> getPage(Page<OrgModifyRecordDTO> page, String orgCode, String searchText);

}
