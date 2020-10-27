
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.familyStatus.FamilyStatusListDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.FamilystatusMapper;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.utils.RegexUtils;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return null;
    }
}
