
package net.herdao.hdp.manpower.mpclient.service.impl;

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

        // 新增校验
        if (importType == 0) {
            for (int i = 0; i < excelList.size(); i++) {
                StaffTrainAddDTO addDTO = (StaffTrainAddDTO) excelList.get(i);
                if (null != addDTO.getStaffCode()){
                    Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_code", addDTO.getStaffCode()));
                    if (null == staff){
                        appendStringBuffer(errMsg, "系统查不到此员工工号：" + addDTO.getStaffCode() + "不存在");
                    }

                    if (!staff.getStaffName().equals(addDTO.getStaffName())){
                        appendStringBuffer(errMsg, "员工姓名不一致：" + addDTO.getStaffName());
                    }
                }

                SysDictItem trainTypeDictItem = itemService.getOne(
                    new QueryWrapper<SysDictItem>()
                            .eq("type", "TRAIN_TYPE")
                            .eq("label", addDTO.getTrainType())
                            .eq("del_flag", 0)
                );
                if (null==trainTypeDictItem){
                    appendStringBuffer(errMsg, "培训类型不存在或已停用：" + addDTO.getTrainType());
                }

                if (errMsg.length() > 0) {
                    errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
                }
            }



        }
        return errList;
    }

    /**
     * 拼接组织异常信息
     *
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
