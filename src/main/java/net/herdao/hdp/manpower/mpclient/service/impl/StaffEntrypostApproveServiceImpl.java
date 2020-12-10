
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.*;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffEntrypostApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.StaffCodePrefixVO;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 录用审批表
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@Service
public class StaffEntrypostApproveServiceImpl extends ServiceImpl<StaffEntrypostApproveMapper, StaffEntrypostApprove> implements StaffEntrypostApproveService {

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private  UserService userService;

    @Autowired
    private UserpostService userpostService;

    @Autowired
    private StaffService staffService;

    @Override
    public Page<EntryApproveDTO> findApprovePage(Page<EntryApproveDTO> page, String orgId, String searchText, String status) {
        return this.baseMapper.findApprovePage(page, orgId, searchText, status);
    }

    @Override
    public EntryApproveFormDTO findApproveDetails(Long id) {
        return this.baseMapper.findApproveDetails(id);
    }

    @Override
    public EntryApproveAddDTO saveApprove(EntryApproveAddDTO approveAddDTO) {
        StaffEntrypostApprove approve = new StaffEntrypostApprove();
        BeanUtils.copyProperties(approveAddDTO, approve);

        SysUser sysUser = SysUserUtils.getSysUser();
        approve.setCreatorTime(LocalDateTime.now());
        approve.setCreatorCode(sysUser.getUsername());
        approve.setCreatorName(sysUser.getAliasName());

        Recruitment recruitment = recruitmentService.getById(approveAddDTO.getUserId());
        if (ObjectUtil.isNotNull(recruitment)){
            approve.setMobileNo(recruitment.getMobile());
            approve.setUserName(recruitment.getTalentName());
            approve.setRecruitmentId(recruitment.getId());
        }

        //获取员工工号
        String staffCode="";
        if (ObjectUtil.isNotNull(recruitment)){
            staffCode = fetchStaffCode(recruitment.getIdnumber(),recruitment.getOrgId());
        }

        //如果员工工号，则生成员工工号。
        if (ObjectUtil.isEmpty(staffCode)) {
            staffCode = createStaffCode(approve.getOrgId());
        }
        approve.setStaffCode(staffCode);
        //状态：1 填报中，2 审批中，3 已审批
        approve.setStatus("1");
        super.save(approve);
        BeanUtils.copyProperties(approve, approveAddDTO);

        return approveAddDTO;
    }

    /**
     * 获取员工工号
     *
     * @param idNumber 身份证号码
     * @param orgId 组织ID
     * @return
     */
    private String fetchStaffCode(String idNumber,Long orgId) {
        String staffCode = "";

        //在员工表中根据身份证号码查找工号
        if (ObjectUtil.isNotEmpty(idNumber)) {
            LambdaQueryWrapper<Staff> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(Staff::getIdNumber, idNumber);
            List<Staff> list = staffService.list(queryWrapper);
            if (ObjectUtil.isNotEmpty(list)) {
                staffCode = list.get(0).getStaffCode();
                return staffCode;
            }
        }

        // 如果为空则在录用审批中查找工号
        if (ObjectUtil.isNotEmpty(staffCode)) {
            List<StaffEntrypostApprove> list = this.baseMapper.getStaffCode(staffCode);
            if (ObjectUtil.isNotEmpty(list)) {
                staffCode = list.get(0).getStaffCode();
                return staffCode;
            }
        }

        //如果为空则生成员工工号
        if (ObjectUtil.isNotEmpty(staffCode)) {
            createStaffCode(orgId);
        }

        return staffCode;
    }

    /**
     * 生成员工工号
     * @param orgId 组织ID
     */
    public String createStaffCode(Long orgId) {
        String staffCode="";
        String groupCode = "";
        LambdaQueryWrapper<Group> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Group::getOrgId, orgId);
        List<Group> list = groupService.list(queryWrapper);
        if (ObjectUtil.isNotEmpty(list)) {
            groupCode = list.get(0).getGroupCode();
        }

