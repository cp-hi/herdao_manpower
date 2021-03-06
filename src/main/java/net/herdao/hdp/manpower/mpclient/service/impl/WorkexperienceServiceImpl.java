
package net.herdao.hdp.manpower.mpclient.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.StaffWorkAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.StaffWorkUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;
import net.herdao.hdp.manpower.mpclient.mapper.WorkexperienceMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.WorkexperienceService;
import net.herdao.hdp.manpower.mpclient.utils.ImportCheckUtils;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;

/**
 * 员工工作经历
 *
 * @author andy
 * @date 2020-09-24 10:24:09
 */
@Service
public class WorkexperienceServiceImpl extends ServiceImpl<WorkexperienceMapper, Workexperience> implements WorkexperienceService {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private StaffService staffService;

    @Override
    public IPage findStaffWorkPage(Page<WorkexperienceDTO> page,WorkexperienceDTO workexperienceDTO, String searchText ){
    	Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("searchText", searchText);
		map.put("groupId",workexperienceDTO.getGroupId());
		map.put("orgId",workexperienceDTO.getOrgId());
		List<Workexperience> workexperienceList = this.baseMapper.findStaffWorkPage(map);
		List<WorkexperienceDTO> workexperienceDTOList = new ArrayList<WorkexperienceDTO>();
    	workexperienceList.forEach(wk ->{
    		WorkexperienceDTO wp = new WorkexperienceDTO();
    		BeanUtils.copyProperties(wk, wp);
    		wp.setBeginDate(LocalDateTimeUtils.convert2Long(wk.getBeginDate()));
    		wp.setEndDate(LocalDateTimeUtils.convert2Long(wk.getEndDate()));
    		workexperienceDTOList.add(wp);
    	});
        page = page.setRecords(workexperienceDTOList);
        return page;
    }

    @Override
    public List<WorkexperienceDTO> findStaffWork(String searchText,String staffId) {
    	List<Workexperience> workexperienceList = this.baseMapper.findStaffWork(searchText,staffId);
    	List<WorkexperienceDTO> workexperienceDTOList = new ArrayList<WorkexperienceDTO>();
    	workexperienceList.forEach(wk ->{
    		WorkexperienceDTO wp = new WorkexperienceDTO();
    		BeanUtils.copyProperties(wk, wp);
    		wp.setBeginDate(LocalDateTimeUtils.convert2Long(wk.getBeginDate()));
    		wp.setEndDate(LocalDateTimeUtils.convert2Long(wk.getEndDate()));
    		workexperienceDTOList.add(wp);
    	});
        return workexperienceDTOList;
    }

    @Override
    public boolean saveWorkDTO(WorkexperienceDTO workexperienceDTO){
    	UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        Long userId = userInfo.getSysUser().getUserId();
        Workexperience workexperience = new Workexperience();
        BeanUtils.copyProperties(workexperienceDTO, workexperience);
        //创建人工号、姓名、时间；修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        workexperience.setCreatorCode(loginCode); 
        workexperience.setCreatorName(userName);
        workexperience.setCreatedTime(now);
        workexperience.setCreatorId(userId);
        workexperience.setModifierCode(loginCode);
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        workexperience.setModifierId(userId);

        boolean flag = super.save(workexperience);
        return flag;
    }

    @Override
    public boolean updateWorkDTO(WorkexperienceDTO workexperienceDTO) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        Long userId = userInfo.getSysUser().getUserId();

