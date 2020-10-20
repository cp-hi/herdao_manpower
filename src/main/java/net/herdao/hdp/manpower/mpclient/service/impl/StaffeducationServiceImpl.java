
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.StaffeducationListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffEducationDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.mapper.StaffeducationMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffeducationService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.vo.StaffeducationVO;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    public void saveVerify(Staffeducation staffeducation) {

    }

    @Override
    public void importVerify(Staffeducation staffeducation, int type) {
        //新增校检
        if (type == 0){
            checkAdd(staffeducation);
        }

        //编辑校检
        /*if (type == 1){
            checkUpdate((FamilyStatusListDTO) familystatus);
        }*/
    }

    /**
     * 新增校检
     * @param staffeducation
     */
    private void checkAdd(Staffeducation staffeducation) {
        try {
            StaffeducationListDTO dto=(StaffeducationListDTO)staffeducation;
            String errorMsg="";

            if (null == dto.getStaffName()){
                errorMsg+="员工姓名不能为空，";
            }

            if (null == dto.getStaffName()){
                errorMsg+="员工工号不能为空，";
            }

            if (null == dto.getSchoolName()){
                errorMsg+="毕业院校不能为空，";
            }

            if (null == dto.getBeginDateView()){
                errorMsg+="入学日期不能为空，";
            }else{
                //判断入学日期的时间格式是否正确
                boolean checkStatus = false;
                List<String> formatList = Arrays.asList("yyyy/mm/dd", "yyyy-mm-dd", "yyyy.mm.dd");
                for (String format : formatList) {
                    checkStatus = DateUtils.isLegalDate(dto.getBeginDateView(), format);
                    if (checkStatus==true){
                        break;
                    }
                }

                if (checkStatus==false){
                    errorMsg+="请填写正确的入学日期时间格式（yyyy/mm/dd 或者 yyyy-mm-dd 或者 yyyy.mm.dd），";
                }else{
                    staffeducation.setBeginDate(DateUtils.parseDate(dto.getBeginDateView(), "yyyy-mm-dd"));
                }
            }

            if (null == dto.getEndDateView()){
                errorMsg+="毕业日期不能为空，";
            }else{
                //判断入学日期的时间格式是否正确
                boolean checkStatus = false;
                List<String> formatList = Arrays.asList("yyyy/mm/dd", "yyyy-mm-dd", "yyyy.mm.dd");
                for (String format : formatList) {
                    checkStatus = DateUtils.isLegalDate(dto.getEndDateView(), format);
                    if (checkStatus==true){
                        break;
                    }
                }

                if (checkStatus==false){
                    errorMsg+="请填写正确的毕业日期时间格式（yyyy/mm/dd 或者 yyyy-mm-dd 或者 yyyy.mm.dd），";
                }else{
                    staffeducation.setEndDate(DateUtils.parseDate(dto.getEndDateView(), "yyyy-mm-dd"));
                }
            }

            SysDictItem eduDegree= itemService.getOne(
                    new QueryWrapper<SysDictItem>()
                            .eq("type", "EDUCATION_DEGREE_TYPE")
                            .eq("label", dto.getEducationDegree())
                            .eq("del_flag",0)
            );
            if (null==eduDegree){
                errorMsg+="学位不存在或已停用:"+dto.getEducationDegree();
            }

            SysDictItem eduQua= itemService.getOne(
                    new QueryWrapper<SysDictItem>()
                            .eq("type", "EDUCATION_QUA_TYPE")
                            .eq("label", dto.getEducationQua())
                            .eq("del_flag",0)
            );
            if (null==eduQua){
                errorMsg+="学历不存在或已停用:"+dto.getEducationDegree();
            }

            if (!errorMsg.isEmpty()){
                throw new RuntimeException(errorMsg);
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

}
