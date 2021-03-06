package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.comm.UserMsgDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.staff.StaffAddVM;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.staff.StaffUpdateVM;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationImportDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.*;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.StaffComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffTotalComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.StaffBasicVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffBasicPositiveVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPage;
import net.herdao.hdp.manpower.sys.mapper.SysDictItemMapper;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.service.SysSequenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {

    @Autowired
	private FamilystatusService familystatusService;

    @Autowired
	private StaffeducationService staffeducationService;

	@Autowired
	private WorkexperienceService workexperienceService;
	
	@Autowired
	private UserpostService userpostService;
	
	@Autowired
	private StafftransactionService stafftransactionService;
	
	@Autowired
	private StaffPracticeService staffPracticeService;
	
	@Autowired
	private StaffProTitleService staffProTitleService;

	@Autowired
	private StaffcontractService staffcontractService;

	@Autowired
    private RemoteUserService remoteUserService;

	@Autowired
	private SysSequenceService sysSequenceService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private SysDictItemService sysDictItemService;

	private final static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	@SuppressWarnings("all")
	@Transactional(rollbackFor = Exception.class)
	public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {

		// 错误数组
		List<ExcelCheckErrDTO> errList = new ArrayList<>();

		// 批量新增、编辑组织信息
		List<Staff> staffList = new ArrayList<>();
		List<User> userList = new ArrayList<>();

		List<OrganizationImportDTO> orgList = organizationService.selectAllOrganization();
		Map<String, Long> renderMap = new HashMap<>();
		if(ObjectUtil.isNotEmpty(orgList)) {
			orgList.forEach(org ->{
				renderMap.put(org.getOrgCode(), org.getId());
			});
		}

		if (importType == 0) {
			long code = sysSequenceService.getNext("staff_code");
			for(int i=0;i<excelList.size();i++){
				StaffAddVM entity = (StaffAddVM)excelList.get(i);
				if(entity.getEntryTimeStr()!=null){
					LocalDate entryTime = LocalDate.parse(entity.getEntryTimeStr(), fmt);
					entity.setEntryTime(entryTime);
				}
				Staff staff = new Staff();
				BeanUtils.copyProperties(entity, staff);
				String staffCode = code + "";
				Long OrgId = renderMap.get(entity.getOrgCode());

				User user = new User();
				user.setLoginCode(staffCode);
				user.setUserName(entity.getStaffName());
				user.setOrgDeptId(OrgId);
				userList.add(user);

				staff.setStaffCode(staffCode);
				staffList.add(staff);
				code++;
			}
			sysSequenceService.updateSeq("staff_code", code-1);
			userService.saveOrUpdateBatch(userList, 200);
			List<Staff> staffListNew = new ArrayList<>();
			Staff staff;
			for(int i=0;i<staffList.size();i++){
				staff = staffList.get(i);
				staff.setUserId(userList.get(i).getId());
				staffListNew.add(staff);
			}
			staffList = staffListNew;
			//add
		}else {
			//update
			List<Staff> staffAllList = this.list();
			Map<String, Long> renderStaffMap = new HashMap<>();
			if(ObjectUtil.isNotEmpty(staffAllList)) {
				staffAllList.forEach(staff ->{
					renderStaffMap.put(staff.getStaffCode(), staff.getId());
				});
			}
			List<User> userAllList = userService.list();
			Map<String, Long> renderUserMap = new HashMap<>();
			if(ObjectUtil.isNotEmpty(userAllList)) {
				userAllList.forEach(user ->{
					renderUserMap.put(user.getLoginCode(), user.getId());
				});
			}
			for(int i=0;i<excelList.size();i++){
				StaffUpdateVM entity = (StaffUpdateVM)excelList.get(i);
				if(entity.getEntryTimeStr()!=null){
					LocalDate entryTime = LocalDate.parse(entity.getEntryTimeStr(), fmt);
					entity.setEntryTime(entryTime);
				}
				Staff staff = new Staff();
				BeanUtils.copyProperties(entity, staff);
				Long id = renderStaffMap.get(entity.getStaffCode());
				staff.setId(id);
				staffList.add(staff);

				Long OrgId = renderMap.get(entity.getOrgCode());
				User user = new User();
				user.setUserName(entity.getStaffName());
				user.setOrgDeptId(OrgId);
				Long userId = renderUserMap.get(entity.getStaffCode());
				user.setId(userId);
				userList.add(user);
			}
			userService.saveOrUpdateBatch(userList, 200);
		}
		// 保存新增、修改组织信息
		if(ObjectUtil.isEmpty(errList)) {
			this.saveOrUpdateBatch(staffList, 200);
		}
		return errList;
	}
    
	@SuppressWarnings("all")
	@Override
	public R<List> selectStaffOrganizationComponent(String orgCode, String batchSelectOrgCodes, String staffIds, String searchText) {
		
		// 组织、员工信息集合
		List stfList = new ArrayList<>();
		
		// 默认查询一级组织信息
		if(StrUtil.isAllBlank(orgCode, staffIds, batchSelectOrgCodes, searchText)) {
			 stfList.addAll(recursion(this.baseMapper.selectOrganizations(), null));
		}else {
			if(StrUtil.isNotBlank(orgCode)) {
				// 子组织信息
				List<StaffOrganizationComponentVO> organizations = this.baseMapper.selectOrganizationComponentList(orgCode);
				// 设置子组织信息
				if(ObjectUtil.isNotEmpty(organizations)) {
					stfList.addAll(recursion(organizations, orgCode));
				}
			}
			// 组织下员工信息
			List<StaffComponentVO> staffs = this.baseMapper.selectStaffs(orgCode, (StrUtil.isBlank(batchSelectOrgCodes) ? null  : batchSelectOrgCodes.split(ManpowerContants.EN_SEPARATOR)), 
					(StrUtil.isBlank(staffIds) ? null : staffIds.split(ManpowerContants.EN_SEPARATOR)), searchText);
			if(ObjectUtil.isNotEmpty(staffs)) {
				stfList.addAll(staffs);
			}
		}
		return R.ok(stfList);
	}
	
	public List<StaffOrganizationComponentVO> recursion(List<StaffOrganizationComponentVO> organizations, String orgCode){
		// 获取部门员工数
		Map<String, Integer> taffTotalComponentMap = this.baseMapper.getStaffTotals(orgCode).stream()
				                                         .collect(Collectors.toMap(StaffTotalComponentVO::getOrgCode, StaffTotalComponentVO::getTotal));
		// 组织员工数
		organizations.forEach(organization -> {
			Integer staffTotal = sumKeyLike(taffTotalComponentMap, organization.getOrgCode());
			organization.setStaffTotal(staffTotal);
		});
		return organizations;
	}
	
	/**
	 * map 集合模糊匹配 合计
	 * 
	 * @param dataMap map集合
	 * @param keyLike 模糊key
	 * @return
	 */
	public Integer sumKeyLike(Map<String, Integer> dataMap, String keyLike) {
		Integer valSum = 0;
		if(!StrUtil.isBlank(keyLike)) {
			for (Map.Entry<String, Integer> entity : dataMap.entrySet()) {
				if (entity.getKey().startsWith(keyLike)) {
					valSum += entity.getValue();
				}
			}
		}
		return valSum;
	}

	@Override
	public Map<String, Object> queryCount(Long groupId,Long orgId){
		//统计全部员工 ，全部员工=在职员工， jobType=1.(全职),staffScope=1 (在职）
		int total =  baseMapper.getStaffCount(groupId,null,orgId,"1");

		//统计全职员工数  jobType=1(全职)
		int jobType1 = baseMapper.getStaffCount(groupId,"1",orgId,"1");

		int jobType2 = baseMapper.getStaffCount(groupId,"2",orgId,"1");
		int jobType3 = baseMapper.getStaffCount(groupId,"7",orgId,"1");
		int toJoin = 0;
		int toLeave = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("total",total);
		map.put("jobType1",jobType1);
		map.put("jobType2",jobType2);
		map.put("jobType3",jobType3);
		map.put("toJoin",toJoin);
		map.put("toLeave",toLeave);
		return map;
	}

	@Override
	public IPage staffPage(Page page, StaffListDTO staff, String searchText){
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("searchText", searchText);
		map.put("isStop", staff.getIsStop());
		map.put("jobType", staff.getJobType());
		map.put("groupId", staff.getGroupId());
		map.put("postId", staff.getPostId());
		map.put("sectionId", staff.getSectionId());
		map.put("pipelineId", staff.getPipelineId());
		map.put("jobLevelId1", staff.getJobLevelId1());
		map.put("orgId",staff.getOrgId());
		page = page.setRecords(baseMapper.staffPage(map));
		return page;
	}

    @Transactional(rollbackFor = Exception.class)
	@Override
	public StaffDetailDTO staffSave(StaffDetailDTO staffForm){
		Staff staff = new Staff();
		BeanUtils.copyProperties(staffForm, staff);
		long code = sysSequenceService.getNext("staff_code");
		String staffCode = code + "";
		User user = new User();
		user.setLoginCode(staffCode);
		user.setUserName(staff.getStaffName());
		user.setOrgDeptId(staffForm.getOrgDeptId());
		userService.save(user);
		Userpost up = new Userpost();
		up.setOrgDeptId(staffForm.getOrgDeptId());
		up.setPostId(staffForm.getPostId());
		up.setUserId(user.getId());
		up.setMainPost(true);
		userpostService.save(up);

		staff.setStaffCode(staffCode);
		staff.setUserId(user.getId());
		this.save(staff);
		BeanUtils.copyProperties(staff, staffForm);
		return staffForm;
	}

    @Override
	public void updateEduLast(Long id){
	    if(id==null){
	        return;
        }
        List<Staffeducation> eduList = staffeducationService.list(new QueryWrapper<Staffeducation>()
                .eq("STAFF_ID", id)
                .orderByDesc("END_DATE")
                .last("limit 1")
        );
        StaffEducationLastDTO educationLast = new StaffEducationLastDTO();
        if(eduList!=null && eduList.size()!=0){
            Staffeducation staffeducation = eduList.get(0);
            BeanUtils.copyProperties(staffeducation, educationLast);
        }
        Staff staff = new Staff();
        BeanUtils.copyProperties(educationLast, staff);
        staff.setId(id);
        this.updateById(staff);
    }

    @Autowired
	private StaffMapper staffMapper;
	@Autowired
	private SysDictItemMapper sysDictItemMapper;


	@Override
	public StaffBasicVO selectBasicById(Long id) throws Exception {
		// 查询员工信息
		QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper();
		staffQueryWrapper.eq("id", id);
		Staff staff = staffMapper.selectOne(staffQueryWrapper);
		if (staff == null) {
			throw new Exception("该员工不存在");
		}
		UserMsgDTO user = userService.getUserMsg(staff.getUserId());
		if (user == null) {
			throw new Exception("该用户不存在");
		}
		return convert2StaffBasicVO(user);

	}

	@Override
	public StaffBasicVO selectBasicByUserId(Long userId) throws Exception {
		// 查询员工信息
		UserMsgDTO user = userService.getUserMsg(userId);
		if (user == null) {
			throw new Exception("该用户不存在");
		}
		return convert2StaffBasicVO(user);
	}

	private StaffBasicVO convert2StaffBasicVO(UserMsgDTO user) throws Exception {
		if (user != null) {
			StaffBasicVO vo = new StaffBasicVO();
			vo.setUserId(user.getId());
			if ((user.getUserName() != null) && (user.getStaffCode()!= null)) {
				String name = user.getUserName();
				String code = user.getStaffCode();
				vo.setStaffNameAndCode(name + "(" + code + ")");
			}
			if (user.getStaffScope() != null) {
				QueryWrapper<SysDictItem> staffScopeQueryWrapper = new QueryWrapper<>();
				SysDictItem staffScope = sysDictItemMapper.selectOne(staffScopeQueryWrapper.eq("type", "RYGSFW")
						.eq("value", user.getStaffScope()));
				vo.setStaffScope(staffScope.getLabel());
			}
			if (user.getJobType() != null) {
				QueryWrapper<SysDictItem> jobTypeQueryWrapper = new QueryWrapper<>();
				SysDictItem jobType = sysDictItemMapper.selectOne(jobTypeQueryWrapper.eq("type","JOB_TYPE")
						.eq("value", user.getJobType()));
				vo.setStaffJobType(jobType.getLabel());
			}
			vo.setNowOrgId(user.getOrgId());
			if (user.getOrgName() != null){
				vo.setNowOrgName(user.getOrgName());
			}
			// TODO:: 待抽象出去
			// 获取 group 不能根据 orgId 直接去拿，
			// 现根据当前的 orgId 获取到当前的 orgCode
			// 再根据当前的 orgCode 左边高3位数据获取到当前组织的顶层组织 topOrgId
			// 再将 topOrgId 作为参数传给 groupId
			Organization currentOrg = organizationService.getById(user.getOrgId());
			if (currentOrg != null) {
				String currentOrgCode = currentOrg.getOrgCode();
				String currentTopOrgCode = currentOrgCode.substring(0,3);
				QueryWrapper<Organization> wrapper = new QueryWrapper();
				Organization currentTopOrg = organizationService.getOne(wrapper.eq("org_code", currentTopOrgCode));
				if (currentTopOrg != null) {
					QueryWrapper<Group> groupQueryWrapper = new QueryWrapper();
					Group group = groupService.getOne(groupQueryWrapper.eq("org_id", currentTopOrg.getId()));
					if (group != null) {
						vo.setGroupId(group.getId());
					}
				}
			}

			vo.setNowPostOrgId(user.getPostOrgId());
			if (user.getPostName() != null) {
				vo.setNowPostOrgName(user.getPostName());
			}
			vo.setNowJobLevelId(user.getJobLevelId());
			if (user.getJobLevelName() != null) {
				vo.setNowJobLevelName(user.getJobLevelName());
			}
			vo.setEntryTime(user.getEntryTime());
			vo.setAge(user.getAge());
			if (StringUtils.isNotBlank(user.getSex())) {
				vo.setSex(user.getSex());
			}
			if (StringUtils.isNotBlank(user.getBirthplace())) {
				vo.setBirthplace(user.getBirthplace());
			}
			return vo;
		}
		return null;
	}

	@Override
	public Staff getByUserId(Long userId) {
		QueryWrapper<Staff> wrapper = new QueryWrapper<>();
		return staffMapper.selectOne(wrapper.eq("user_id", userId));
	}

	@Override
    public Map<String, Object> getStaffDetail(Long id){
		Staff staff = this.getById(id);
		StaffBaseDTO base = baseMapper.getStaffBase(id);
		if(base==null){
			base = new StaffBaseDTO();
		}
		StaffInfoDTO info = baseMapper.getStaffInfo(id);
		if(info==null){
			info = new StaffInfoDTO();
		}
		StaffJobInfoDTO jobInfo = baseMapper.getStaffJobInfo(id);
		if(jobInfo==null){
			jobInfo = new StaffJobInfoDTO();
		}
		StaffInfoOtherDTO infoOther = new StaffInfoOtherDTO();
		StaffEmergencyDTO emergency = new StaffEmergencyDTO();
		BeanUtils.copyProperties(staff, base);
		BeanUtils.copyProperties(staff, info);
		BeanUtils.copyProperties(staff, jobInfo);
		BeanUtils.copyProperties(staff, infoOther);
		BeanUtils.copyProperties(staff, emergency);
		Map<String, Object> map = new HashMap<>();
		map.put("staffBaseDTO", base);
		map.put("staffInfoDTO", info);
		map.put("staffJobInfoDTO", jobInfo);
		map.put("staffInfoOtherDTO", infoOther);
		map.put("staffEmergencyDTO", emergency);

		List<StaffFamilyDTO> familyDtoList = familystatusService.findStaffFamilyStatus(id.toString());
		map.put("staffFamilyDTO", familyDtoList);

		List<Staffeducation> eduList = staffeducationService.list(new QueryWrapper<Staffeducation>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("END_DATE")
				.last("limit 1")
		);
		StaffEducationLastDTO educationLast = new StaffEducationLastDTO();
		if(eduList!=null && eduList.size()!=0){
			Staffeducation staffeducation = eduList.get(0);
			BeanUtils.copyProperties(staffeducation, educationLast);
		}else{
			educationLast = null;
		}
		map.put("staffEducationLastDTO", educationLast);
		return map;
	}

	@Override
	public Map<String, Object> getHomePage(Long id){
		Staff staff = this.getById(id);
		StaffBaseDTO base = baseMapper.getStaffBase(id);
		if(base==null){
			base = new StaffBaseDTO();
		}
		StaffArchiveDTO archive = baseMapper.getStaffArchive(id);
		if(archive==null){
			archive = new StaffArchiveDTO();
		}
		StaffWelfareDTO welfare = baseMapper.getStaffWelfare(id);
		if(welfare==null){
			welfare = new StaffWelfareDTO();
		}
		StaffCarreraDTO carrera = new StaffCarreraDTO();
		BeanUtils.copyProperties(staff, base);
		BeanUtils.copyProperties(staff, archive);
		BeanUtils.copyProperties(staff, welfare);
		BeanUtils.copyProperties(staff, carrera);
		Map<String, Object> map = new HashMap<>();
		map.put("staffBaseDTO", base);
		map.put("staffArchiveDTO", archive);
		map.put("staffWelfareDTO", welfare);
		map.put("staffCarreraDTO", carrera);
		List<StaffJobTravelDTO> uphDtoList = baseMapper.getJobTravel(id);
		map.put("staffJobTravelDTO", uphDtoList);

		List<Workexperience> expList = workexperienceService.list(new QueryWrapper<Workexperience>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("BEGIN_DATE")
		);
		List<StaffWorkExpDTO> expDtoList = new ArrayList<>();
		StaffWorkExpDTO workExp;
		for(int i=0;i<expList.size();i++){
			workExp = new StaffWorkExpDTO();
			BeanUtils.copyProperties(expList.get(i), workExp);
			workExp.setBeginDate(LocalDateTimeUtils.convert2Long(expList.get(i).getBeginDate()));
			workExp.setEndDate(LocalDateTimeUtils.convert2Long(expList.get(i).getEndDate()));
			expDtoList.add(workExp);
		}
		map.put("staffWorkExpDTO", expDtoList);

		List<Familystatus> familyList = familystatusService.list(new QueryWrapper<Familystatus>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("MODIFIED_TIME")
		);
		List<StaffFamilyDTO> familyDtoList = new ArrayList<>();
		StaffFamilyDTO family;
		for (Familystatus familystatus : familyList) {
			family = new StaffFamilyDTO();
			SysDictItem relationsType = sysDictItemService.getDictItemByTypeAndValue("RELATIONS_TYPE", familystatus.getRelations());
			familystatus.setRelations(relationsType.getLabel());

			BeanUtils.copyProperties(familystatus, family);
			familyDtoList.add(family);
		}
		map.put("staffFamilyDTO", familyDtoList);

		List<Staffeducation> eduList = staffeducationService.list(new QueryWrapper<Staffeducation>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("END_DATE")
				.last("limit 1")
		);
		StaffEducationLastDTO educationLast = baseMapper.getStaffEducationLast(id);
		if(educationLast==null){
			educationLast = new StaffEducationLastDTO();
		}
		if(eduList!=null && eduList.size()!=0){
			Staffeducation staffeducation = eduList.get(0);
			BeanUtils.copyProperties(staffeducation, educationLast);
		}else{
			educationLast = null;
		}
		map.put("staffEducationLastDTO", educationLast);
		return map;
	}

	@Override
	public Map<String, Object> getStaffWelfare(Long id){
		Staff staff = this.getById(id);
		StaffBaseDTO base = baseMapper.getStaffBase(id);
		if(base==null){
			base = new StaffBaseDTO();
		}
		StaffSecurityDTO security = baseMapper.getStaffSecurity(id);
		if(security==null){
			security = new StaffSecurityDTO();
		}
		StaffFundDTO fund = new StaffFundDTO();
		StaffSalaryDTO salary = baseMapper.getStaffSalary(id);
		if(salary==null){
			salary = new StaffSalaryDTO();
		}
		BeanUtils.copyProperties(staff, base);
		BeanUtils.copyProperties(staff, security);
		BeanUtils.copyProperties(staff, fund);
		BeanUtils.copyProperties(staff, salary);
		Map<String, Object> map = new HashMap<>();
		map.put("staffBaseDTO", base);
		map.put("staffSecurityDTO", security);
		map.put("staffFundDTO", fund);
		map.put("staffSalaryDTO", salary);

		List<Staffcontract> contractList = staffcontractService.list(new QueryWrapper<Staffcontract>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("START_DATE")
		);
		List<StaffContractDetailDTO> contractDtoList = new ArrayList<>();
		StaffContractDetailDTO contractDto;
		for(int i=0;i<contractList.size();i++){
			contractDto = new StaffContractDetailDTO();
			BeanUtils.copyProperties(contractList.get(i), contractDto);
			contractDtoList.add(contractDto);
		}
		map.put("staffContractDetailDTO", contractDtoList);
		return  map;
	}
	

	/**
	 * 工作情况
	 * @param id 用户id
	 * @author lift
	 * @date 2020-10-15 21:09:29
	 * @return
	 */
	@Override
	public Map<String, Object> getStaffWork(Long id){
		Staff staff = this.getById(id);
		Map<String, Object> map = new HashMap<>();
		
		/*-------------目前任职 star-----------------*/
		UserpostDTO userpostDto = userpostService.findCurrentJob(id);
		map.put("userpostDto", userpostDto);
		/*-------------目前任职 end-----------------*/
				
		/*-------------异动情况 star-----------------*/
		List<StafftransactionDTO> stafftransactionDTOList = stafftransactionService.findStafftransactionDto(id);
		map.put("stafftransactionDTOList", stafftransactionDTOList);
		/*-------------异动情况 end-----------------*/
		
		
		/*-------------工作年限 star-----------------*/
		StaffWorkYearDTO staffWorkYearDTO = baseMapper.getStaffWorkYear(id);
		map.put("staffWorkYearDTO", staffWorkYearDTO);
		/*-------------工作年限 end-----------------*/
		
		/*-------------工作经历 star-----------------*/
		List<WorkexperienceDTO> workexperienceDTOList = workexperienceService.findWorkexperienceDTO(id);
		map.put("workexperienceDTOList", workexperienceDTOList);
		/*-------------工作经历 end-----------------*/
		
		/*-------------实习记录 star-----------------*/
		StaffPracticeDTO staffPracticeDTO = staffPracticeService.findStaffPractice(id);
		if (staffPracticeDTO == null)
		{
			staffPracticeDTO = new StaffPracticeDTO();
			staffPracticeDTO.setStaffId(id);
		}
		map.put("staffPracticeDTO", staffPracticeDTO);
		/*-------------实习记录 end-----------------*/
		
		/*-------------职称及职业资格 star-----------------*/
		List<StaffProTitleDTO> staffProTitleList = staffProTitleService.findStaffProTitleDTO(id);
		map.put("staffProTitleList", staffProTitleList);
		/*-------------职称及职业资格 end-----------------*/
		
		return map;
	}

	/**
	 * 工作情况-目前任职
	 * @param id 用户id
	 * @author lift
	 * @date 2020-10-19
	 * @return
	 */
	@Override
	public UserpostDTO getStaffWorkCurrentJob(Long id){
		UserpostDTO userpostDto = userpostService.findCurrentJob(id);
		return userpostDto;
	}

	/**
	 * 工作情况-异动情况
	 * @param id 用户id
	 * @author lift
	 * @date 2020-10-19
	 * @return
	 */
	@Override
	public List<StafftransactionDTO> getStafftransaction(Long id){
		List<StafftransactionDTO> stafftransactionDTOList = stafftransactionService.findStafftransactionDto(id);
		return stafftransactionDTOList;
	}

	/**
	 * 工作情况-工作年限
	 * @param id 用户id
	 * @author lift
	 * @date 2020-10-19
	 * @return
	 */
	@Override
	public StaffWorkYearDTO getStaffWorkYear(Long id){
		StaffWorkYearDTO staffWorkYearDTO = baseMapper.getStaffWorkYear(id);
		return staffWorkYearDTO;
	}

	/**
	 * 工作情况-工作经历
	 * @param id 用户id
	 * @author lift
	 * @date 2020-10-19
	 * @return
	 */
	@Override
	public List<WorkexperienceDTO> getWorkexperienceDTO(Long id){
		List<WorkexperienceDTO> workexperienceDTOList = workexperienceService.findWorkexperienceDTO(id);
		return workexperienceDTOList;
	}
	
	/**
	 * 工作情况-实习记录
	 * @param id 用户id
	 * @author lift
	 * @date 2020-10-19
	 * @return
	 */
	@Override
	public StaffPracticeDTO getStaffPractice(Long id){
		StaffPracticeDTO staffPracticeDTO = staffPracticeService.findStaffPractice(id);
		if (staffPracticeDTO == null)
		{
			staffPracticeDTO = new StaffPracticeDTO();
			staffPracticeDTO.setStaffId(id);
		}
		return staffPracticeDTO;
	}
	
	/**
	 * 修改实习记录
	 * @param staffPracticeDTO
	 * @author lift
	 * @date 2020-10-19
	 * @return
	 */
	@Override
	public boolean updateStaffPractice(StaffPracticeDTO staffPracticeDTO){
    	UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        String userName=userInfo.getSysUser().getUsername();
        String loginCode=userInfo.getSysUser().getUsername();
        LocalDateTime now = LocalDateTime.now();
        boolean status=false;
//		StaffPractice staffPractice = new StaffPractice();
//		BeanUtils.copyProperties(staffPracticeDTO, staffPractice);
//		if(staffPracticeDTO.getId()!=null){
//			staffPractice.setModifierId(userId.toString());
//			staffPractice.setModifiedTime(now);
//			status=this.updateById(staffPractice);
//		}
//		else{
//			staffPractice.setCreatorId(userId.toString());
//			staffPractice.setCreatedTime(now);
//			staffPractice.setModifierId(userId.toString());
//			staffPractice.setModifiedTime(now);
//			
//		}
		return status;
	}

	/**
	 * 工作情况-职称及职业资格
	 * @param id 用户id
	 * @author lift
	 * @date 2020-10-19
	 * @return
	 */
	@Override
	public List<StaffProTitleDTO> getStaffProTitle(Long id){
		List<StaffProTitleDTO> staffProTitleList = staffProTitleService.findStaffProTitleDTO(id);
		return staffProTitleList;
	}

	@Override
	public boolean updateStaffWorkYear(StaffWorkYearDTO staffWorkYearDTO) {
		Staff staff = this.getById(staffWorkYearDTO.getStaffid());
		BeanUtils.copyProperties(staffWorkYearDTO, staff);
		// 处理workDate和entryTime属性内容无法拷贝问题 start
		LocalDate workDate = staffWorkYearDTO.getWorkDate();
		staff.setWorkDate(toLocalDateTime(workDate));
		
		LocalDate entryTime = staffWorkYearDTO.getEntryTime();
		staff.setEntryTime(toLocalDateTime(entryTime));
		// 处理workDate和entryTime属性内容无法拷贝问题 end
		return this.updateById(staff);
	}

	/**
	 * LocalDate转换成LocalDateTime
	 * 
	 * @author yeks
	 */
	private LocalDateTime toLocalDateTime(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		Date date;
		try {
			date = sdf.parse(localDate.toString());
			Instant instant = date.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
			return localDateTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转正管理(员工)基础信息-（根据id查询基本信息）
	 * @return
	 * @throws Exception
	 */
	@Override
	public StaffBasicPositiveVO getStaffPositiveBasic(Long id)  throws  Exception{
		//封装员工基本信息接口
		StaffBasicVO staffBasicVO = this.selectBasicById(id);
		StaffBasicPositiveVO staffBasicPositiveVO = new StaffBasicPositiveVO();
		BeanUtils.copyProperties(staffBasicVO,staffBasicPositiveVO);
		//查询任职日期
		if (ObjectUtil.isNotEmpty(staffBasicVO.getUserId())){
			StaffPositiveApprovalPage user = userService.getUserStartDate(staffBasicVO.getUserId());
			staffBasicPositiveVO.setEntryTime1(LocalDateTimeUtils.convert2Long(user.getEntryTime()));
		}else {
			staffBasicPositiveVO.setEntryTime1(staffBasicPositiveVO.getEntryTime());
		}
		return staffBasicPositiveVO;
	}





}
