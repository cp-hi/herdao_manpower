
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.*;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.entity.StaffEntrypostApprove;
import net.herdao.hdp.manpower.mpclient.mapper.StaffEntrypostApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.StaffCodePrefixVO;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private StaffService staffService;

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private GroupService groupService;

    @Override
    public Page<EntryApproveDTO> findApprovePage(Page<EntryApproveDTO> page, String orgId, String searchText, String status) {
        Page<EntryApproveDTO> pageResult = this.baseMapper.findApprovePage(page, orgId, searchText, status);
        return pageResult;
    }

    @Override
    public EntryApproveFormDTO findApproveDetails(Long id) {
        EntryApproveFormDTO result = this.baseMapper.findApproveDetails(id);
        return result;
    }

    @Override
    public EntryApproveAddDTO saveApprove(EntryApproveAddDTO approveAddDTO) {
        StaffEntrypostApprove approve = new StaffEntrypostApprove();
        BeanUtils.copyProperties(approveAddDTO, approve);

        SysUser sysUser = SysUserUtils.getSysUser();
        approve.setCreatorTime(LocalDateTime.now());
        approve.setCreatorCode(sysUser.getUsername());
        approve.setCreatorName(sysUser.getAliasName());

        //姓名是一个下拉组件 会查询人才表 带姓名和ID过来 待完善
        //获取员工工号
        Recruitment recruitment = recruitmentService.getById(approve.getRecruitmentId());
        String staffCode="";
        staffCode = fetchStaffCode(recruitment.getIdnumber(),recruitment.getOrgId());

        //如果员工工号，则生成员工工号。
        if (ObjectUtil.isEmpty(staffCode)) {
            staffCode = createStaffCode(approve.getOrgId());
        }

        approve.setStaffCode(staffCode);
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

        return staffCode;
    }

    @Override
    public Page<EntryDTO> findEntryPage(Page<EntryDTO> page, String orgId, String searchText) {
        Page<EntryDTO> pageResult = this.baseMapper.findEntryPage(page, orgId, searchText);
        return pageResult;
    }

    @Override
    public Page<EntryDTO> findInJobPage(Page<EntryDTO> page, String orgId, String searchText) {
        Page<EntryDTO> pageResult = this.baseMapper.findInJobPage(page, orgId, searchText);
        return pageResult;
    }

    @Override
    public Page<EntryDTO> findEntryInvitePage(Page<EntryDTO> page, String orgId, String searchText) {
        Page<EntryDTO> pageResult = this.baseMapper.findEntryInvitePage(page, orgId, searchText);
        return pageResult;
    }

    @Override
    public Page<EntryRegisterDTO> findEntryRegisterPage(Page<EntryRegisterDTO> page, String orgId, String entryCheckStatus, String searchText) {
        Page<EntryRegisterDTO> pageResult = this.baseMapper.findEntryRegisterPage(page, orgId, entryCheckStatus, searchText);
        return pageResult;
    }
}
