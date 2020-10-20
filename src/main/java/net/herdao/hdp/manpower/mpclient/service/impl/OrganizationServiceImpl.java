
package net.herdao.hdp.manpower.mpclient.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartDTO;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffOrgDTO;
import net.herdao.hdp.manpower.mpclient.entity.OrgModifyRecord;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.mapper.OrganizationMapper;
import net.herdao.hdp.manpower.mpclient.service.OrgModifyRecordService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.manpower.mpclient.vo.OrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationTreeVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;

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
    public List<OrganizationTreeVO> selectOrganizationTree() {
       return this.baseMapper.selectOrganizationTree();
    }
    
    @Override
    public List<OrganizationTreeVO> organizationTreeList(String searchText){
    	return this.baseMapper.organizationTreeList(searchText);
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
     * @description 预停用组织 
     * 
     * @author      shuling
	 * @date        2020-10-19 10:45:22
	 * @version     1.0
     */
    @ApiOperation(value = "停用组织", notes = "停用组织")
    @SysLog("预停用组织")
    @Override
    @Transactional(rollbackFor = Exception.class)
	public R expectedDisable(Long id, String stopDateStr) {
		// 停用状态
		Integer status = 1;
		Organization organization = this.getById(id);

		// 设置停用日期
		organization.setStopDate(DateUtil.parseDate(stopDateStr));
		// 设置为停用 TOTO 后期关联定时任务
		organization.setIsStop(status);

		this.saveOrUpdate(organization);

		return R.ok(null, ManpowerContants.DISABLE_SUCCESS);
	}
    
    
    /**
     * @description 停用组织
     * 
     * @author      shuling
	 * @date        2020-10-19 10:22:11
	 * @version     1.0 
     */
    @ApiOperation(value = "停用组织", notes = "停用组织")
    @SysLog("停用组织")
    @Override
    @Transactional(rollbackFor = Exception.class)
	public R disable() {
    	// 停用状态
    	Integer status = 1;
		// 查询待停用的组织信息
		List<Organization> organizations = this.lambdaQuery().le(Organization::getStopDate, DateUtil.parseDate(DateUtil.formatDate(new Date())))
											   .ne(Organization::getIsStop, status).list();
		if (ObjectUtil.isNotEmpty(organizations)) {
			organizations.forEach(organization -> {
				// 当前组织下在职员工数
				Integer countUser = userService.getCountUser(organization.getOrgCode(), 0);

				// 组织没有在职用户
				if (countUser == 0) {
					// 设置为停用 TOTO 后期关联定时任务
					organization.setIsStop(status);

					this.saveOrUpdate(organization);
				}
			});
		}
		return R.ok(null, ManpowerContants.DISABLE_SUCCESS);
	}
    
    
    /**
     * @description 预启用组织
     * 
     * @author      shuling
	 * @date        2020-10-19 10:22:19
	 * @version     1.0
     */
    @ApiOperation(value = "启用组织", notes = "启用组织")
    @SysLog("预启用组织")
    @Override
    @Transactional(rollbackFor = Exception.class)
	public R expectedEnable(Long id, String startDateStr) {
    	// 启用状态
    	Integer status = 0;
    	
		Organization organization = this.getById(id);

		// 设置停用日期
		organization.setStopDate(null);

		// 设置启用
		organization.setStopDate(DateUtil.parseDate(startDateStr));
		// 设置为启用 TOTO 后期关联定时任务
		organization.setIsStop(status);
		
		this.saveOrUpdate(organization);
		
		return R.ok(null, ManpowerContants.ENABLE_SUCCESS);
	}
    
    /**
     * @description 启用组织
     * 
     * @author      shuling
	 * @date        2020-10-19 10:42:15
	 * @version     1.0
     */
    @ApiOperation(value = "启用组织", notes = "启用组织")
    @SysLog("启用组织")
    @Override
    @Transactional(rollbackFor = Exception.class)
	public R enable() {
    	
    	// 启用状态
    	Integer status = 0;
    	// 查询待启用的组织信息
    	List<Organization> organizations = this.lambdaQuery().le(Organization::getStartDate, DateUtil.parseDate(DateUtil.formatDate(new Date())))
    												   .ne(Organization::getIsStop, status).list();
    	
    	if (ObjectUtil.isNotEmpty(organizations)) {
			organizations.forEach(organization -> {
				// 设置为停用 TOTO 后期关联定时任务
				organization.setIsStop(status);

				this.saveOrUpdate(organization);
			});
		}

		return R.ok(null, ManpowerContants.ENABLE_SUCCESS);
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
     * @description 删除组织
     * 
     * @author      shuling
	 * @date        2020-10-19 20:22:19
	 * @version     1.0
     * @param       ids
     * @return      R
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removeOrg(String ids) {
    	
    	StringBuffer message = new StringBuffer();
    	
    	if(StrUtil.isBlank(ids)) {
    		return R.failed("请先选择要删除的组织！");
    	}
    	
        String [] idAy = ids.split(ManpowerContants.EN_SEPARATOR);
        
        // 组织orgCode集合
        List<String> orgCodes = new ArrayList<String>();
        
        for(int i=0; i<idAy.length; i++) {
        	
        	Long id = Long.parseLong(idAy[i]);
        	
        	Organization organization = this.getById(id);
        	
        	String orgCode = organization.getOrgCode();
        	
        	int countUser = userService.getCountUser(orgCode, null);
        	
        	if(countUser > 0) {
        		String orgMsg = "组织：" + organization.getOrgName() + "， 用户数：" + countUser;
        		message.append(message.length() > 0 ? (ManpowerContants.CH_SEPARATOR + orgMsg) : orgMsg);
        	}
        	
        	orgCodes.add(orgCode);
        }
        
        if(message.length() > 0) {
        	return R.failed("删除失败，组织下不能存在人员，包括离职人员、派遣员等， 以下组织存在人员信息：" + message);
        }
        
        // 删除组织 TOTO 待使用脚本删除方式
        Set<Organization> organizationSet = new HashSet<Organization>();
        
        for(int i=0; i<orgCodes.size(); i++) {
        	List<Organization> organizations =  this.baseMapper.selectOrganizationByOrgCode(orgCodes.get(i));
        	if(ObjectUtil.isNotEmpty(organizations)) {
        		organizations.forEach(organization ->{
        			organization.setDelFlag(true);
        			organizationSet.add(organization);
        		});
        	}
        }
        // 批量逻辑删除
        this.updateBatchById(organizationSet);
        
        return R.ok(null, ManpowerContants.DELETE_SUCCESS);
        
    }

     /**
     * 默认加载展示2级组织架构
     * @param condition
     * @return R
     */
    @Override
    public  R findOrganization2Level(@RequestBody Organization condition) {
        if (null != condition) {
            //默认加载启用状态的组织架构（值：0 启用 、值：1 停用 、值：3 或者 NULL 查询全部）
            condition.setIsStop(0);
        }
        List<Organization> rootOrgans = this.baseMapper.findRootOrganizations(condition);

        if (!rootOrgans.isEmpty()) {
            for (Organization rootOrgan : rootOrgans) {
                Organization childrenCondition = new Organization();
                childrenCondition.setParentId(rootOrgan.getId());
                //默认加载启用状态的组织架构
                childrenCondition.setIsStop(0);

                List<Organization> childrenOrgans = this.baseMapper.findOrganizationByCondition(childrenCondition);
                rootOrgan.setChildren(childrenOrgans);
            }

        }

        return R.ok(rootOrgans);
    }

    @Override
    public Page<OrganizationVO> findOrgPage(Page<OrganizationVO> page, Integer stop, String searchText) {
        return this.baseMapper.findOrgPage(page, stop, searchText);
    }


    @Override
    public Organization findOrgDetails(Organization condition) {
        Organization organization = this.baseMapper.findOrgDetails(condition);
        return organization;
    }

    /**
     * @description 新增、修改组织信息
     * 
     * @author      shuling
	 * @date        2020-10-19 10:22:19
	 * @version     1.0
     */
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
    	
    	if(ObjectUtil.isNotNull(id)) {
    		
    		Organization tpOrganization = this.getById(id);
    		// 更新了父组织
    		if(!organization.getParentId().equals(tpOrganization.getParentId())) {
    			List<Organization> organizations = this.baseMapper.selectOrganizationByOrgCode(tpOrganization.getOrgCode());
    			if(ObjectUtil.isNotEmpty(organizations)) {
    				
    				// 更新组织orgCode、orgFullname
    				organizations.forEach(org ->{
    					// 校验是否存在父组织
    					if(ObjectUtil.isNull(parentOrganization)) {
    						org.setOrgCode(org.getOrgCode().replaceFirst(tpOrganization.getOrgCode(), organization.getOrgCode()));
        					org.setOrgFullname(org.getOrgFullname().replaceFirst(tpOrganization.getOrgFullname(), organization.getOrgFullname()));
    					}else {
    						org.setOrgCode(org.getOrgCode().replaceFirst(tpOrganization.getOrgCode(), parentOrganization.getOrgCode()));
        					org.setOrgFullname(org.getOrgFullname().replaceFirst(tpOrganization.getOrgFullname(), parentOrganization.getOrgFullname()));
    					}
    				});
    				
    				this.updateBatchById(organizations);
    			}
    		}
    	}
    	
    	// 操作日志信息 TODO 需求待定
    	OrgModifyRecord orgModifyRecord = new OrgModifyRecord();
    	
    	orgModifyRecordService.save(orgModifyRecord);
    	
    	this.saveOrUpdate(organization);
    	
    	return R.ok(organization);
    }
    
   /**
    * @description 获取组织编码 
    * 
    * @author      shuling
    * @date        2020-10-18 10:37:22
    * @param       parentId
    * @return
    */
	String getOrgCode(Long parentId) {

		// 最大组织编码
		String maxOrgCode = this.baseMapper.getMaxOrgCode(parentId);
		
		// 首次新增 默认 001
		maxOrgCode = StrUtil.isBlank(maxOrgCode) ? "001" : maxOrgCode;

		return String.format("%0" + maxOrgCode.length() + "d", Long.parseLong(maxOrgCode) + 1);

	}
	
	/**
	 * @description 设置 组织编码、组织名称全路径 例如：orgFullname /广东省/广州市/天河区
	 * @author      shuling
	 * @date        2020-10-18 10:42:29
	 * @param       organization
	 * @param       parentOrganization
	 */
    void setOrgFullCodeAndOrgFullName(Organization organization, Organization parentOrganization) {
    	
    	String pathSeparator = "/";
    	
    	if(ObjectUtil.isNull(parentOrganization)) {
    		
    		// 取当前组织信息
    		organization.setOrgFullname(pathSeparator + organization.getOrgName());
    	}else {
    		
    		// 父orgFull + org
    		organization.setOrgFullname(parentOrganization.getOrgName() + pathSeparator + organization.getOrgName());
    	}
    }
    
	@Override
	public R<List<OrganizationComponentVO>> selectOrganizations() {
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

    @Override
    public boolean saveOrgChart(OrgChartFormDTO form){
        Organization entity = new Organization();
        BeanUtils.copyProperties(form, entity);
        long parentId = entity.getParentId();
        String orgCode = getOrgCode(parentId);
        entity.setOrgCode(orgCode);
        return this.save(entity);
    }

    @Override
    public boolean editOrgChart(OrgChartFormDTO form){
        Organization entity = new Organization();
        BeanUtils.copyProperties(form, entity);
        return this.updateById(entity);
    }
}