        Workexperience workexperience = this.getById(workexperienceDTO.getId());
        BeanUtils.copyProperties(workexperienceDTO, workexperience);
        //修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        workexperience.setModifierCode(loginCode);
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        workexperience.setModifierId(userId);
        boolean status = super.updateById(workexperience);
        return status;
    }
    
    @Override
    public List<WorkexperienceDTO> findWorkexperienceDTO(Long staffid){
    	List<Workexperience> workexperienceList = this.baseMapper.findWorkexperience(staffid);
    	List<WorkexperienceDTO> workexperienceDTOList = new ArrayList<WorkexperienceDTO>();
    	workexperienceList.forEach(wk ->{
    		WorkexperienceDTO workexperienceDTO = new WorkexperienceDTO();
    		BeanUtils.copyProperties(wk, workexperienceDTO);
    		workexperienceDTO.setBeginDate(LocalDateTimeUtils.convert2Long(wk.getBeginDate()));
    		workexperienceDTO.setEndDate(LocalDateTimeUtils.convert2Long(wk.getEndDate()));
    		workexperienceDTOList.add(workexperienceDTO);
    	});
    	return workexperienceDTOList;
    }

    @Override
    public boolean saveWork(WorkexperienceDTO workexperienceDTO) {
    	Workexperience workexperience = new Workexperience();
    	BeanUtils.copyProperties(workexperienceDTO, workexperience);
    	workexperience.setBeginDate(LocalDateTimeUtils.convert2LocalDateTime(workexperienceDTO.getBeginDate()));
        workexperience.setEndDate(LocalDateTimeUtils.convert2LocalDateTime(workexperienceDTO.getEndDate()));
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        Long userId = userInfo.getSysUser().getUserId();
        LocalDateTime now = LocalDateTime.now();
        workexperience.setCreatorCode(loginCode);
        workexperience.setCreatorName(userName);
        workexperience.setCreatedTime(now);
        workexperience.setCreatorId(userId);
        boolean flag = super.save(workexperience);
        return flag;
     }

    @Override
    public boolean updateWork(WorkexperienceDTO workexperienceDTO) {
    	Workexperience workexperience = new Workexperience();
    	BeanUtils.copyProperties(workexperienceDTO, workexperience);
    	workexperience.setBeginDate(LocalDateTimeUtils.convert2LocalDateTime(workexperienceDTO.getBeginDate()));
        workexperience.setEndDate(LocalDateTimeUtils.convert2LocalDateTime(workexperienceDTO.getEndDate()));
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        Long userId = userInfo.getSysUser().getUserId();

        //修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        workexperience.setModifierCode(loginCode);
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        workexperience.setModifierId(userId);
        boolean status = super.updateById(workexperience);
        return status;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        StringBuffer errMsg = new StringBuffer();
        List<ExcelCheckErrDTO> errList = new ArrayList<>();
        List<Workexperience> workexperienceList=new ArrayList<Workexperience>();

        // 新增校验
        if (importType == 0) {
            checkAdd(excelList, errMsg, errList, workexperienceList);
        }

        // 编辑校验
        if (importType == 1) {
            checkUpdate(excelList, errMsg, errList, workexperienceList);
        }

        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(workexperienceList);
        }
        return errList;
    }

    /**
     * 新增校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param workexperienceList
     */
    private void checkAdd(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Workexperience> workexperienceList) {
        for (int i = 0; i < excelList.size(); i++) {
            Workexperience workexperience=new Workexperience();
            StaffWorkAddDTO addDTO = (StaffWorkAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId.toString());

            //校检时间
            String pattern= ImportCheckUtils.checkDate(errMsg, addDTO.getBeginDate(),addDTO.getEndDate());

            //检查数据库是否存在记录，且唯一记录。
            List<Workexperience> checkList = super.list(
                    new QueryWrapper<Workexperience>()
                            .eq("staff_id", staffId)
                            .eq("begin_date", addDTO.getBeginDate())
                            .eq("end_date", addDTO.getEndDate())
                            .eq("company_name", addDTO.getCompanyName())
            );
            if (!checkList.isEmpty()&&checkList.size()>=1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工工作经历表中存在此记录，因此不可新增；");
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, workexperience);
                workexperience.setStaffId(Long.parseLong(addDTO.getStaffId()));
                workexperience.setSubordinates(Integer.parseInt(addDTO.getSubordinates()));
                
                workexperience.setBeginDate(LocalDateTimeUtils.convertStr2DateTime(addDTO.getBeginDate(),pattern));
                workexperience.setEndDate(LocalDateTimeUtils.convertStr2DateTime(addDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                workexperience.setCreatedTime(LocalDateTime.now());
                workexperience.setCreatorCode(sysUser.getUsername());
                workexperience.setCreatorName(sysUser.getAliasName());
                workexperience.setCreatorId(sysUser.getUserId());

                workexperienceList.add(workexperience);
            }
        }
    }

    /**
     * 编辑校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param workexperienceList
     */
    private void checkUpdate(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Workexperience> workexperienceList) {
        for (int i = 0; i < excelList.size(); i++) {
            Workexperience workexperience=new Workexperience();
            StaffWorkUpdateDTO addDTO = (StaffWorkUpdateDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId.toString());

            //校检时间
            String pattern= ImportCheckUtils.checkDate(errMsg, addDTO.getBeginDate(),addDTO.getEndDate());

            //检查数据库是否存在记录，且唯一记录。
            List<Workexperience> checkList = super.list(
                    new QueryWrapper<Workexperience>()
                            .eq("staff_id", staffId)
                            .eq("begin_date", addDTO.getBeginDate())
                            .eq("end_date", addDTO.getEndDate())
                            .eq("company_name", addDTO.getCompanyName())
            );
            if (checkList.isEmpty()){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工工作经历表中不存在此记录，因此不可编辑更新；");
            }else if (!checkList.isEmpty()&&checkList.size()>1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工工作经历表中存在多条此记录，因此不可编辑更新；");
            }else{
                addDTO.setId(checkList.get(0).getId());
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, workexperience);
                workexperience.setStaffId(Long.parseLong(addDTO.getStaffId()));
                workexperience.setSubordinates(Integer.parseInt(addDTO.getSubordinates()));

                SysUser sysUser = SysUserUtils.getSysUser();
                workexperience.setModifiedTime(LocalDateTime.now());
                workexperience.setModifierCode(sysUser.getUsername());
                workexperience.setModifierName(sysUser.getAliasName());
                workexperience.setModifierId(sysUser.getUserId());

                workexperienceList.add(workexperience);
            }
        }
    }
}
