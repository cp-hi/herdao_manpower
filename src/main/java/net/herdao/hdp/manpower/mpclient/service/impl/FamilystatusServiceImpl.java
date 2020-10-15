
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.dto.familyStatus.FamilyStatusListDto;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.FamilystatusMapper;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.utils.RegexUtils;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工家庭成员
 *
 * @author andy
 * @date 2020-09-23 10:53:08
 */
@Service
@AllArgsConstructor
public class FamilystatusServiceImpl extends ServiceImpl<FamilystatusMapper, Familystatus> implements FamilystatusService {
    private StaffService staffService;

    private SysDictItemService itemService;

    @Override
    public Page<Familystatus> findFamilyStatusPage(Page<Familystatus> page, String orgId, String staffName, String staffCode) {
        Page<Familystatus> pageResult = this.baseMapper.findFamilyStatusPage(page, orgId,staffName,staffCode);
        return pageResult;
    }

    @Override
    public List<FamilyStatusVO> findFamilyStatus(String orgId, String staffName, String staffCode) {
        List<FamilyStatusVO> list= this.baseMapper.findFamilyStatus(orgId, staffName, staffCode);
        return list;
    }


    @Override
    public void saveVerify(Familystatus familystatus) {

    }

    @Override
    public void importVerify(Familystatus familystatus, int type) {
        //新增校检
        if (type == 0){
            checkAdd((FamilyStatusListDto) familystatus);
        }

        //编辑校检
        if (type == 1){
            checkUpdate((FamilyStatusListDto) familystatus);
        }
    }

    /**
     * 新增校检
     * @param dto
     */
    private void checkAdd(FamilyStatusListDto dto) {
        String errorMsg="";

        if (null == dto.getStaffName()){
            errorMsg+="员工姓名不能为空，";
        }

        if (null == dto.getStaffName()){
            errorMsg+="员工工号不能为空，";
        }

        if (null == dto.getName()){
            errorMsg+="家庭成员姓名不能为空，";
        }

        if (null == dto.getRelations()){
            errorMsg+="关系不能为空，";
        }

        if (null != dto.getStaffCode()){
            Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_code", dto.getStaffCode()));
            if (null == staff){
                errorMsg+="系统查不到此员工工号:"+dto.getStaffCode()+",";
            }

            if (null != staff){
                if (staff.getStaffName()!=null && !staff.getStaffName().equals(dto.getStaffName())){
                    errorMsg+="员工姓名不一致:"+dto.getStaffName()+",";
                }
            }
        }

        if (null != dto.getAge()){
            boolean isNumber = RegexUtils.isNumber(dto.getAge().toString());
            if (!isNumber){
                errorMsg+="员工年龄不是正整数:"+dto.getAge()+",";
            }
        }

        SysDictItem dictItem = itemService.getOne(
             new QueryWrapper<SysDictItem>()
                     .eq("type", "RELATIONS_TYPE")
                     .eq("label", dto.getRelations())
                     .eq("del_flag", 0)
        );

        if (null==dictItem){
            errorMsg+="关系不存在或已停用:"+dto.getRelations();
        }

        if (!errorMsg.isEmpty()){
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * 编辑校检
     * @param familystatus
     */
    private void checkUpdate(FamilyStatusListDto familystatus) {
        FamilyStatusListDto dto = familystatus;
        String errorMsg="";

        if (null == dto.getStaffName()){
            errorMsg+="员工姓名不能为空，";
        }

        if (null == dto.getStaffName()){
            errorMsg+="员工工号不能为空，";
        }

        if (null == dto.getName()){
            errorMsg+="家庭成员姓名不能为空，";
        }

        if (null != dto.getStaffCode()){
            Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_code", dto.getStaffCode()));
            if (null == staff){
                errorMsg+="系统查不到此员工工号:"+dto.getStaffCode()+",";
            }
            if (null != staff){
                List<Familystatus> list = super.list(new QueryWrapper<Familystatus>().eq("staff_id", staff.getId()));
                if (null==list || list.isEmpty()){
                    errorMsg+="家庭成员表中不存在此员工工号:"+dto.getStaffCode()+",";
                }

                if (null !=  dto.getName()){
                    Familystatus result = super.getOne(new QueryWrapper<Familystatus>().eq("staff_Id", staff.getId()).eq("name", dto.getName()));
                    if (null == result){
                        errorMsg+="家庭成员信息匹配不到，请重新填写系统已存在的家庭成员:"+dto.getStaffCode()+",";
                    }else {
                        dto.setId(result.getId());
                    }

                }
            }

            if (null != staff){
                if (staff.getStaffName()!=null && !staff.getStaffName().equals(dto.getStaffName())){
                    errorMsg+="员工姓名不一致:"+dto.getStaffName()+",";
                }
            }
        }

        if (null != dto.getAge()){
            boolean isNumber = RegexUtils.isNumber(dto.getAge().toString());
            if (!isNumber){
                errorMsg+="员工年龄不是正整数:"+dto.getAge()+",";
            }
        }

        SysDictItem dictItem = itemService.getOne(
                new QueryWrapper<SysDictItem>()
                        .eq("type", "RELATIONS_TYPE")
                        .eq("label", dto.getRelations())
                        .eq("del_flag",0)
        );

        if (null==dictItem){
            errorMsg+="关系不存在或已停用:"+dto.getRelations();
        }

        if (!errorMsg.isEmpty()){
            throw new RuntimeException(errorMsg);
        }
    }
}
