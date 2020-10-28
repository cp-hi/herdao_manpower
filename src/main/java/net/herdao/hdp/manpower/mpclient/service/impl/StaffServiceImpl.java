package net.herdao.hdp.manpower.mpclient.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.*;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.DtoUtils;
import net.herdao.hdp.manpower.sys.service.SysSequenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffTotalComponentVO;

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
	private UserposthistoryService userposthistoryService;

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
	public R<List<StaffOrganizationComponentVO>> selectStaffOrganizationComponent() {
		
		// 待接入用户权限 TODO
		String searchText = "";
		
		List<StaffOrganizationComponentVO> organizations = this.baseMapper.selectOrganizations(searchText);
		
		// 获取部门员工数
		Map<String, Integer> taffTotalComponentMap = this.baseMapper.getStaffTotals().stream()
				                                         .collect(Collectors.toMap(StaffTotalComponentVO::getOrgCode, StaffTotalComponentVO::getTotal));
		// 部门/组织员工数
		organizations.forEach(organization -> {
			Integer staffTotal = sumKeyLike(taffTotalComponentMap, organization.getOrgCode());
			organization.setStaffTotal(staffTotal);
		});
				
		organizations.forEach(organization -> {
			List<StaffOrganizationComponentVO> staffOrganizationComponents = organization.getStaffOrganizationComponents();
			if(ObjectUtil.isNotEmpty(staffOrganizationComponents)) {
				recursionOrganization(staffOrganizationComponents, taffTotalComponentMap);
			}
		});
		
		return R.ok(organizations);
	}
	
	/**
	 * 递归合计部门/组织人员数
	 * @param staffOrganizationComponents
	 * @param taffTotalComponentMap
	 */
	public void recursionOrganization(List<StaffOrganizationComponentVO> staffOrganizationComponents, Map<String, Integer> taffTotalComponentMap) {
		if(ObjectUtil.isNotEmpty(staffOrganizationComponents)) {
			staffOrganizationComponents.forEach(organizationChildren ->{
				// 子部门/组织员工数
				Integer staffTotal = sumKeyLike(taffTotalComponentMap, organizationChildren.getOrgCode());
				organizationChildren.setStaffTotal(staffTotal);
				recursionOrganization(organizationChildren.getStaffOrganizationComponents(), taffTotalComponentMap);
			});
		}
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
		for (Map.Entry<String, Integer> entity : dataMap.entrySet()) {
			if (entity.getKey().startsWith(keyLike)) {
				valSum += entity.getValue();
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
	public IPage staffPage(Page page, Staff staff, String searchText){
		QueryWrapper<Staff> wrapper =  Wrappers.query(staff);
		if(searchText!=null && !"".equals(searchText)){
			wrapper.like("CONCAT(STAFF_NAME,STAFF_CODE)", searchText);
		}
		IPage result = this.page(page, wrapper);
		List<Staff> list = result.getRecords();
		List<StaffListDTO> entityList = new ArrayList<>();
		StaffListDTO entity;
		for(int i=0;i<list.size();i++){
			entity = DtoUtils.transferObject(list.get(i), StaffListDTO.class);
			entityList.add(entity);
		}
		result.setRecords(entityList);
		return result;
	}

	@Override
	public StaffDetailBaseDTO staffSave(StaffDetailDTO staffForm){
		Staff staff = new Staff();
		BeanUtils.copyProperties(staffForm.getBaseObj(), staff);
		BeanUtils.copyProperties(staffForm.getJobObj(), staff);
		long code = sysSequenceService.getNext("staff_code");
		String staffCode = code + "";
		User user = new User();
		user.setLoginCode(staffCode);
		user.setUserName(staff.getStaffName());
		userService.save(user);

		staff.setStaffCode(staffCode);
		staff.setUserId(user.getId());
		this.save(staff);
		StaffDetailBaseDTO baseDTO = new StaffDetailBaseDTO();
		BeanUtils.copyProperties(staff, baseDTO);
		return baseDTO;
	}

    @Override
    public boolean staffUpdate(StaffDetailDTO staffForm){
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffForm.getBaseObj(), staff);
        BeanUtils.copyProperties(staffForm.getJobObj(), staff);
        return this.updateById(staff);
    }

	@Override
    public StaffDetailDTO getStaffById(Long id){
	    Staff staff = this.getById(id);
        StaffDetailBaseDTO base = new StaffDetailBaseDTO();
        StaffDetailJobDTO job = new StaffDetailJobDTO();
        BeanUtils.copyProperties(staff, base);
        BeanUtils.copyProperties(staff, job);
        StaffDetailDTO form = new StaffDetailDTO(base, job);
        return form;
    }

	@Override
    public Map<String, Object> getStaffDetail(Long id){
		Staff staff = this.getById(id);
		StaffBaseDTO base = baseMapper.getStaffBase(id);
		StaffInfoDTO info = new StaffInfoDTO();
		StaffJobInfoDTO jobInfo = new StaffJobInfoDTO();
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
		StaffArchiveDTO archive = new StaffArchiveDTO();
		StaffWelfareDTO welfare = new StaffWelfareDTO();
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

		List<Userposthistory> uphList = userposthistoryService.list(new QueryWrapper<Userposthistory>()
				.eq("USER_ID", staff.getUserId())
				.orderByDesc("START_DATE")
		);
		List<StaffJobTravelDTO> uphDtoList = new ArrayList<>();
		StaffJobTravelDTO jobTravel;
		for(int i=0;i<uphList.size();i++){
			jobTravel = new StaffJobTravelDTO();
			BeanUtils.copyProperties(uphList.get(i), jobTravel);
			uphDtoList.add(jobTravel);
		}
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
	public Map<String, Object> getStaffWelfare(Long id){
		Staff staff = this.getById(id);
		StaffBaseDTO base = baseMapper.getStaffBase(id);
		StaffSecurityDTO security = new StaffSecurityDTO();
		StaffFundDTO fund = new StaffFundDTO();
		StaffSalaryDTO salary = new StaffSalaryDTO();
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
	
	@Override
	public boolean updateStaffWorkYear(StaffWorkYearDTO staffWorkYearDTO){
		Staff staff = this.getById(staffWorkYearDTO.getStaffid());
		BeanUtils.copyProperties(staffWorkYearDTO, staff);
		return this.updateById(staff);
	}

}
