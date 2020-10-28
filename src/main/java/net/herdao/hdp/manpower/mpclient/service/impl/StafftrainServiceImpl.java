
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainAddDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.mapper.StafftrainMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.ImportCheckUtils;
import net.herdao.hdp.manpower.sys.service.impl.SysDictItemServiceImpl;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 员工培训
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
    public String getAddRemarks() {
        StringBuffer remarks = new StringBuffer();
        remarks.append("导入说明：\r\n")
                .append("1、标红字段为必填\r\n")
                .append("2、操作导入前请删除示例数据\r\n")
                .append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
                .append("4、开始时间，结束时间的日期格式为：yyyy/MM/dd HH:mm:ss 或者 yyyy/MM/dd HH:mm:ss 或者 yyyy.MM.dd HH:mm:ss。\r\n")
                .append("5、培训类型：输入系统中已存在的类型 ，如：内部培训， 外部培训。\r\n")
                .append("6、培训时长，培训总学时，培训总学分，培训成绩输入内容格式：正整数。\r\n")
                .append("7、员工姓名+员工工号+开始时间+结束时间:数据唯一标识，不允许重复导入记录。");
         return remarks.toString();
    }

    @Override
    public String getUpdateRemarks() {
        StringBuffer remarks = new StringBuffer();
        remarks.append("导入说明：\r\n")
                .append("1、标红字段为必填\r\n")
                .append("2、操作导入前请删除示例数据\r\n")
                .append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
                .append("4、开始时间，结束时间的日期格式为：yyyy/MM/dd HH:mm:ss 或者 yyyy/MM/dd HH:mm:ss 或者 yyyy.MM.dd HH:mm:ss。\r\n")
                .append("5、培训类型：输入系统中已存在的类型 ，如：内部培训， 外部培训。\r\n")
                .append("6、培训时长，培训总学时，培训总学分，培训成绩输入内容格式：正整数。");

        return remarks.toString();
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        StringBuffer errMsg = new StringBuffer();
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
            StaffTrainAddDTO addDTO = (StaffTrainAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId);

            //校检员工培训类型
            SysDictItem dictItem = ImportCheckUtils.checkDicItem(errMsg, "TRAIN_TYPE", addDTO.getTrainType(), itemService);
            if(null != dictItem){
                addDTO.setTrainType(dictItem.getValue());
            }

            //校检时间
            String pattern= ImportCheckUtils.checkTime(errMsg, addDTO.getBeginTime(),addDTO.getEndTime());

            //检查数据库是否存在记录，且唯一记录。
            List<Stafftrain> checkList = super.list(
                    new QueryWrapper<Stafftrain>().eq("staff_id", staffId)
                            .eq("begin_time", addDTO.getBeginTime())
                            .eq("end_time", addDTO.getEndTime())
            );
            if (!checkList.isEmpty()&&checkList.size()>=1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工培训表中存在多条此记录，因此不可新增；");
            }

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
     * 编辑校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param stafftrainList
     */
    private void checkUpdate(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Stafftrain> stafftrainList) {
        for (int i = 0; i < excelList.size(); i++) {
            Stafftrain stafftrain=new Stafftrain();
            StaffTrainAddDTO addDTO = (StaffTrainAddDTO) excelList.get(i);
            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId);

            //校检培训类型
            ImportCheckUtils.checkDicItem(errMsg,"TRAIN_TYPE", addDTO.getTrainType(),itemService);

            //校检时间
            String pattern= ImportCheckUtils.checkTime(errMsg, addDTO.getBeginTime(),addDTO.getEndTime());

            //检查数据库是否存在记录，且唯一记录。
            List<Stafftrain> checkList = super.list(
                    new QueryWrapper<Stafftrain>().eq("staff_id", staffId)
                            .eq("begin_time", addDTO.getBeginTime())
                            .eq("end_time", addDTO.getEndTime())
            );
            if (checkList.isEmpty()){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工培训表中不存在此记录，因此不可编辑更新；");
            }else if (!checkList.isEmpty()&&checkList.size()>1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工培训表中存在多条此记录，因此不可编辑更新；");
            }else{
                addDTO.setId(checkList.get(0).getId());
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, stafftrain);
                stafftrain.setBeginTime(DateUtils.parseDate(addDTO.getBeginTime(),pattern));
                stafftrain.setEndTime(DateUtils.parseDate(addDTO.getEndTime(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                stafftrain.setModifiedTime(new Date());
                stafftrain.setModifierCode(sysUser.getUserId().toString());
                stafftrainList.add(stafftrain);
            }
        }
    }


}
