
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.FamilyStatusListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.StaffFamilyAddDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.FamilystatusMapper;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.utils.ImportCheckUtils;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工家庭成员
 *
 * @author andy
 * @date 2020-09-23 10:53:08
 */
@Service
public class FamilystatusServiceImpl extends ServiceImpl<FamilystatusMapper, Familystatus> implements FamilystatusService {

    @Autowired
    private StaffService staffService;

    @Autowired
    private SysDictItemService itemService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public Page<FamilyStatusListDTO> findFamilyStatusPage(Page<FamilyStatusListDTO> page, String searchText) {
        Page<FamilyStatusListDTO> pageResult = this.baseMapper.findFamilyStatusPage(page, searchText);
        return pageResult;
    }

    @Override
    public List<FamilyStatusVO> findFamilyStatus(String searchText) {
        List<FamilyStatusVO> list = this.baseMapper.findFamilyStatus(searchText);
        return list;
    }

    @Override
    @OperationEntity(operation = "保存或新增", clazz = Familystatus.class)
    public boolean saveOrUpdate(Familystatus familystatus) {
        boolean status =false;
        if (null != familystatus){
            UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
            String userName=userInfo.getSysUser().getUsername();
            String loginCode=userInfo.getSysUser().getUsername();

            //新增
            if (null == familystatus.getId()){
                familystatus.setCreatorCode(loginCode);
                familystatus.setCreatedTime(LocalDateTime.now());
                status = super.save(familystatus);
            }

            //修改
            if (null != familystatus.getId()){
                familystatus.setModifierCode(loginCode);
                familystatus.setModifierName(userName);
                familystatus.setModifiedTime(LocalDateTime.now());
                status = super.updateById(familystatus);
            }
        }

        return status;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        StringBuffer errMsg = new StringBuffer();
        List<ExcelCheckErrDTO> errList = new ArrayList<>();
        List<Familystatus> staffFamilyList=new ArrayList<Familystatus>();

        // 新增校验
        if (importType == 0) {
            checkAdd(excelList, errMsg, errList, staffFamilyList);
        }

        // 编辑校验
        if (importType == 1) {
            checkUpdate(excelList, errMsg, errList, staffFamilyList);
        }

        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(staffFamilyList);
        }
        return errList;
    }

    /**
     * 新增校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param familystatusList
     */
    private void checkAdd(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Familystatus> familystatusList) {
        for (int i = 0; i < excelList.size(); i++) {
            Familystatus familystatus=new Familystatus();
            StaffFamilyAddDTO addDTO = (StaffFamilyAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId);

            //校检关系
            SysDictItem relationItem = ImportCheckUtils.checkDicItem(errMsg, "RELATIONS_TYPE", addDTO.getRelations(), itemService);
            if(null != relationItem){
                addDTO.setRelations(relationItem.getValue());
            }

            //检查数据库是否存在记录，且唯一记录。
            List<Familystatus> checkList = super.list(
                    new QueryWrapper<Familystatus>()
                            .eq("staff_id", staffId)
                            .eq("name", addDTO.getName())
            );
            if (!checkList.isEmpty()&&checkList.size()>=1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工家庭表中存在多条此记录，因此不可新增；");
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, familystatus);
                SysUser sysUser = SysUserUtils.getSysUser();
                familystatus.setCreatedTime(LocalDateTime.now());
                familystatus.setCreatorCode(sysUser.getUserId().toString());

                familystatusList.add(familystatus);
            }
        }
    }

    /**
     * 编辑校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param familystatusList
     */
    private void checkUpdate(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Familystatus> familystatusList) {
        for (int i = 0; i < excelList.size(); i++) {
            Familystatus familystatus=new Familystatus();
            StaffFamilyAddDTO addDTO = (StaffFamilyAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId);

            //校检关系
            SysDictItem relationItem = ImportCheckUtils.checkDicItem(errMsg, "RELATIONS_TYPE", addDTO.getRelations(), itemService);
            if(null != relationItem){
                addDTO.setRelations(relationItem.getValue());
            }

            //检查数据库是否存在记录，且唯一记录。
            List<Familystatus> checkList = super.list(
                    new QueryWrapper<Familystatus>()
                            .eq("staff_id", staffId)
                            .eq("name", addDTO.getName())
            );
            if (checkList.isEmpty()){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工家庭表中不存在此记录，因此不可编辑更新；");
            }else if (!checkList.isEmpty()&&checkList.size()>1){
                ImportCheckUtils.appendStringBuffer(errMsg, "员工家庭表中不存在此记录，因此不可编辑更新；");
            }else{
                addDTO.setId(checkList.get(0).getId());
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, familystatus);
                SysUser sysUser = SysUserUtils.getSysUser();
                familystatus.setModifierName(sysUser.getUsername());
                familystatus.setModifiedTime(LocalDateTime.now());
                familystatus.setModifierCode(sysUser.getUserId().toString());

                familystatusList.add(familystatus);
            }
        }
    }
}
