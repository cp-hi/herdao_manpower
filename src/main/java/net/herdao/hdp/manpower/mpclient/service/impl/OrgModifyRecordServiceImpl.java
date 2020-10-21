
package net.herdao.hdp.manpower.mpclient.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.entity.OrgModifyRecord;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.mapper.OrgModifyRecordMapper;
import net.herdao.hdp.manpower.mpclient.service.OrgModifyRecordService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.mpclient.utils.ObjectFieldCompareUtils;
import net.herdao.hdp.manpower.mpclient.vo.orgmodifyrecord.OrgChangePropertiesVO;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
@Service
public class OrgModifyRecordServiceImpl extends ServiceImpl<OrgModifyRecordMapper, OrgModifyRecord> implements OrgModifyRecordService {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Override
	public OrgModifyRecord saveOrgModifyRecord(Organization newOrg, Organization oldOrg) {
		OrgModifyRecord orgModifyRecord = createOrgModifyRecord(newOrg, oldOrg);
		if(ObjectUtil.isNotNull(orgModifyRecord)) {
			this.save(orgModifyRecord);
		}
		return orgModifyRecord;
	}

	public OrgModifyRecord createOrgModifyRecord(Organization newOrg, Organization oldOrg) {

		if (newOrg == null || oldOrg == null) {
			return null;
		}
		// 修改组织信息
		OrgChangePropertiesVO newOrgCp = new OrgChangePropertiesVO();
		BeanUtils.copyProperties(newOrg, newOrgCp);

		// 修改前组织信息
		OrgChangePropertiesVO oldOrgCp = new OrgChangePropertiesVO();
		BeanUtils.copyProperties(oldOrg, oldOrgCp);

		// 获取已经修改的属性
		List<Map<String, Object>> orgChangePropList = ObjectFieldCompareUtils.compareObject(newOrgCp, oldOrgCp);
		if (ObjectUtil.isNotEmpty(orgChangePropList)) {
			OrgModifyRecord orgModifyRecord = new OrgModifyRecord();
			// 获取修改的组织信息
			orgModifyRecord.setCurOrgId(newOrg.getId());
			orgModifyRecord.setCurOrgName(newOrg.getOrgName());
			orgModifyRecord.setCurOrgCode(newOrg.getOrgCode());
			orgModifyRecord.setCurOrgTreeLevel(newOrg.getOrgTreeLevel());
			// 新上级组织信息
			Long newParentId = newOrg.getParentId();
			if (ObjectUtil.isNotNull(newParentId)) {
				Organization newParentOrg = organizationService.getById(newParentId);
				orgModifyRecord.setCurOrgParentId(newParentOrg.getId());
				orgModifyRecord.setCurOrgParentName(newParentOrg.getOrgName());
			}

			// 原组织信息
			orgModifyRecord.setOldOrgCode(oldOrg.getOrgCode());
			orgModifyRecord.setOldOrgName(oldOrg.getOrgName());
			orgModifyRecord.setOldOrgTreeLevel(oldOrg.getOrgTreeLevel());

			// 原上级组织信息
			Long oldParentId = oldOrg.getParentId();
			if (ObjectUtil.isNotNull(oldParentId)) {
				Organization oldParentOrg = organizationService.getById(oldParentId);
				orgModifyRecord.setOldOrgParentId(oldParentOrg.getId());
				orgModifyRecord.setOldOrgParentName(oldParentOrg.getOrgName());

			}

			// 生效日期
			orgModifyRecord.setEffectTime(newOrg.getStartDate());

			SysUser sysUser = SysUserUtils.getSysUser();
			// 操作人信息
			orgModifyRecord.setOperatorId(StrUtil.toString(sysUser.getUserId()));
			orgModifyRecord.setOperatorName(sysUser.getUsername());
			orgModifyRecord.setOperatorTime(new Date());

			return orgModifyRecord;
		}
		return null;
	}

}
