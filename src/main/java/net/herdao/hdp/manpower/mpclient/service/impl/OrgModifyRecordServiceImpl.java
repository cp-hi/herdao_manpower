
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.OrgModifyRecord;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.mapper.OrgModifyRecordMapper;
import net.herdao.hdp.manpower.mpclient.service.OrgModifyRecordService;
import net.herdao.hdp.manpower.mpclient.vo.orgmodifyrecord.OrgChangePropertiesVO;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
@Service
public class OrgModifyRecordServiceImpl extends ServiceImpl<OrgModifyRecordMapper, OrgModifyRecord> implements OrgModifyRecordService {

	@Override
	public OrgModifyRecord createOrgModifyRecord(Organization newOrg, Organization oldOrg) {
		
		// 修改组织信息
		OrgChangePropertiesVO newOrgCp = new OrgChangePropertiesVO();
		BeanUtils.copyProperties(newOrg, newOrgCp);
		
		// 修改前组织信息
		OrgChangePropertiesVO oldOrgCp = new OrgChangePropertiesVO();
		BeanUtils.copyProperties(oldOrg, oldOrgCp);
		
		return null;
	}
	

}
