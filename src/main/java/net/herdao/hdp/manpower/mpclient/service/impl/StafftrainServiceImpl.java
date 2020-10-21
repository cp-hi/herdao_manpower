
package net.herdao.hdp.manpower.mpclient.service.impl;

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
import net.herdao.hdp.manpower.mpclient.dto.StaffeducationListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.mapper.StafftrainMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.RegexUtils;
import net.herdao.hdp.manpower.mpclient.vo.StafftrainVO;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Service
@AllArgsConstructor
public class StafftrainServiceImpl extends ServiceImpl<StafftrainMapper, Stafftrain> implements StafftrainService {
    @Autowired
    private StaffService staffService;

    @Override
    public Page<StafftrainDTO> findStaffTrainPage(Page<StafftrainDTO> page, String searchText) {
        Page<StafftrainDTO> list = this.baseMapper.findStaffTrainPage(page, searchText);
        return list;
    }

    @Override
    public List<StafftrainDTO> findStaffTrain(String searchText) {
        List<StafftrainDTO> list = this.baseMapper.findStaffTrain(searchText);
        return list;
    }

    @Override
    public void saveVerify(Stafftrain stafftrain) {

    }

    @Override
    public void importVerify(Stafftrain stafftrain, int type) {
        //新增校检
        if (type == 0){
            checkAdd(stafftrain);
        }

        //编辑校检
        /*if (type == 1){
            checkUpdate((FamilyStatusListDTO) familystatus);
        }*/
    }

    /**
     * 新增校检
     * @param stafftrain
     */
    private void checkAdd(Stafftrain stafftrain) {
           StafftrainVO stafftrainVO=(StafftrainVO)stafftrain;
           String errorMsg="";

            if (null == stafftrainVO.getStaffName()){
                errorMsg+="员工姓名不能为空，";
            }

            if (null == stafftrainVO.getBeginTimeStr()){
                errorMsg+="开始时间不能为空，";
            }

            if (null == stafftrainVO.getEndTimeStr()){
                errorMsg+="结束时间不能为空，";
            }

            if (null != stafftrainVO.getStaffCode()){
                Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_code", stafftrainVO.getStaffCode()));
                if (null == staff){
                    errorMsg+="系统查不到此员工工号:"+stafftrainVO.getStaffCode()+",";
                }

                if (null != staff){
                    if (staff.getStaffName()!=null && !staff.getStaffName().equals(stafftrainVO.getStaffName())){
                        errorMsg+="员工姓名不一致:"+stafftrainVO.getStaffName()+",";
                    }
                }
            }


            String pattern="";
            //校检开始时间
            boolean checkBeginTime = false;
            List<String> formatList = Arrays.asList("yyyy/m/dd","yyyy/mm/dd","yyyy/mm/dd hh:mm:ss", "yyyy-mm-dd hh:mm:ss", "yyyy.mm.dd hh:mm:ss","yyyy-mm-dd");
            for (String format : formatList) {
                checkBeginTime = DateUtils.isLegalDate(stafftrainVO.getBeginTimeStr(), format);
                if (checkBeginTime==true){
                    pattern = format;
                    break;
                }
            }
            if (checkBeginTime==false){
                errorMsg+="请填写正确的开始时间的时间格式（yyyy/mm/dd 或者 yyyy/mm/dd hh:mm:ss或者 yyyy-mm-dd  hh:mm:ss或者  yyyy.mm.dd hh:mm:ss），";
            }else{
                stafftrainVO.setBeginTime(DateUtils.parseDate(stafftrainVO.getBeginTimeStr(), pattern));
            }

            //校检结束时间
            boolean checkEndTime = false;
            for (String format : formatList) {
                checkEndTime = DateUtils.isLegalDate(stafftrainVO.getEndTimeStr(), format);
                if (checkEndTime==true){
                    break;
                }
            }
            if (checkEndTime==false){
                errorMsg+="请填写正确的结束时间的时间格式（yyyy/mm/dd 或者 yyyy/mm/dd hh:mm:ss或者 yyyy-mm-dd  hh:mm:ss或者  yyyy.mm.dd hh:mm:ss），";
            }else{
                stafftrainVO.setEndTime(DateUtils.parseDate(stafftrainVO.getEndTimeStr(), pattern));
            }

            //比较开始时间和结束时间
            boolean compareDateStatus = DateUtils.compareDate(stafftrainVO.getBeginTimeStr(), stafftrainVO.getEndTimeStr(), pattern);
            if (!compareDateStatus){
                errorMsg+="结束日期必须在开始日期之后，";
            }

            //校检培训时长
            boolean checkTrainPeriod = RegexUtils.isNumber(stafftrainVO.getTrainPeriod());
            if (!checkTrainPeriod){
                errorMsg+="培训时长（请填写正整数）:"+stafftrainVO.getTrainPeriod()+"，";
            }

            //校检培训总学时
            boolean checkTrainLearnPeriod = RegexUtils.isNumber(stafftrainVO.getTrainLearnPeriod());
            if (!checkTrainLearnPeriod){
                errorMsg+="培训总学时（请填写正整数）:"+stafftrainVO.getTrainLearnPeriod()+"，";
            }

            //校检培训总学分
            boolean checkTrainLearnScore = RegexUtils.isNumber(stafftrainVO.getTrainLearnScore());
            if (!checkTrainLearnScore){
                errorMsg+="培训总学分（请填写正整数）:"+stafftrainVO.getTrainLearnScore()+"，";
            }

            //校检培训成绩
            boolean checkScore = RegexUtils.isNumber(stafftrainVO.getScore());
            if (!checkScore){
                errorMsg+="培训成绩（请填写正整数）:"+stafftrainVO.getScore()+"，";
            }

            SysUser sysUser = SysUserUtils.getSysUser();
            stafftrainVO.setCreatedTime(new Date());
            stafftrainVO.setCreatorCode(sysUser.getUserId().toString());

            if (!errorMsg.isEmpty()){
                throw new RuntimeException(errorMsg);
            }

    }
}
