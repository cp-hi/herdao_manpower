package net.herdao.hdp.manpower.mpclient.service.impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import net.herdao.hdp.common.core.util.R;
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
	public boolean staffSave(StaffDetailDTO staffForm){
		Staff staff = new Staff();
		BeanUtils.copyProperties(staffForm.getBaseObj(), staff);
		BeanUtils.copyProperties(staffForm.getJobObj(), staff);
		return this.save(staff);
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
		StaffInfoDTO info = new StaffInfoDTO();
		StaffJobInfoDTO jobInfo = new StaffJobInfoDTO();
		StaffInfoOtherDTO infoOther = new StaffInfoOtherDTO();
		StaffEmergencyDTO emergency = new StaffEmergencyDTO();
		StaffEducationLastDTO educationLast = new StaffEducationLastDTO();
		BeanUtils.copyProperties(staff, info);
		BeanUtils.copyProperties(staff, jobInfo);
		BeanUtils.copyProperties(staff, infoOther);
		BeanUtils.copyProperties(staff, emergency);
		BeanUtils.copyProperties(staff, educationLast);

		Map<String, Object> map = new HashMap<>();
		map.put("info", info);
		map.put("jobInfo", jobInfo);
		map.put("infoOther", infoOther);
		map.put("emergency", emergency);
		map.put("educationLast", educationLast);

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
		map.put("familyList", familyDtoList);

		List<Staffeducation> eduList = staffeducationService.list(new QueryWrapper<Staffeducation>()
				.eq("STAFF_ID", staff.getId())
				.orderByDesc("END_DATE")
		);
		List<StaffEducationDTO> eduDtoList = new ArrayList<>();
		StaffEducationDTO eduDto;
		for(int i=0;i<familyList.size();i++){
			eduDto = new StaffEducationDTO();
			BeanUtils.copyProperties(eduList.get(i), eduDto);
			eduDtoList.add(eduDto);
		}
		map.put("eduList", eduDtoList);
		return map;
	}

	@Override
	public Map<String, Object> getHomePage(Long id){
		Staff staff = this.getById(id);
		StaffArchiveDTO archive = new StaffArchiveDTO();
		StaffEducationLastDTO educationLast = new StaffEducationLastDTO();
		StaffWelfareDTO welfare = new StaffWelfareDTO();
		StaffCarreraDTO carrera = new StaffCarreraDTO();
		BeanUtils.copyProperties(staff, archive);
		BeanUtils.copyProperties(staff, educationLast);
		BeanUtils.copyProperties(staff, welfare);
		BeanUtils.copyProperties(staff, carrera);
		Map<String, Object> map = new HashMap<>();
		map.put("archive", archive);
		map.put("educationLast", educationLast);
		map.put("welfare", welfare);
		map.put("carrera", carrera);

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
		map.put("uphDtoList", uphDtoList);

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
		map.put("expDtoList", expDtoList);

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
		map.put("familyDtoList", familyDtoList);
		return map;
	}
	

	/**
	 * 工作情况
	 * 
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
		
		//更多任职
		
		/*-------------异动情况 star-----------------*/
		List<Stafftransaction> stafftransactionList = stafftransactionService.list(new QueryWrapper<Stafftransaction>()
				.eq("STAFF_ID", id)
				.orderByDesc("TRAN_TIME")
		);
		map.put("stafftransactionList", stafftransactionList);
		/*-------------异动情况 end-----------------*/
		
		
		/*-------------工作年限 star-----------------*/
		StaffWorkYearDTO staffWorkYearDTO = baseMapper.getStaffWorkYear(id);
		map.put("staffWorkYearDTO", staffWorkYearDTO);
		/*-------------工作年限 end-----------------*/
		
		/*-------------工作经历 star-----------------*/
		List<Workexperience> workexperienceList = workexperienceService.list(new QueryWrapper<Workexperience>()
				.eq("STAFF_ID", id)
				.orderByDesc("BEGIN_DATE")
		);
		map.put("workexperienceList", workexperienceList);
		/*-------------工作经历 end-----------------*/
		
		/*-------------实习记录 star-----------------*/
		List<StaffPractice> staffPracticeList = staffPracticeService.list(new QueryWrapper<StaffPractice>()
				.eq("STAFF_ID", id)
				.orderByDesc("CREATED_TIME")
		);
		StaffPractice staffPractice;
		if (staffPracticeList.size()>0)
		{
			staffPractice = staffPracticeList.get(0);
		}
		else{
			staffPractice = new StaffPractice();
		}
		map.put("staffPractice", staffPractice);
		/*-------------实习记录 end-----------------*/
		
		/*-------------职称及职业资格 star-----------------*/
		List<StaffProTitle> staffProTitleList = staffProTitleService.list(new QueryWrapper<StaffProTitle>()
				.eq("STAFF_ID", id)
				.orderByDesc("certificate_time")
		);
		map.put("staffProTitleList", staffProTitleList);
		/*-------------职称及职业资格 end-----------------*/
		
		return map;
	}

	@Override
	public Map<String, Object> getStaffWelfare(Long id){
		Staff staff = this.getById(id);
		StaffSecurityDTO security = new StaffSecurityDTO();
		StaffFundDTO fund = new StaffFundDTO();
		StaffSalaryDTO salary = new StaffSalaryDTO();
		BeanUtils.copyProperties(staff, security);
		BeanUtils.copyProperties(staff, fund);
		BeanUtils.copyProperties(staff, salary);
		Map<String, Object> map = new HashMap<>();
		map.put("security", security);
		map.put("fund", fund);
		map.put("salary", salary);

		List<Staffcontract> contractList = staffcontractService.list(new QueryWrapper<Staffcontract>()
				.eq("SATFF_ID", staff.getId())
				.orderByDesc("START_DATE")
		);
		List<StaffcontractDTO> contractDtoList = new ArrayList<>();
		StaffcontractDTO contractDto;
		for(int i=0;i<contractList.size();i++){
			contractDto = new StaffcontractDTO();
			BeanUtils.copyProperties(contractList.get(i), contractDto);
			contractDtoList.add(contractDto);
		}
		map.put("contractDtoList", contractDtoList);
		return  map;
	}
}