        //查询员工工号前缀
        if (ObjectUtil.isNotEmpty(groupCode)) {
            if (ObjectUtil.isNotNull(orgId)) {
                StaffCodePrefixVO prefixVO = this.baseMapper.getStaffCodePrefix(groupCode, orgId.toString());
                if (ObjectUtil.isNotNull(prefixVO)){
                    String staffCodeHead = prefixVO.getStaffCodeHead();
                    if (ObjectUtil.isNotEmpty(staffCodeHead)){
                        //在员工表和录用审批表中查询最大的员工工号并加1
                        StaffCodePrefixVO entity = this.baseMapper.getMaxStaffCodeAddOne(staffCodeHead);
                        if (ObjectUtil.isNotNull(entity)){
                            staffCode = entity.getStaffCode();
                        }else{
                            //在员工表和录用审批表中无对应最大的员工工号，根据员工工号前缀加0100或00100的规则生成工号
                            int size=2;
                            if(staffCodeHead.length()==size){
                                staffCode= staffCodeHead + "0100";
                            }else{
                                staffCode= staffCodeHead + "00100";
                            }
                        }
                    }
                }
            }
        }

        return staffCode;
    }

    @Override
    public Page<EntryDTO> findEntryPage(Page<EntryDTO> page, String orgId, String searchText) {
        return this.baseMapper.findEntryPage(page, orgId, searchText);
    }

    @Override
    public Page<EntryDTO> findInJobPage(Page<EntryDTO> page, String orgId, String searchText) {
        return this.baseMapper.findInJobPage(page, orgId, searchText);
    }

    @Override
    public Page<EntryDTO> findEntryInvitePage(Page<EntryDTO> page, String orgId, String searchText) {
        return this.baseMapper.findEntryInvitePage(page, orgId, searchText);
    }

    @Override
    public Page<EntryRegisterDTO> findEntryRegisterPage(Page<EntryRegisterDTO> page, String orgId, String entryCheckStatus, String searchText) {
         return this.baseMapper.findEntryRegisterPage(page, orgId, entryCheckStatus, searchText);
    }

    @Override
    public EntryPersonInfoDTO findEntryPersonInfo(String recruitmentId) {
         return this.baseMapper.findEntryPersonInfo(recruitmentId);
    }

    @Override
    public EntryJobDTO findEntryJobInfo(String recruitmentId) {
         return this.baseMapper.findEntryJobInfo(recruitmentId);
    }

    @Override
    public EntryApproveUpdateDTO findEntryJobEditInfoById(Long id) {
        return this.baseMapper.findEntryJobEditInfoById(id);
    }

    @Override
    public List<EntryApproveDTO> exportApprove(String orgId, String searchText, String status) {
        return this.baseMapper.exportApprove( orgId, searchText, status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StaffEntrypostApprove confirmEntry(Long recruitmentId, String approveId, String certificateType, String certificateNo) {
        StaffEntrypostApprove approve = super.getById(approveId);
        if (ObjectUtil.isNotNull(approve)){
            //入职登记状态 (1:未提交，2：已提交，3：已确认）
            approve.setEntryCheckStatus("3");
            //修改更新入职登记状态
            super.updateById(approve);
        }

        //修改更新人才表
        Recruitment recruitment = recruitmentService.getById(recruitmentId);
        if (ObjectUtil.isNotNull(recruitment)){
            //更新人才表
            recruitment.setId(recruitmentId);
            recruitment.setCertificateType(certificateType);
            recruitment.setCertificateNo(certificateNo);
            recruitmentService.updateById(recruitment);

            //同步人才表（mp_recruitment)到mp_user表
            User user=new User();
            BeanUtils.copyProperties(recruitment,user);
            user.setTenantId(null);
            user.setId(null);
            if (ObjectUtil.isNotNull(recruitment.getTalentName())){
                user.setUserName(recruitment.getTalentName());
            }
            //是否停用   1：是  ， 0:否
            user.setIsStop(0L);
            //登录帐号 = 员工工号
            if (ObjectUtil.isNotNull(approve.getStaffCode())){
                user.setLoginCode(approve.getStaffCode());
            }
            //登录密码 = 身份证后六位
            if (ObjectUtil.isNotNull(recruitment.getIdnumber())){
                String password = recruitment.getIdnumber().substring(recruitment.getIdnumber().length() - 6);
                user.setPassword(password);
            }
            userService.save(user);

            //同步人才表（mp_recruitment)到mp_userpost表
            Userpost userpost=new Userpost();
            BeanUtils.copyProperties(recruitment,userpost);
            userpost.setTenantId(null);
            userpost.setId(null);
            if(ObjectUtil.isNotNull(user)){
                userpost.setUserId(user.getId());
            }
            if (ObjectUtil.isNotNull(recruitment.getOrgId())){
                userpost.setOrgId(recruitment.getOrgId());
            }
            if (ObjectUtil.isNotNull(recruitment.getIntentionPostId())){
                userpost.setPostId(Long.parseLong(recruitment.getIntentionPostId()));
            }
            if (ObjectUtil.isNotNull(recruitment.getInductionTime())){
                //任职日期
                userpost.setStartDate(recruitment.getInductionTime());
            }
            //是否主岗 默认主岗
            userpost.setMainPost(true);
            //是否虚拟岗位 默认否
            userpost.setIsVirtual(false);
            userpostService.save(userpost);

            //同步人才表（mp_recruitment)到mp_staff表
            Staff staff=new Staff();
            BeanUtils.copyProperties(recruitment,staff);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            staff.setId(null);
            staff.setTenantId(null);
            staff.setStaffName(recruitment.getTalentName());
            staff.setJobType(approve.getOfficeType());
            staff.setPoliticsStatus(recruitment.getPoliticalLandscape());
           /* staff.setEntryTime(recruitment.getInductionTime());*/
            staff.setGoalPosts(recruitment.getIntentionPostId());
            staff.setPostCode(recruitment.getZipcode());
            staff.setIdType(recruitment.getCertificateType());
            staff.setProbPeriod(approve.getProbation().longValue());
            staff.setSchoolName(recruitment.getGraduated());
            staff.setEducationQua(recruitment.getHighestEducation());

            //staff.setStaffCode(approve.getStaffCode());
            //staff.setPersonnelNature(approve.getPersonnelNature());
            //staff.setPersonnelType(approve.getPersonnelType());
            //staff.setSex(recruitment.getSex());
            //staff.setBirthday(recruitment.getBirthday());
            //staff.setBirthplace(recruitment.getBirthplace());
            //staff.setNation(recruitment.getNation());
            //staff.setMaritalStatus(recruitment.getMaritalStatus());
            //staff.setFertility(recruitment.getFertility());
            //staff.setHealthStatus(recruitment.getHealthStatus());
            //staff.setHeight(recruitment.getHeight());
            //staff.setWeight(recruitment.getWeight());
            //staff.setWorkDate(recruitment.getWorkdate());
            //staff.setAccountAddress(recruitment.getAccountAddress());
            //staff.setAccountType(recruitment.getAccountType());
            //staff.setEmail(recruitment.getEmail());
            //staff.setIdNumber(recruitment.getIdnumber());
            //staff.setHomePhone(recruitment.getHomePhone());
            //staff.setMobile(recruitment.getMobile());
            //staff.setEmergencyContacts(recruitment.getEmergencyContacts());
            //staff.setBeginDate(recruitment.getBeginDate());
            //staff.setEndDate(recruitment.getEndDate());
            //staff.setLearnForm(recruitment.getLearnForm());
            //staff.setProfessional(recruitment.getProfessional());
            //staff.setCertificateType(recruitment.getCertificateType());
            //staff.setCertificateNo(recruitment.getCertificateNo());
            //staff.setCertificationTime(recruitment.getCertificateTime());
            //staff.setInterests(recruitment.getInterests());
            //staff.setSpecialty(recruitment.getSpecialty());
            //staff.setCharacteristics(recruitment.getCharacteristics());
            //staff.setAdvantage(recruitment.getAdvantage());
            //staff.setShortcoming(recruitment.getShortcoming());
            //staff.setAssignmentLocations(recruitment.getAcceptAssignmentLocation());
            //staff.setProfessionalCompetence(recruitment.getProfessionalCompetence());
            //staff.setWritingAbility(recruitment.getWritingAbility());
            staff.setProfessionalExperience(DateUtil.format(recruitment.getProfessionalExperience(), "yyyy-MM-dd"));
            staff.setManagementExperience(DateUtil.format(recruitment.getManagementExperience(), "yyyy-MM-dd"));
            staff.setRealestateExperience(DateUtil.format(recruitment.getRealEstateExperience(), "yyyy-MM-dd"));
            staff.setContractCompany(approve.getContractCompanyId().toString());
            staff.setContractTerm(approve.getContractPeriod().toString());
            staff.setUserId(user.getId());
            staff.setPaidUnit(approve.getPaidUnitsId().toString());
            staff.setSecurityUnit(approve.getSecurityUnitsId().toString());
            staff.setFundUnit(approve.getFundUnitsId().toString());
            if (recruitment.getIsAcceptAssignment()!=null){
                if (recruitment.getIsAcceptAssignment()==1){
                    staff.setAcceptedAssignment(true);
                }
                if (recruitment.getIsAcceptAssignment()==0){
                    staff.setAcceptedAssignment(false);
                }
            }

            staffService.save(staff);
        }


        return approve;
    }




}
