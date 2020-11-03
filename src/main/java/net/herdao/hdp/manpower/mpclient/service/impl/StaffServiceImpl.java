package net.herdao.hdp.manpower.mpclient.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.staff.StaffAddVM;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffArchiveDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffBaseDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffCarreraDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffContractDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffEducationLastDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffEmergencyDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFamilyDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFundDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffInfoOtherDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffJobInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffJobTravelDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffPracticeDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffProTitleDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffSalaryDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffSecurityDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffWelfareDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffWorkExpDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffWorkYearDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftransactionDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
import net.herdao.hdp.manpower.mpclient.service.StaffPracticeService;
import net.herdao.hdp.manpower.mpclient.service.StaffProTitleService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import net.herdao.hdp.manpower.mpclient.service.StaffeducationService;
import net.herdao.hdp.manpower.mpclient.service.StafftransactionService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.manpower.mpclient.service.WorkexperienceService;
import net.herdao.hdp.manpower.mpclient.vo.StaffComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffTotalComponentVO;
import net.herdao.hdp.manpower.sys.service.SysSequenceService;

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

	@Override
	@SuppressWarnings("all")
	@Transactional(rollbackFor = Exception.class)
	public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {

		// 错误数组
		List<ExcelCheckErrDTO> errList = new ArrayList<>();

		// 批量新增、编辑组织信息
		List<Staff> staffList = new ArrayList<>();

		if (importType == 0) {
			for(int i=0;i<excelList.size();i++){
				StaffAddVM entity = (StaffAddVM)excelList.get(i);
				Staff staff = new Staff();
				BeanUtils.copyProperties(entity, staff);
				staffList.add(staff);
			}
			//add
		}else {
			//update
			return errList;
		}
		// 保存新增、修改组织信息
		if(ObjectUtil.isEmpty(errList)) {
			this.saveOrUpdateBatch(staffList, 200);
		}
		return errList;
	}
    
	@SuppressWarnings("all")
	@Override
	public R<List> selectStaffOrganizationComponent(String orgCode, String batchSelectOrgCodes, String searchText) {
		
		// 组织、员工信息集合
		List stfList = new ArrayList<>();
		
		// 默认查询一级组织信息
		if(StrUtil.isAllBlank(orgCode, searchText, batchSelectOrgCodes)) {
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
			List<StaffComponentVO> staffs = this.baseMapper.selectStaffs(orgCode, (StrUtil.isBlank(batchSelectOrgCodes) ? "" : batchSelectOrgCodes).split(ManpowerContants.EN_SEPARATOR), searchText);
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
	public Map<String, Object> queryCount(){
		int total = baseMapper.selectCount(new QueryWrapper<Staff>());
		int jobType1 = baseMapper.selectCount(new QueryWrapper<Staff>()
				.eq("JOB_TYPE", "1")
		);
		int jobType2 = baseMapper.selectCount(new QueryWrapper<Staff>()
				.eq("JOB_TYPE", "2")
		);
		int jobType3 = baseMapper.selectCount(new QueryWrapper<Staff>()
				.eq("JOB_TYPE", "3")
		);
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
		page = page.setRecords(baseMapper.staffPage(map));
		return page;
	}

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

		List<Familystatus> familyList = familystatusService.list(new QueryWrapper<Familystatus>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("MODIFIED_TIME")
		);
		List<StaffFamilyDTO> familyDtoList = new ArrayList<>();
		StaffFamilyDTO familyDto;
		for(int i=0;i<familyList.size();i++){
			familyDto = new StaffFamilyDTO();
			BeanUtils.copyProperties(familyList.get(i), familyDto);
			familyDtoList.add(familyDto);
		}
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
			expDtoList.add(workExp);
		}
		map.put("staffWorkExpDTO", expDtoList);

		List<Familystatus> familyList = familystatusService.list(new QueryWrapper<Familystatus>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("MODIFIED_TIME")
		);
		List<StaffFamilyDTO> familyDtoList = new ArrayList<>();
		StaffFamilyDTO family;
		for(int i=0;i<familyList.size();i++){
			family = new StaffFamilyDTO();
			BeanUtils.copyProperties(familyList.get(i), family);
			familyDtoList.add(family);
		}
		map.put("staffFamilyDTO", familyDtoList);

		List<Staffeducation> eduList = staffeducationService.list(new QueryWrapper<Staffeducation>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("END_DATE")
				.last("limit 1")
		);
		StaffEducationLastDTO educationLast = baseMapper.getStaffEducationLast(id);
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
	public boolean updateStaffWorkYear(StaffWorkYearDTO staffWorkYearDTO){
		Staff staff = this.getById(staffWorkYearDTO.getStaffid());
		BeanUtils.copyProperties(staffWorkYearDTO, staff);
		return this.updateById(staff);
	}

}
