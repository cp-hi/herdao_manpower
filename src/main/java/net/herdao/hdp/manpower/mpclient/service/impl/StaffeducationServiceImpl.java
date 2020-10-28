package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 员工教育经历
 * @author andy
 * @date 2020-09-23 17:22:28
 */
@Service
@AllArgsConstructor
public class StaffeducationServiceImpl extends ServiceImpl<StaffeducationMapper, Staffeducation> implements StaffeducationService {

    private RemoteUserService remoteUserService;

    private SysDictItemService itemService;

    private StaffService staffService;

    @Override
    public Boolean saveEdu(Staffeducation staffeducation) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        staffeducation.setCreatorCode(userId.toString());
        staffeducation.setCreatedTime(new Date());
        boolean status = super.save(staffeducation);
        return status;
    }

    @Override
    public boolean updateEdu(Staffeducation staffeducation) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        staffeducation.setModifierCode(userId.toString());
        staffeducation.setModifiedTime(new Date());
        boolean status = super.updateById(staffeducation);
        return status;
    }

    @Override
    public Page<StaffEducationDTO> findStaffEducationPage(Page<StaffEducationDTO> page, String searchText, String staffId) {
        Page<StaffEducationDTO> pageResult = this.baseMapper.findStaffEducationPage(page, searchText, staffId);
        return pageResult;
    }

    @Override
    public List<StaffEducationDTO> findStaffEducation(String searchText, String staffId) {
        List<StaffEducationDTO> list = this.baseMapper.findStaffEducation(searchText, staffId);
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
                ImportCheckUtils.appendStringBuffer(errMsg, "员工教育表中存在多条此记录，因此不可新增；");
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
            if (checkList.isEmpty()){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工教育表中不存在此记录，因此不可编辑更新；");
            }else if (!checkList.isEmpty()&&checkList.size()>1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工教育表中存在多条此记录，因此不可编辑更新；");
            }else{
                addDTO.setId(checkList.get(0).getId());
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, staffEducation);
                staffEducation.setBeginDate(DateUtils.parseDate(addDTO.getBeginDate(),pattern));
                staffEducation.setEndDate(DateUtils.parseDate(addDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                staffEducation.setModifiedTime(new Date());
                staffEducation.setModifierCode(sysUser.getUserId().toString());

                staffEduList.add(staffEducation);
            }
        }
    }

    @Override
    public String getAddRemarks() {
        StringBuffer remarks = new StringBuffer();
        remarks.append("导入说明：\r\n")
                .append("1、标红字段为必填\r\n")
                .append("2、操作导入前请删除示例数据\r\n")
                .append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
                .append("4、奖惩时间的日期格式为：yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。\r\n")
                .append("5、奖惩类别：输入系统中已存在的类型，如：年度优秀员工奖。\r\n")
                .append("6、奖惩金额：正整数。\r\n")
                .append("7、奖惩/惩罚：奖惩 或 惩罚");
        return remarks.toString();
    }

    @Override
    public String getUpdateRemarks() {
        StringBuffer remarks = new StringBuffer();
        remarks.append("导入说明：\r\n")
                .append("1、标红字段为必填\r\n")
                .append("2、操作导入前请删除示例数据\r\n")
                .append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
                .append("4、奖惩时间的日期格式为：yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。\r\n")
                .append("5、奖惩类别：输入系统中已存在的类型，如：年度优秀员工奖。\r\n")
                .append("6、奖惩金额：正整数。\r\n")
                .append("7、奖惩/惩罚：奖惩 或 惩罚")
                .append("8、员工姓名+员工工号+奖励/惩罚+奖惩时间+奖惩类别:数据唯一标识，不允许重复导入记录。");
        return remarks.toString();
    }
}
