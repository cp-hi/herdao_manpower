
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpUpdateDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import net.herdao.hdp.manpower.mpclient.mapper.StaffRewardsPulishmentsMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.ImportCheckUtils;
import net.herdao.hdp.manpower.mpclient.utils.ObjectFieldCompareUtils;
import net.herdao.hdp.manpower.mpclient.vo.staffRp.StaffRpChangePropertiesVO;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@Service
public class StaffRewardsPulishmentsServiceImpl extends ServiceImpl<StaffRewardsPulishmentsMapper, StaffRewardsPulishments> implements StaffRewardsPulishmentsService {
    @Autowired
    private SysDictItemService itemService;

    @Autowired
    private StaffService staffService;

    @Override
    public Page<StaffRpDTO> findStaffRpPage(Page<StaffRpDTO> page, String searchText) {
        Page<StaffRpDTO> list = this.baseMapper.findStaffRpPage(page, searchText);
        return list;
    }

    @Override
    public List<StaffRpDTO> findStaffRp(String searchText) {
        List<StaffRpDTO> list = this.baseMapper.findStaffRp(searchText);
        return list;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        StringBuffer errMsg = new StringBuffer();
        // 错误数组
        List<ExcelCheckErrDTO> errList = new ArrayList<>();

        List<StaffRewardsPulishments> staffRpList=new ArrayList<StaffRewardsPulishments>();

        // 新增校验
        if (importType == 0) {
            checkAdd(excelList, errMsg, errList, staffRpList);
        }

        // 编辑校验
        if (importType == 1) {
            checkUpdate(excelList, errMsg, errList, staffRpList);
        }

        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(staffRpList);
        }
        return errList;
    }

    /**
     * 新增校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param staffRpList
     */
    private void checkAdd(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<StaffRewardsPulishments> staffRpList) {
        for (int i = 0; i < excelList.size(); i++) {
            StaffRewardsPulishments staffRp=new StaffRewardsPulishments();
            StaffRpAddDTO addDTO = (StaffRpAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId);

            //校检字典
            SysDictItem dictItem = ImportCheckUtils.checkDicItem(errMsg, "YGJCLB", addDTO.getType(), itemService);
            if(null != dictItem){
                addDTO.setType(dictItem.getValue());
            }

            //校检时间
            String pattern= ImportCheckUtils.checkSingleTime(errMsg, addDTO.getExecuteDate());

            //检查数据库是否存在记录，且唯一记录。
            String choiceValue="";
            if (addDTO.getChoice().equals("奖励")){
                choiceValue="1";
            }
            if (addDTO.getChoice().equals("惩罚")){
                choiceValue="2";
            }
            List<StaffRewardsPulishments> checkList = this.list(
                    new QueryWrapper<StaffRewardsPulishments>().eq("staff_id", staffId)
                            .eq("choice", choiceValue)
                            .eq("execute_date", addDTO.getExecuteDate())
                            .eq("type",  dictItem!=null? dictItem.getValue():"")
            );
            if (!checkList.isEmpty() && checkList.size()>=1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工奖惩表中存在多条此记录，因此不可新增；");
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, staffRp);
                staffRp.setExecuteDate(DateUtils.parseDate(addDTO.getExecuteDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                staffRp.setCreatedTime(LocalDateTime.now());
                staffRp.setCreatorCode(sysUser.getUserId().toString());
                staffRp.setChoice(Long.parseLong(choiceValue));

                staffRpList.add(staffRp);
            }
        }
    }

    /**
     * 编辑校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param staffRpList
     */
    private void checkUpdate(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<StaffRewardsPulishments> staffRpList) {
        for (int i = 0; i < excelList.size(); i++) {
            StaffRewardsPulishments staffRp=new StaffRewardsPulishments();
            StaffRpUpdateDTO updateDTO = (StaffRpUpdateDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, updateDTO.getStaffCode(), updateDTO.getStaffName(),staffService);
            updateDTO.setStaffId(staffId);

            //校检字典
            SysDictItem dictItem = ImportCheckUtils.checkDicItem(errMsg, "YGJCLB", updateDTO.getType(), itemService);
            if(null != dictItem){
                updateDTO.setType(dictItem.getValue());
            }

            //校检时间
            String pattern= ImportCheckUtils.checkSingleTime(errMsg, updateDTO.getExecuteDate());

            //检查数据库是否存在记录，且唯一记录。
            String choiceValue="";
            if (updateDTO.getChoice().equals("奖励")){
                choiceValue="1";
            }
            if (updateDTO.getChoice().equals("惩罚")){
                choiceValue="2";
            }
            List<StaffRewardsPulishments> checkList = super.list(
                    new QueryWrapper<StaffRewardsPulishments>()
                            .eq("staff_id", staffId)
                            .eq("choice", choiceValue)
                            .eq("execute_date", updateDTO.getExecuteDate())
                            .eq("type", dictItem.getValue())
            );

            if (checkList.isEmpty()){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工奖惩表中不存在此记录，因此不可编辑；");
            }else if (checkList.size()>1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工奖惩表中存在多条此记录，因此不可编辑；");
            }else{
                updateDTO.setId(checkList.get(0).getId());
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(updateDTO, errMsg.toString()));
            }else {
                StaffRpChangePropertiesVO newStaffRp=new StaffRpChangePropertiesVO();
                BeanUtils.copyProperties(updateDTO,newStaffRp);

                StaffRpChangePropertiesVO oldStaffRp=new StaffRpChangePropertiesVO();
                BeanUtils.copyProperties(checkList.get(0),oldStaffRp);

                List<Map<String, Object>> compareResult = ObjectFieldCompareUtils.compareObject(newStaffRp, oldStaffRp);
                //数据有变动
                if (!ObjectUtil.isEmpty(compareResult)){
                    BeanUtils.copyProperties(updateDTO, staffRp);

                    SysUser sysUser = SysUserUtils.getSysUser();
                    staffRp.setModifiedTime(LocalDateTime.now());
                    staffRp.setModifierCode(sysUser.getUserId().toString());
                    staffRp.setChoice(Long.parseLong(choiceValue));

                    staffRpList.add(staffRp);
                }
            }
        }
    }
}
