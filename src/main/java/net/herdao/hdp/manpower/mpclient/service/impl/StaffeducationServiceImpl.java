package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEduUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEducationDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEduAddDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.mapper.StaffeducationMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.StaffeducationService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.ImportCheckUtils;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工教育经历
 * @author andy
 * @date 2020-09-23 17:22:28
 */
@Service
public class StaffeducationServiceImpl extends ServiceImpl<StaffeducationMapper, Staffeducation> implements StaffeducationService {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private SysDictItemService itemService;

    @Autowired
    private StaffService staffService;

    @Override
    public Boolean saveEdu(Staffeducation staffeducation) {
        SysUser sysUser = SysUserUtils.getSysUser();
        staffeducation.setCreatedTime(new Date());
        staffeducation.setCreatorCode(sysUser.getUsername());
        staffeducation.setCreatorId(sysUser.getUserId());
        staffeducation.setCreatorName(sysUser.getAliasName());
        boolean status = super.save(staffeducation);
        staffService.updateEduLast(staffeducation.getStaffId());
        return status;
    }

    @Override
    public boolean updateEdu(Staffeducation staffeducation) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        staffeducation.setModifierCode(userId.toString());
        staffeducation.setModifiedTime(new Date());
        boolean status = super.updateById(staffeducation);
        staffService.updateEduLast(staffeducation.getStaffId());
        return status;
    }

    @Override
    public IPage findStaffEducationPage(Page<StaffEducationDTO> page,StaffEducationDTO staffEducationDTO, String searchText) {
    	Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("searchText", searchText);
		map.put("groupId",staffEducationDTO.getGroupId());
		map.put("orgId",staffEducationDTO.getOrgId());
		
        page = page.setRecords(this.baseMapper.findStaffEducationPage(map));
        return page;
    }

    @Override
    public List<StaffEducationDTO> findStaffEducation(StaffEducationDTO staffEducationDTO,String searchText) {
        List<StaffEducationDTO> list = this.baseMapper.findStaffEducation(staffEducationDTO, searchText);
        return list;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        StringBuffer errMsg = new StringBuffer();
        List<ExcelCheckErrDTO> errList = new ArrayList<>();
        List<Staffeducation> staffEduList=new ArrayList<Staffeducation>();

        // 新增校验
        if (importType == 0) {
            checkAdd(excelList, errMsg, errList, staffEduList);
        }

        // 编辑校验
        if (importType == 1) {
            checkUpdate(excelList, errMsg, errList, staffEduList);
        }

        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(staffEduList);
        }
        return errList;
    }

    /**
     * 新增校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param staffEduList
     */
    private void checkAdd(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Staffeducation> staffEduList) {
        for (int i = 0; i < excelList.size(); i++) {
            Staffeducation staffEducation=new Staffeducation();
            StaffEduAddDTO addDTO = (StaffEduAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId.toString());

            //校检学位
            SysDictItem degreeItem = ImportCheckUtils.checkDicItem(errMsg, "EDUCATION_DEGREE_TYPE", addDTO.getEducationDegree(), itemService);
            if(null != degreeItem){
                addDTO.setEducationDegree(degreeItem.getValue());
            }

            //校检学历
            SysDictItem eduItem = ImportCheckUtils.checkDicItem(errMsg, "EDUCATION_QUA_TYPE", addDTO.getEducationQua(), itemService);
            if(null != eduItem){
                addDTO.setEducationQua(eduItem.getValue());
            }

            //校检学习形式
            SysDictItem studyItem = ImportCheckUtils.checkDicItem(errMsg, "XXXS", addDTO.getLearnForm(), itemService);
            if(null != studyItem){
                addDTO.setLearnForm(studyItem.getValue());
            }

            //校检时间
            String pattern= ImportCheckUtils.checkDate(errMsg, addDTO.getBeginDate(),addDTO.getEndDate());

            //检查数据库是否存在记录，且唯一记录。
            List<Staffeducation> checkList = super.list(
                    new QueryWrapper<Staffeducation>()
                            .eq("staff_id", staffId)
                            .eq("begin_date", addDTO.getBeginDate())
                            .eq("end_date", addDTO.getEndDate())
                            .eq("school_name", addDTO.getSchoolName())
            );
            if (!checkList.isEmpty()&&checkList.size()>=1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工教育表中已存在该记录，因此不可新增；");
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, staffEducation);
                staffEducation.setBeginDate(DateUtils.parseDate(addDTO.getBeginDate(),pattern));
                staffEducation.setEndDate(DateUtils.parseDate(addDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                staffEducation.setCreatedTime(new Date());
                staffEducation.setCreatorCode(sysUser.getUserId().toString());

                staffEduList.add(staffEducation);
            }
        }
    }

    /**
     * 编辑校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param staffEduList
     */
    private void checkUpdate(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Staffeducation> staffEduList) {
        for (int i = 0; i < excelList.size(); i++) {
            Staffeducation staffEducation=new Staffeducation();
            StaffEduUpdateDTO updateDTO = (StaffEduUpdateDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, updateDTO.getStaffCode(), updateDTO.getStaffName(),staffService);
            updateDTO.setStaffId(staffId.toString());

            //校检学位
            SysDictItem degreeItem = ImportCheckUtils.checkDicItem(errMsg, "EDUCATION_DEGREE_TYPE", updateDTO.getEducationDegree(), itemService);
            if(null != degreeItem){
                updateDTO.setEducationDegree(degreeItem.getValue());
            }

            //校检学历
            SysDictItem eduItem = ImportCheckUtils.checkDicItem(errMsg, "EDUCATION_QUA_TYPE", updateDTO.getEducationQua(), itemService);
            if(null != eduItem){
                updateDTO.setEducationQua(eduItem.getValue());
            }

            //校检学习形式
            SysDictItem studyItem = ImportCheckUtils.checkDicItem(errMsg, "XXXS", updateDTO.getLearnForm(), itemService);
            if(null != studyItem){
                updateDTO.setLearnForm(studyItem.getValue());
            }

            //校检时间
            String pattern= ImportCheckUtils.checkDate(errMsg, updateDTO.getBeginDate(),updateDTO.getEndDate());

            //检查数据库是否存在记录，且唯一记录。
            List<Staffeducation> checkList = super.list(
                    new QueryWrapper<Staffeducation>()
                            .eq("staff_id", staffId)
                            .eq("begin_date", updateDTO.getBeginDate())
                            .eq("end_date", updateDTO.getEndDate())
                            .eq("school_name", updateDTO.getSchoolName())
            );
            if (checkList.isEmpty()){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工教育表中不存在此记录，因此不可编辑更新；");
            }else if (!checkList.isEmpty()&&checkList.size()>1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工教育表中存在多条此记录，因此不可编辑更新；");
            }else{
                updateDTO.setId(checkList.get(0).getId());
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(updateDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(updateDTO, staffEducation);
                staffEducation.setBeginDate(DateUtils.parseDate(updateDTO.getBeginDate(),pattern));
                staffEducation.setEndDate(DateUtils.parseDate(updateDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                staffEducation.setModifiedTime(new Date());
                staffEducation.setModifierCode(sysUser.getUserId().toString());

                staffEduList.add(staffEducation);
            }
        }
    }


}
