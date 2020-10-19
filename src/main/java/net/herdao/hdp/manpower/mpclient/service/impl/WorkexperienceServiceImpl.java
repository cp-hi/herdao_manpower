
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.staff.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;
import net.herdao.hdp.manpower.mpclient.mapper.WorkexperienceMapper;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.mpclient.service.WorkexperienceService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工工作经历
 *
 * @author andy
 * @date 2020-09-24 10:24:09
 */
@Service
public class WorkexperienceServiceImpl extends ServiceImpl<WorkexperienceMapper, Workexperience> implements WorkexperienceService {
    @Autowired
    private RemoteUserService remoteUserService;


    @Override
    public Page<WorkexperienceDTO> findStaffWorkPage(Page<WorkexperienceDTO> page, String searchText) {
        Page<WorkexperienceDTO> pageResult = this.baseMapper.findStaffWorkPage(page, searchText);
        return pageResult;
    }

    @Override
    public List<WorkexperienceDTO> findStaffWork(String searchText) {
        List<WorkexperienceDTO> list = this.baseMapper.findStaffWork(searchText);
        return list;
    }

    @Override
    public boolean saveWorkDTO(WorkexperienceDTO workexperienceDTO){
    	UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getUsername();
        String loginCode=userInfo.getSysUser().getUsername();
        
        Workexperience workexperience = new Workexperience();
        BeanUtils.copyProperties(workexperienceDTO, workexperience);
        //创建人工号、姓名、时间；修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        workexperience.setCreatorCode(loginCode); 
        workexperience.setCreatorName(userName);
        workexperience.setCreatedTime(now);
        workexperience.setModifierCode(loginCode); 
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        boolean flag = super.save(workexperience);
        return flag;
    }

    @Override
    public boolean updateWorkDTO(WorkexperienceDTO workexperienceDTO) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getUsername();
        String loginCode=userInfo.getSysUser().getUsername();
        
        Workexperience workexperience = this.getById(workexperienceDTO.getId());
        BeanUtils.copyProperties(workexperienceDTO, workexperience);
        //修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        workexperience.setModifierCode(loginCode);
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        boolean status = super.updateById(workexperience);
        return status;
    }
    
    @Override
    public List<WorkexperienceDTO> findWorkexperienceDTO(Long staffid){
    	List<WorkexperienceDTO> workexperienceDTOList = this.baseMapper.findWorkexperienceDTO(staffid);
    	return workexperienceDTOList;
    }

    @Override
    public boolean saveWork(Workexperience workexperience) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getUsername();
        String loginCode=userInfo.getSysUser().getUsername();
        LocalDateTime now = LocalDateTime.now();
        workexperience.setCreatorCode(loginCode);
        workexperience.setCreatorName(userName);
        workexperience.setCreatedTime(now);
        workexperience.setModifierCode(loginCode);
        workexperience.setModifierName(userName);
        workexperience.setModifiedTime(now);
        boolean flag = super.save(workexperience);
        return flag;
     }

    @Override
    public boolean updateWork(Workexperience orkexperience) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getUsername();
        String loginCode=userInfo.getSysUser().getUsername();

        //修改人工号、姓名、时间
        LocalDateTime now = LocalDateTime.now();
        orkexperience.setModifierCode(loginCode);
        orkexperience.setModifierName(userName);
        orkexperience.setModifiedTime(now);
        boolean status = super.updateById(orkexperience);
        return status;
    }
}
