
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.StaffWorkAddDTO;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;
import net.herdao.hdp.manpower.mpclient.mapper.WorkexperienceMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.WorkexperienceService;

import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.ImportCheckUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public Page<WorkexperienceDTO> findStaffWorkPage(Page<WorkexperienceDTO> page, String searchText, String staffId) {
        Page<WorkexperienceDTO> pageResult = this.baseMapper.findStaffWorkPage(page, searchText,staffId);
        return pageResult;
    }

    @Override
    public List<WorkexperienceDTO> findStaffWork(String searchText,String staffId) {
        List<WorkexperienceDTO> list = this.baseMapper.findStaffWork(searchText,staffId);
        return list;
    }

    @Override
    public boolean saveWorkDTO(WorkexperienceDTO workexperienceDTO){
    	UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        Workexperience workexperience = new Workexperience();
        BeanUtils.copyProperties(workexperienceDTO, workexperience);
        //创建人工号、姓名、时间；修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        workexperience.setCreatorCode(loginCode); 
        workexperience.setCreatorName(userName);
        workexperience.setCreatedTime(now);
        workexperience.setModifierCode(loginCode); 
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        boolean flag = super.save(workexperience);
        return flag;
    }

    @Override
    public boolean updateWorkDTO(WorkexperienceDTO workexperienceDTO) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        
        Workexperience workexperience = this.getById(workexperienceDTO.getId());
        BeanUtils.copyProperties(workexperienceDTO, workexperience);
        //修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        workexperience.setModifierCode(loginCode);
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        boolean status = super.updateById(workexperience);
        return status;
    }
    
    @Override
    public List<WorkexperienceDTO> findWorkexperienceDTO(Long staffid){
    	List<WorkexperienceDTO> workexperienceDTOList = this.baseMapper.findWorkexperienceDTO(staffid);
    	return workexperienceDTOList;
    }

    @Override
    public boolean saveWork(Workexperience workexperience) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        LocalDateTime now = LocalDateTime.now();
        workexperience.setCreatorCode(loginCode);
        workexperience.setCreatorName(userName);
        workexperience.setCreatedTime(now);
        boolean flag = super.save(workexperience);
        return flag;
     }

    @Override
    public boolean updateWork(Workexperience orkexperience) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();

        //修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        orkexperience.setModifierCode(loginCode);
        orkexperience.setModifierName(userName);
        orkexperience.setModifiedTime(now);
        boolean status = super.updateById(orkexperience);
        return status;
    }

    @Override
    public String getAddRemarks() {
        StringBuffer remarks = new StringBuffer();
        remarks.append("导入说明：\r\n")
                .append("1、标红字段为必填\r\n")
                .append("2、操作导入前请删除示例数据\r\n")
                .append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
                .append("4、下属人数：正整数。\r\n")
                .append("5、开始日期、结束日期：格式为yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。结束时间必须在开始时间之后。");
        return remarks.toString();
    }

    @Override
    public String getUpdateRemarks() {
        StringBuffer remarks = new StringBuffer();
        remarks.append("导入说明：\r\n")
                .append("1、标红字段为必填\r\n")
                .append("2、操作导入前请删除示例数据\r\n")
                .append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
                .append("4、下属人数：正整数。\r\n")
                .append("5、开始日期、结束日期：格式为yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。结束时间必须在开始时间之后。\r\n")
                .append("6、员工姓名+员工工号+开始时间+结束时间+单位名称:数据唯一标识，不允许重复导入记录。\r\n");
        return remarks.toString();
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
                ImportCheckUtils.appendStringBuffer(errMsg, "员工工作经历表中存在多条此记录，因此不可新增；");
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, workexperience);
                workexperience.setStaffId(Long.parseLong(addDTO.getStaffId()));
                workexperience.setSubordinates(Integer.parseInt(addDTO.getSubordinates()));
                workexperience.setBeginDate(DateUtils.parseDate(addDTO.getBeginDate(),pattern));
                workexperience.setEndDate(DateUtils.parseDate(addDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                workexperience.setCreatedTime(LocalDateTime.now());
                workexperience.setCreatorCode(sysUser.getUserId().toString());

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
                workexperience.setBeginDate(DateUtils.parseDate(addDTO.getBeginDate(),pattern));
                workexperience.setEndDate(DateUtils.parseDate(addDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                workexperience.setCreatedTime(LocalDateTime.now());
                workexperience.setCreatorCode(sysUser.getUserId().toString());

                workexperienceList.add(workexperience);
            }
        }
    }
}
