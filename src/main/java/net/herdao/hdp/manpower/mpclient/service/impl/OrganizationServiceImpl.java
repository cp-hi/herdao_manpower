
package net.herdao.hdp.manpower.mpclient.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartDTO;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffOrgDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.mapper.OrganizationMapper;
import net.herdao.hdp.manpower.mpclient.service.OrgModifyRecordService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.manpower.mpclient.utils.StringBufferUtils;
import net.herdao.hdp.manpower.mpclient.vo.OrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationFormVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationTreeVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;

/**
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

	@Autowired
    private PostService postService;
	@Autowired
    private UserService userService;
	@Autowired
    private UserpostService userpostService;
	@Autowired
    private RemoteUserService remoteUserService;
	@Autowired
	private SysDictItemService sysDictItemService;
	@Autowired
    private OrgModifyRecordService orgModifyRecordService;

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
    public List<OrganizationTreeVO> organizationTreeList(String orgCode, String searchText){
    	return this.baseMapper.organizationTreeList(orgCode, searchText);
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
		Organization organization = this.getById(id);
		
		List<Organization> organizations = this.baseMapper.selectOrganizationByOrgCode(organization.getOrgCode());
		if(ObjectUtil.isNull(organizations)) {
			organizations = new ArrayList<Organization>();
		}
		
		organizations.add(organization);
		organizations.forEach(org ->{
			// 设置停用日期
			org.setStopDate(DateUtil.parseDate(stopDateStr));
			// 设置为停用 TOTO 后期关联定时任务
			org.setIsStop(true);
		});
		
		this.saveOrUpdateBatch(organizations);

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
		// 查询待停用的组织信息
		List<Organization> organizations = this.lambdaQuery().le(Organization::getStopDate, DateUtil.parseDate(DateUtil.formatDate(new Date())))
											   .ne(Organization::getIsStop, true).list();
		if (ObjectUtil.isNotEmpty(organizations)) {
			organizations.forEach(organization -> {
				// 当前组织下在职员工数
				Integer countUser = userService.getCountUser(organization.getOrgCode(), 0);

				// 组织没有在职用户
				if (countUser == 0) {
					
					List<Organization> organizationChildrens = this.baseMapper.selectOrganizationByOrgCode(organization.getOrgCode());
					if(ObjectUtil.isNull(organizationChildrens)) {
						organizationChildrens = new ArrayList<Organization>();
					}
					
					organizationChildrens.add(organization);
					organizationChildrens.forEach(org ->{
						// 设置为停用 TOTO 后期关联定时任务
						org.setIsStop(true);
					});
					
					this.saveOrUpdateBatch(organizations);
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
    	
		Organization organization = this.getById(id);

		// 设置停用日期
		organization.setStopDate(null);

		// 设置启用
		organization.setStopDate(DateUtil.parseDate(startDateStr));
		// 设置为启用 TOTO 后期关联定时任务
		organization.setIsStop(false);
		
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
    	// 查询待启用的组织信息
    	List<Organization> organizations = this.lambdaQuery().le(Organization::getStartDate, DateUtil.parseDate(DateUtil.formatDate(new Date())))
    												   .ne(Organization::getIsStop, false).list();
    	
    	if (ObjectUtil.isNotEmpty(organizations)) {
			organizations.forEach(organization -> {
				// 设置为停用 TOTO 后期关联定时任务
				organization.setIsStop(false);
			});
			
			this.saveOrUpdateBatch(organizations);
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
        
        // 删除组织 TOTO 待使用脚本删除方式
        Set<Organization> organizationSet = new HashSet<Organization>();
        
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
        	organizationSet.add(organization);
        }
        
        if(message.length() > 0) {
        	return R.failed("删除失败，组织下不能存在人员，包括离职人员、派遣员等， 以下组织存在人员信息：" + message);
        }
        
        for(int i=0; i<orgCodes.size(); i++) {
        	List<Organization> organizations =  this.baseMapper.selectOrganizationByOrgCode(orgCodes.get(i));
        	if(ObjectUtil.isNotEmpty(organizations)) {
        		organizations.forEach(organization ->{
        			organizationSet.add(organization);
        		});
        	}
        }
        
        // 批量逻辑删除
        if(ObjectUtil.isNotEmpty(organizationSet)) {
        	organizationSet.forEach(org ->{
        		LambdaUpdateWrapper<Organization> updateWrapper = Wrappers.lambdaUpdate();
                updateWrapper.set(Organization::getDelFlag, true)
                			 .eq(Organization::getId, org.getId());
                this.update(updateWrapper);
        	});
        }
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
            condition.setIsStop(false);
        }
        List<Organization> rootOrgans = this.baseMapper.findRootOrganizations(condition);

        if (!rootOrgans.isEmpty()) {
            for (Organization rootOrgan : rootOrgans) {
                Organization childrenCondition = new Organization();
                childrenCondition.setParentId(rootOrgan.getId());
                //默认加载启用状态的组织架构
                childrenCondition.setIsStop(false);

                List<Organization> childrenOrgans = this.baseMapper.findOrganizationByCondition(childrenCondition);
                rootOrgan.setChildren(childrenOrgans);
            }

        }

        return R.ok(rootOrgans);
    }

    @Override
    public Page<OrganizationVO> findOrgPage(Page<OrganizationVO> page, String orgCode, Integer stop, String searchText) {
        return this.baseMapper.findOrgPage(page, orgCode, stop, searchText);
    }

    @Override
    public OrganizationFormVO findOrgDetails(Long id) {
    	return this.baseMapper.findOrgDetails(id);
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
    	
    	// 组织id
    	Long id = organizationVO.getId();
    	
    	// 更新前组织信息
    	Organization tpOrganization = ObjectUtil.isNull(id) ? null : this.getById(id);
    	
    	// 父组织id
    	Long parentId = organizationVO.getParentId();
    	
    	// 父组织信息
    	Organization parentOrganization = ObjectUtil.isNull(parentId) ? null : this.getById(parentId);
    	
    	// 校验同一个组织下不能存在组织名称一样的组织
    	if(ObjectUtil.isNotNull(parentOrganization)) {
    		List<Organization> orgs = this.lambdaQuery().eq(Organization::getOrgName, organization.getOrgName())
    						  .eq(Organization::getParentId, parentId)
    						  .ne(id != null, Organization::getId, id).list();
    		if(ObjectUtil.isNotEmpty(orgs)) {
    			return R.failed("组织名称已经存在，请修改后保存！");
    		}
    	}
    	
    	if(StrUtil.isBlank(organization.getOrgCode()) || tpOrganization.getParentId() != organization.getParentId()) {
    		
    		// 设置组织编码
        	organization.setOrgCode(getOrgCode(organization.getParentId()));
    	}
    	
    	// 设置 组织编码、组织名称全路径
    	setOrgFullCodeAndOrgFullName(organization, parentOrganization);
    	
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
    		
    		// 更新了父组织
    		if(tpOrganization.getParentId() != organization.getParentId()) {
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
    	
    	// 保存组织
    	this.saveOrUpdate(organization);
    	
    	// 更新上级组织为NULL
    	if(ObjectUtil.isNull(parentId)) {
    		LambdaUpdateWrapper<Organization> updateWrapper = Wrappers.lambdaUpdate();
            updateWrapper.set(Organization::getParentId, null)
            			 .eq(Organization::getId, organization.getId());
            this.update(updateWrapper);
    	}
    	
    	// 操作日志信息 TODO 如果父组织更新了，是否记录子组织操作日志， 需求待优化
    	if(ObjectUtil.isNotNull(id)) {
    		orgModifyRecordService.saveOrgModifyRecord(organization, tpOrganization);
    	}
    	
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
		
		String defaultOrgCode = "001";

		// 最大组织编码
		String maxOrgCode = this.baseMapper.getMaxOrgCode(parentId);
		
		if(StrUtil.isBlank(maxOrgCode) && ObjectUtil.isNotNull(parentId)) {
			return maxOrgCode = this.getById(parentId).getOrgCode() + defaultOrgCode;
		}
			
		// 首次新增 默认 001
		maxOrgCode = StrUtil.isBlank(maxOrgCode) ? defaultOrgCode : maxOrgCode;

		return String.format("%0" + maxOrgCode.length() + "d", Long.parseLong(maxOrgCode) + 1);

	}
	
	/**
	 * @description 设置 组织名称全路径 例如：orgFullname /广东省/广州市/天河区
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

	@Override
	public List<OrganizationAddDTO> selectAllOrganization() {
		return this.baseMapper.selectAllOrganization();
	}
	
	/**======================================  组织导入 start ====================================**/
	
	@Override
	public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {

		// 错误数组
		List<ExcelCheckErrDTO> errList = new ArrayList<>();

		Map<String, OrganizationAddDTO> parentOrganizationMap = getParentOrganizationMap(this.baseMapper.selectAllOrganization());

		// 组织类型
		List<SysDictItem> orgTypeList = sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, "ZZLX"));

		// 用户信息
		Map<String, User> userMap = getUserMap(userService.lambdaQuery().list());

		// 岗位信息
		Map<String, Post> postMap = getPostMap(postService.lambdaQuery().list());

		List<Organization> organizationList = new ArrayList<Organization>();

		if (importType == 0) {
			importAddOrganization(excelList, parentOrganizationMap, orgTypeList, userMap, postMap, errList, organizationList);
		}
		// 保存新增、修改组织信息
		if(ObjectUtil.isEmpty(errList)) {
			this.saveOrUpdateBatch(organizationList, null);
		}
		
		return errList;
	}
	
	/**
	 * 组织批量新增
	 * 
	 * @param excelList
	 * @param parentOrganizationMap
	 * @param orgTypeList
	 * @param userMap
	 * @param postMap
	 * @param errList
	 * @param organizationList
	 */
	public void importAddOrganization(List excelList, Map<String, OrganizationAddDTO> parentOrganizationMap, List<SysDictItem> orgTypeList, Map<String, User> userMap,
			Map<String, Post> postMap, List<ExcelCheckErrDTO> errList, List<Organization> organizationList) {
		// 导入校验
		for (int i = 0; i < excelList.size(); i++) {

			Organization organization = new Organization();

			// 导入组织信息
			OrganizationAddDTO orgDto = (OrganizationAddDTO) excelList.get(i);
			// 异常信息
			StringBuffer errMsg = null;

			// 父组织信息
			Organization parentOrganization = null;
			String parentOrgCode = orgDto.getParentOrgCode();

			if (ObjectUtil.isNotNull(parentOrgCode)) {
				// 获取父组织信息
				OrganizationAddDTO parentOrgDto = parentOrganizationMap.get(orgDto.getParentOrgCode());
				if (ObjectUtil.isNotNull(parentOrgDto)) {
					parentOrganization = new Organization();
					BeanUtils.copyProperties(orgDto, parentOrganization);
					organization.setParentId(parentOrganization.getId());
				}
			}

			// 校验父组织信息
			if (ObjectUtil.isNull(parentOrganization)) {
				StringBufferUtils.appendStringBuffer(errMsg, "组织编码：" + orgDto.getParentOrgCode() + "不存在");
			}

			// 校验组织类型
			String orgType = getDictItem(orgTypeList, orgDto.getOrgType());
			if (StrUtil.isNotBlank(orgType)) {
				StringBufferUtils.appendStringBuffer(errMsg, "组织类型：" + orgDto.getOrgType() + "不存在");
			} else {
				// 转字典value
				organization.setOrgType(orgType);
			}
			// 校验用户信息
			User user = userMap.get(orgDto.getOrgChargeWorkNo());
			if (user == null) {
				StringBufferUtils.appendStringBuffer(errMsg, "组织负责人工号：" + orgDto.getOrgChargeWorkNo() + "不存在");
			} else {
				organization.setOrgChargeId(StrUtil.toString(user.getId()));
				organization.setOrgChargeName(user.getUserName());
				organization.setOrgChargeWorkNo(user.getLoginCode());
			}

			Post post = postMap.get(orgDto.getPostCode());
			if (post == null) {
				StringBufferUtils.appendStringBuffer(errMsg, "岗位编号：" + orgDto.getPostCode() + "不存在");
			} else {
				organization.setPostId(post.getId());
			}
			// 设置组织编码
			organization.setOrgCode(getOrgCode(organization.getParentId()));
			// 设置组织层级
			organization.setOrgTreeLevel(parentOrganization.getOrgTreeLevel() + 1L);
			// 设置 组织名称全路径
			setOrgFullCodeAndOrgFullName(organization, parentOrganization);
			if (StrUtil.isNotBlank(errMsg)) {
				errList.add(new ExcelCheckErrDTO(orgDto, errMsg.toString()));
			} else {
				organizationList.add(organization);
			}
		}
	}
	
	/**
     * 获取组织集合 key = orgName + parentOrgCode 作为唯一标识
     * @return
     */
    Map<String, OrganizationAddDTO> getOrganizationMap(){
    	
       Map<String, OrganizationAddDTO> renderMap = new HashMap<String, OrganizationAddDTO>();
       List<OrganizationAddDTO> orgList = this.selectAllOrganization();
       if(ObjectUtil.isNotEmpty(orgList)) {
    	   orgList.forEach(org ->{
    		   // 唯一标识，用于数据导入校验
    		   String key = org.getOrgName() + ManpowerContants.SEPARATOR + org.getParentOrgCode();
    		   renderMap.put(key, org);
    	   });
       }
       return renderMap;
    }
    
    /**
     * 组织集合 orgCode 作为唯一标识
     * 
     * @param orgList
     * @return
     */
    Map<String, OrganizationAddDTO> getParentOrganizationMap(List<OrganizationAddDTO> orgList){
    	return orgList.stream().collect(Collectors.toMap(OrganizationAddDTO::getOrgCode, (p) -> p)); 
    }
    
	 
	/**
	 * 获取字典value值
	 * 
	 * @param sysDictItemList
	 * @param label
	 * @return
	 */
	public String getDictItem(List<SysDictItem> sysDictItemList, String label) {
		if (ObjectUtil.isNotEmpty(sysDictItemList)){
			for(SysDictItem dict : sysDictItemList) {
				if(dict.getLabel().equals(label)) {
					return dict.getValue();
				}
			}
        }
		return null;
	}
	
	/**
	 * 用户信息
	 * 
	 * @return
	 */
	public Map<String, User> getUserMap(List<User> userList){
		Map<String, User> renderMap = new HashMap<String, User>();
		if(ObjectUtil.isNotEmpty(userList)) {
			userList.forEach(us ->{
				renderMap.put(us.getLoginCode(), us);
			});
		}
		return renderMap;
	}
	
	/**
	 * 岗位信息
	 * 
	 * @return
	 */
	public Map<String, Post> getPostMap(List<Post> postList){
		Map<String, Post> renderMap = new HashMap<String, Post>();
		if(ObjectUtil.isNotEmpty(postList)) {
			postList.forEach(ps ->{
				renderMap.put(ps.getPostCode(), ps);
			});
		}
		return renderMap;
	}
	
	/**======================================  组织导入 end ====================================**/

}