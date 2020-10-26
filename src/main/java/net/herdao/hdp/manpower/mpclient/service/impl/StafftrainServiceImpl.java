
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainAddDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.mapper.StafftrainMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.RegexUtils;
import net.herdao.hdp.manpower.mpclient.vo.StafftrainErrMsg;
import net.herdao.hdp.manpower.sys.service.impl.SysDictItemServiceImpl;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private StaffService staffService;

    private SysDictItemServiceImpl itemService;

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
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        StringBuffer errMsg = new StringBuffer();
        // 错误数组
        List<ExcelCheckErrDTO> errList = new ArrayList<>();

        List<Stafftrain> stafftrainList=new ArrayList<Stafftrain>();

        // 新增校验
        if (importType == 0) {
            checkAdd(excelList, errMsg, errList, stafftrainList);
        }

        // 编辑校验
        if (importType == 1) {
            checkUpdate(excelList, errMsg, errList, stafftrainList);
        }

        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(stafftrainList);
        }
        return errList;
    }

    /**
     * 新增校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param stafftrainList
     */
    private void checkAdd(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Stafftrain> stafftrainList) {
        for (int i = 0; i < excelList.size(); i++) {
            Stafftrain stafftrain=new Stafftrain();

            StaffTrainAddDTO addDTO = checkStaffForAdd(excelList, errMsg, i);

            checkDicItem(errMsg, addDTO);

            String pattern= checkTime(errMsg, addDTO);

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, stafftrain);
                stafftrain.setBeginTime(DateUtils.parseDate(addDTO.getBeginTime(),pattern));
                stafftrain.setEndTime(DateUtils.parseDate(addDTO.getEndTime(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                stafftrain.setCreatedTime(new Date());
                stafftrain.setCreatorCode(sysUser.getUserId().toString());

                stafftrainList.add(stafftrain);
            }
        }
    }

    /**
     * 校检字典
     * @param errMsg
     * @param addDTO
     */
    private void checkDicItem(StringBuffer errMsg, StaffTrainAddDTO addDTO) {
        SysDictItem trainTypeDictItem = itemService.getOne(
                new QueryWrapper<SysDictItem>()
                        .eq("type", "TRAIN_TYPE")
                        .eq("label", addDTO.getTrainType())
                        .eq("del_flag", 0)
        );
        if (null == trainTypeDictItem) {
            appendStringBuffer(errMsg, "培训类型不存在或已停用：" + addDTO.getTrainType());
        }
    }

    /**
     * 新增方法的 员工校检
     * @param excelList
     * @param errMsg
     * @param i
     * @return
     */
    private StaffTrainAddDTO checkStaffForAdd(List excelList, StringBuffer errMsg, int i) {
        StaffTrainAddDTO addDTO = (StaffTrainAddDTO) excelList.get(i);
        if (null != addDTO.getStaffCode()){
            Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_code", addDTO.getStaffCode()));
            if (null == staff){
                appendStringBuffer(errMsg, "系统查不到此员工工号：" + addDTO.getStaffCode() + "不存在");
            }else{
                if (!staff.getStaffName().equals(addDTO.getStaffName())){
                    appendStringBuffer(errMsg, "员工姓名不一致：" + addDTO.getStaffName());
                }else{
                    addDTO.setStaffId(staff.getId());
                }
            }
        }
        return addDTO;
    }


    /**
     * 编辑校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param stafftrainList
     */
    private void checkUpdate(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Stafftrain> stafftrainList) {
        for (int i = 0; i < excelList.size(); i++) {
            Stafftrain stafftrain=new Stafftrain();

            StaffTrainAddDTO addDTO = checkStaffForUpdate(excelList, errMsg, i);

            checkDicItem(errMsg, addDTO);

            checkTime(errMsg, addDTO);

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, stafftrain);
                stafftrainList.add(stafftrain);
            }
        }
    }

    /**
     * 编辑更新 校检员工
     * @param excelList
     * @param errMsg
     * @param i
     * @return
     */
    private StaffTrainAddDTO checkStaffForUpdate(List excelList, StringBuffer errMsg, int i) {
        StaffTrainAddDTO addDTO = (StaffTrainAddDTO) excelList.get(i);
        if (null != addDTO.getStaffCode()){
            Staff staff = staffService.getOne(
                new QueryWrapper<Staff>().eq("staff_code", addDTO.getStaffCode())
            );
            if (null == staff){
                appendStringBuffer(errMsg, "系统查不到此员工工号：" + addDTO.getStaffCode() + "不存在");
            }else{
                if (!staff.getStaffName().equals(addDTO.getStaffName())){
                    appendStringBuffer(errMsg, "员工姓名不一致：" + addDTO.getStaffName());
                }else{
                    //检查数据库是否存在记录，且唯一记录。
                    List<Stafftrain> checkList = super.list(
                            new QueryWrapper<Stafftrain>().eq("staff_id", staff.getId())
                                    .eq("begin_time", addDTO.getBeginTime())
                                    .eq("end_time", addDTO.getEndTime())
                    );
                    if (checkList.isEmpty()){
                        appendStringBuffer(errMsg, "员工培训表中不存在此记录，因此不可编辑更新；");
                    }
                    if (!checkList.isEmpty()&&checkList.size()>1){
                        appendStringBuffer(errMsg, "员工培训表中存在多条此记录，因此不可编辑更新；");
                    }else{
                        addDTO.setId(checkList.get(0).getId());
                    }
                 }
            }
        }
        return addDTO;
    }

    /**
     * 校检时间
     * @param errMsg
     * @param addDTO
     */
    private String checkTime(StringBuffer errMsg, StaffTrainAddDTO addDTO) {
        String pattern = "";
        //校检开始时间的时间格式
        boolean checkBeginTime = false;
        List<String> formatList = Arrays.asList("yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss");
        for (String format : formatList) {
            checkBeginTime = DateUtils.isLegalDate(addDTO.getBeginTime(), format);
            if (checkBeginTime) {
                pattern = format;
                break;
            }
        }
        if (!checkBeginTime) {
            appendStringBuffer(errMsg, "请填写正确的开始时间的时间格式（yyyy/MM/dd HH:mm:ss 或者 yyyy/MM/dd HH:mm:ss 或者 yyyy.MM.dd HH:mm:ss）");
        }
        //校检结束时间的时间格式
        boolean checkEndTime = false;
        for (String format : formatList) {
            checkEndTime = DateUtils.isLegalDate(addDTO.getEndTime(), format);
            if (checkEndTime) {
                break;
            }
        }
        if (!checkEndTime) {
            appendStringBuffer(errMsg, "请填写正确的结束时间的时间格式（yyyy/MM/dd HH:mm:ss 或者 yyyy/MM/dd HH:mm:ss 或者 yyyy.MM.dd HH:mm:ss）");
        }
        //比较开始时间和结束时间
        boolean compareDateStatus = DateUtils.compareDate(addDTO.getBeginTime(), addDTO.getEndTime(), pattern);
        if (!compareDateStatus) {
            appendStringBuffer(errMsg, "结束日期必须在开始日期之后");
        }

        return pattern;
    }

    /**
     * 拼接组织异常信息
     * @param stringBuffer
     * @param errorMsg
     */
    void appendStringBuffer(StringBuffer stringBuffer, String errorMsg) {
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer();
        }

        stringBuffer.append(stringBuffer.length() > 0 ? errorMsg + ManpowerContants.CH_SEMICOLON : errorMsg);
    }
}
