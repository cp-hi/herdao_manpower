
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffOrgDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationVO;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.mapper.OrganizationMapper;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdpbase.template.contant.TemplateContants;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

/**
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
@Service
@AllArgsConstructor
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    private final UserpostService userpostService;

    private final OrgModifyRecordService orgModifyRecordService;

    private final RemoteUserService remoteUserService;

    private final PostService postService;

    private final UserService userService;

    @Override
    public List<Organization> selectOrganizationListByParentOid(String parentId) {
        List<Organization> list = this.baseMapper.selectOrganizationListByParentOid(parentId);
        return list;
    }

    @Override
    public List<Organization> findAllOrganizations(Organization condition) {
        List<Organization> list = this.baseMapper.findAllOrganizations(condition);
        return list;
    }

    @Override
    public List<Organization> fetchDeptTree(Organization condition) {
        List<Organization> list = this.baseMapper.fetchDeptTree(condition);
        return list;
    }

    @Override
    public List<Organization> findOrganizationByCondition(Organization condition) {
        List<Organization> list = this.baseMapper.findOrganizationByCondition(condition);
        return list;
    }

    @Override
    public List<Organization> findOrganizationByKeyWords(String keyword) {
        List<Organization> list = this.baseMapper.findOrganizationByKeyWords(keyword);
        return list;
    }

    @Override
    public List<Organization> selectOrganByCurrentOrgOid(String id) {
        List<Organization> list = this.baseMapper.selectOrganByCurrentOrgOid(id);
        return list;
    }


    /**
     * 组织启用/停用
     * @param condition
     * @return R
     */
    @ApiOperation(value = "组织启用/停用", notes = "组织启用/停用")
    @SysLog("组织启用/停用")
    @Override
    @Transactional
    public R startOrStopOrg(@RequestBody Organization condition) {
        try {
            //更新组织变更记录表
            OrgModifyRecord record=new OrgModifyRecord();
            record.setCurOrgId(condition.getId());
            record.setCurOrgName(condition.getOrgName());
            record.setCurOrgCode(condition.getOrgCode());
            record.setCurOrgParentId(condition.getParentId());
            record.setCurOrgTreeLevel(condition.getOrgTreeLevel());
            record.setOperatorTime(new Date());
            UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
            if (null != userInfo){
                //操作人ID
                record.setOperatorId(userInfo.getSysUser().getUserId().toString());
                //操作人名称
                record.setOperatorName(userInfo.getSysUser().getUsername());
            }

            // 是否停用 ： 0 停用 ，1启用（默认），3全部
            //如果是启用操作，则直接启用当前组织。
            if (null != condition && condition.getIsStop() == 1){
                this.baseMapper.updateById(condition);

                record.setOperateDesc("启用组织");
                record.setEffectTime(condition.getStartDate());
                return R.ok("组织的启用成功");
            }

            //如果是停用操作
            if (null != condition && condition.getIsStop() == 0){
                record.setOperateDesc("停用组织");
                 record.setEffectTime(condition.getStopDate());

                List<Map<String, Long>> orgIds = new ArrayList<>();
                //递归获取组织架构parentId
                getRecursionParentIds(condition.getId(), orgIds,"stopOrg");

                if (!orgIds.isEmpty()) {
                    List<Long> orgIdList = new ArrayList<>();
                    //操作标志
                    boolean operFlag = false;
                    for (Map<String, Long> maps : orgIds) {
                        for (Long orgId : maps.values()) {
                            orgIdList.add(orgId);
                        }
                    }
                    Userpost userpost = new Userpost();
                    userpost.setOrgDeptIds(orgIdList);
                    //查询该组织及其所有下层组织中任一个是否有挂靠的在职员工
                    List<Userpost> userPostList = userpostService.findUserPost(userpost);
                    if (!userPostList.isEmpty()) {
                        log.error("停用组织失败,该组织及其下属组织有挂靠员工！");
                        R.failed("停用组织失败,该组织及其下属组织有挂靠员工！");
                        return R.ok("停用组织失败,该组织及其下属组织有挂靠员工！");
                    }else{
                        operFlag = true;
                    }

                    //如果该组织及其所有下层组织中任一个有挂靠的在职员工 则不能停用 更不能删除
                    if (operFlag) {
                        try {
                            //遍历循环组织架构id
                            for (Map<String, Long> maps : orgIds) {
                                //停用组织及其下层组织
                                for (Long orgId : maps.values()) {
                                    //更新该组织的停用日期 设置为停用状态
                                    Organization updateCondition=new Organization();
                                    updateCondition.setId(orgId);
                                    updateCondition.setIsStop(0);
                                    if(null != condition.getStopDate()){
                                        updateCondition.setStopDate(condition.getStopDate());
                                    }

                                    this.baseMapper.updateById(updateCondition);
                                }
                            }

                            //更新组织变更记录表
                            orgModifyRecordService.update();

                        }catch (Exception ex){
                            log.error("组织的停用失败");
                            return R.failed("组织的停用失败");
                        }

                        return R.ok("组织的停用成功");
                    }
                }
            }

            //更新组织变更记录表
            orgModifyRecordService.save(record);
        }catch (Exception ex){
            log.error("组织启用/停用失败",ex);
            return R.failed("组织启用/停用失败");
        }

        return R.ok("组织的停用成功");
    }


    @SysLog("递归获取组织架构parentId")
    public void getRecursionParentIds(Long id, List<Map<String, Long>> orgIds,String operate) {
        Organization condition = new Organization();
        condition.setId(id);
        List<Organization> list=new ArrayList<>();
        if (null != operate && operate=="stopOrg"){
            list = this.baseMapper.findAllOrg(condition);
        }else{
            list = this.baseMapper.findAllOrganizations(condition);
        }

        if (!list.isEmpty()) {
            for (Organization entity : list) {
                if (null != entity) {
                    Map<String, Long> map = new HashMap<>();
                    map.put(entity.getOrgName(), entity.getId());
                    orgIds.add(map);

                    if (null !=entity.getChildren() && !entity.getChildren().isEmpty()) {
                        getChildren(entity, orgIds);
                    }
                }

            }
        }
    }

    @SysLog("递归获取组织架构parentId")
    public void getChildren(Organization entity, List<Map<String, Long>> orgIds) {
        List<Organization> children = entity.getChildren();
        if (!children.isEmpty()) {
            for (Organization child : children) {
                if (null != child) {
                    Map<String, Long> map = new HashMap<>();
                    map.put(child.getOrgName(), child.getId());
                    orgIds.add(map);
                    getChildren(child, orgIds);
                }

            }
        }
    }

    @SysLog("点击展开组织架构树（默认两级）")
    @Override
    public R getRecursionOrgByLevel(@RequestBody Organization condition) {
        List<Organization> allOrgList = new ArrayList<>();
        List<Organization> rootOrgList = this.baseMapper.findOrganizationByCondition(condition);

        if (!rootOrgList.isEmpty()) {
            allOrgList.addAll(rootOrgList);
            List<Organization> childrenTemp = new ArrayList<>();
            for (Organization rootOrgan : rootOrgList) {
                Organization childrenCondition = new Organization();
                childrenCondition.setParentId(rootOrgan.getId());
                if (null == condition.getOrgTreeLevel()) {
                    //默认展示两级
                    queryOrgByParentId(childrenTemp, childrenCondition, 2L);
                } else {
                    //传参自定义展示层级数
                    queryOrgByParentId(childrenTemp, childrenCondition, condition.getOrgTreeLevel());
                }

            }
            allOrgList.addAll(childrenTemp);
        }

        //查询分页结果
        List<Long> ids = new ArrayList<>();
        for (Organization organization : allOrgList) {
            ids.add(organization.getId());
        }
        QueryWrapper<Organization> wrapper = Wrappers.query();
        wrapper.in("id", ids);

        List<Organization> list = super.list(wrapper);

        return R.ok(list);
    }

    @SysLog("点击展开组织架构树（默认两级）")
    private void queryOrgByParentId(List<Organization> childrenTemp, Organization condition, Long orgLevel) {
        List<Organization> children = this.baseMapper.findOrganizationByCondition(condition);
        if (!children.isEmpty()) {
            childrenTemp.addAll(children);
            for (Organization child : children) {
                Organization entity = new Organization();
                entity.setParentId(child.getId());
                //控制层级
                if (null != child.getOrgLevel()) {
                    if (Integer.parseInt(child.getOrgLevel()) < orgLevel) {
                        queryOrgByParentId(childrenTemp, entity, orgLevel);
                    }
                }
            }
        }
    }

    /**
     * 删除组织
     * @param condition
     * @return R
     */
    @Override
    public R removeOrg(@RequestBody Organization condition) {
        try {
            List<Map<String, Long>> orgIds = new ArrayList<>();
            //递归获取组织架构parentId
            getRecursionParentIds(condition.getId(), orgIds,"removeOrg");

            if (!orgIds.isEmpty()) {
                List<Long> orgIdList = new ArrayList<>();
                //删除标志
                boolean delFlag = false;
                for (Map<String, Long> maps : orgIds) {
                    for (Long orgId : maps.values()) {
                        orgIdList.add(orgId);
                    }
                }
                Userpost userpost = new Userpost();
                userpost.setOrgDeptIds(orgIdList);
                //查询该组织及其所有下层组织中任一个是否有挂靠的在职员工
                List<Userpost> userPostList = userpostService.findUserPost(userpost);
                if (!userPostList.isEmpty()) {
                    delFlag = true;
                    log.error("删除组织失败,该组织及其下属组织有挂靠员工！");
                    R.failed("删除组织失败,该组织及其下属组织有挂靠员工！");
                }

                //如果该组织及其所有下层组织中任一个有挂靠的在职员工 则不能停用 更不能删除
                if (delFlag == true) {
                    //遍历循环组织架构id
                    for (Map<String, Long> maps : orgIds) {
                        //停用组织及其下层组织
                        for (Long orgId : maps.values()) {
                            this.baseMapper.stopOrganById(orgId);
                        }

                        //组织需停用后才能删除 停用选中组织的所有下层组织
                        for (Long orgId : maps.values()) {
                            super.removeById(orgId);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error("删除组织失败,事务回滚！");
            R.failed("删除组织失败,事务回滚！");
        }

        return R.ok("删除组织成功");
    }

     /**
     * 默认加载展示2级组织架构
     * @param condition
     * @return R
     */
    @Override
    public  R findOrganization2Level(@RequestBody Organization condition) {
        if (null != condition) {
            //默认加载启用状态的组织架构(0 停用 ，1启用，3全部)
            condition.setIsStop(1);
        }
        List<Organization> rootOrgans = this.baseMapper.findRootOrganizations(condition);

        if (!rootOrgans.isEmpty()) {
            for (Organization rootOrgan : rootOrgans) {
                Organization childrenCondition = new Organization();
                childrenCondition.setParentId(rootOrgan.getId());
                //默认加载启用状态的组织架构(0 停用 ，1启用，3全部)
                childrenCondition.setIsStop(1);

                List<Organization> childrenOrgans = this.baseMapper.findOrganizationByCondition(childrenCondition);
                rootOrgan.setChildren(childrenOrgans);
            }

        }

        return R.ok(rootOrgans);
    }

    @Override
    public Page<Organization> findOrgPage(Page<Organization> page, String orgCode, Integer stop, String searchText) {
        Organization organization = this.getOne(new QueryWrapper<Organization>().lambda()
        								.eq(Organization::getOrgCode, orgCode));
        return this.baseMapper.findOrgPage(page, orgCode, organization.getOrgTreeLevel() + 1, stop, searchText);
    }


    @Override
    public Organization findOrgDetails(Organization condition) {
        Organization organization = this.baseMapper.findOrgDetails(condition);
        return organization;
    }

    @Override
    @SysLog("新增、修改组织信息")
    @OperationEntity(operation = "新增、修改组织信息", clazz = Organization.class)
    @Transactional(rollbackFor = Exception.class)
    public R<Organization> saveOrUpdateOrganization(OrganizationVO organizationVO) {
    	
    	Organization organization = new Organization();
    	
    	BeanUtils.copyProperties(organizationVO, organization);

    	Long id = organizationVO.getId();
    	
    	// 父组织id
    	Long parentId = organizationVO.getParentId();
    	
    	Organization parentOrganization = ObjectUtil.isNull(parentId) ? null : this.getById(parentId);

    	if(StrUtil.isBlank(organization.getOrgCode())) {
    		
    		// 设置组织编码
        	organization.setOrgCode(getOrgCode(organization.getParentId()));
        	
        	// 设置 组织编码、组织名称全路径
        	setOrgFullCodeAndOrgFullName(organization, parentOrganization);
    	}
    	
    	// 组织组织树层级
    	if(ObjectUtil.isNull(parentOrganization)) {
    		
    		// root 组织级别值：1
    		organization.setOrgTreeLevel(1L);
    	}else {
    		
    		// 组织组织树层级
    		organization.setOrgTreeLevel(parentOrganization.getOrgTreeLevel() + 1L);
    	}
    	
    	// 设置启用日期（默认为创建组织当日，可以编辑为其他日期）
    	
    	if(ObjectUtil.isNull(id) && ObjectUtil.isNull(organization.getStartDate())) {
    		organization.setStartDate(DateUtil.date());
    	}
    	
    	// 操作日志信息 TODO 需求待定
    	OrgModifyRecord orgModifyRecord = new OrgModifyRecord();
    	
    	orgModifyRecordService.save(orgModifyRecord);
    	
    	this.saveOrUpdate(organization);
    	
    	return R.ok(organization);
    }
    
    
   /**
    * @description 获取组织编码 
    * @author      shuling
    * @date        2020-10-18 10:37:22
    * @param organization 组织信息
    * @return
    */
	String getOrgCode(Long parentId) {

		// 数据库最大组织编码
		String maxOrgCode = this.baseMapper.getMaxOrgCode(parentId);
		
		// 首次新增 默认 001
		maxOrgCode = StrUtil.isBlank(maxOrgCode) ? "001" : maxOrgCode;

		return String.format("%0" + maxOrgCode.length() + "d", Long.parseLong(maxOrgCode) + 1);

	}
	
	/**
	 * @description 设置 组织编码、组织名称全路径 例如：orgFullname /广东省/广州市/天河区
	 * @author      shuling
	 * @date        2020-10-18 10:42:29
	 * @param organization
	 * @param parentOrganization
	 */
    void setOrgFullCodeAndOrgFullName(Organization organization, Organization parentOrganization) {
    	
    	String pathSeparator = "/";
    	
    	if(ObjectUtil.isNull(parentOrganization)) {
    		// 取当前组织信息
    		organization.setOrgFullname(pathSeparator + organization.getOrgName());
    		organization.setOrgFullCode(pathSeparator + organization.getOrgCode());
    	}else {
    		// 父orgFull + org
    		organization.setOrgFullname(parentOrganization.getOrgName() + pathSeparator + organization.getOrgName());
    		organization.setOrgFullCode(parentOrganization.getOrgCode() + pathSeparator + organization.getOrgCode());
    	}
    }
    
	@Override
	public R<?> selectOrganizations() {
		String searchText = "";
		return R.ok(this.baseMapper.selectOrganizations(searchText));
	}

    @Override
    public List<StaffOrgDTO> selectOrgStaffAll(Page page, String orgCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("orgCode", orgCode);
        return baseMapper.selectOrgStaffAll(map);
    }

    @Override
    public OrgChartDTO selectOrgChartRoot(Long id){
        return baseMapper.selectOrgChartRoot(id);
    }

    @Override
    public List<OrgChartDTO> selectOrgChartChild(Long id){
        return baseMapper.selectOrgChartChild(id);
    }
}
