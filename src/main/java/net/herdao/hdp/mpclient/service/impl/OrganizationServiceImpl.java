
package net.herdao.hdp.mpclient.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.mpclient.common.Utils.DateUtils;
import net.herdao.hdp.mpclient.entity.*;
import net.herdao.hdp.mpclient.mapper.OrganizationMapper;
import net.herdao.hdp.mpclient.service.*;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

/**
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
@Service
@AllArgsConstructor
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    private final UserpostService userpostService;

    private final OrgModifyRecordService orgModifyRecordService;

    private final RemoteUserService remoteUserService;

    private final PostService postService;

    private final UserService userService;

    @Override
    public List<Organization> selectOrganizationListByParentOid(String parentId) {
        List<Organization> list = this.baseMapper.selectOrganizationListByParentOid(parentId);
        return list;
    }

    @Override
    public List<Organization> findAllOrganizations(Organization condition) {
        List<Organization> list = this.baseMapper.findAllOrganizations(condition);
        return list;
    }

    @Override
    public List<Organization> fetchDeptTree(Organization condition) {
        List<Organization> list = this.baseMapper.fetchDeptTree(condition);
        return list;
    }

    @Override
    public List<Organization> findOrganizationByCondition(Organization condition) {
        List<Organization> list = this.baseMapper.findOrganizationByCondition(condition);
        return list;
    }

    @Override
    public List<Organization> findOrganizationByKeyWords(String keyword) {
        List<Organization> list = this.baseMapper.findOrganizationByKeyWords(keyword);
        return list;
    }

    @Override
    public List<Organization> selectOrganByCurrentOrgOid(String id) {
        List<Organization> list = this.baseMapper.selectOrganByCurrentOrgOid(id);
        return list;
    }


    /**
     * 组织启用/停用
     * @param condition
     * @return R
     */
    @ApiOperation(value = "组织启用/停用", notes = "组织启用/停用")
    @SysLog("组织启用/停用")
    @Override
    @Transactional
    public R startOrStopOrg(@RequestBody Organization condition) {
        try {
            //更新组织变更记录表
            OrgModifyRecord record=new OrgModifyRecord();
            record.setCurOrgId(condition.getId());
            record.setCurOrgName(condition.getOrgName());
            record.setCurOrgCode(condition.getOrgCode());
            record.setCurOrgParentId(condition.getParentId());
            record.setCurOrgTreeLevel(condition.getOrgTreeLevel());
            record.setOperatorTime(new Date());
            UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
            if (null != userInfo){
                //操作人ID
                record.setOperatorId(userInfo.getSysUser().getUserId().toString());
                //操作人名称
                record.setOperatorName(userInfo.getSysUser().getUsername());
            }

            // 是否停用 ： 0 停用 ，1启用（默认），3全部
            //如果是启用操作，则直接启用当前组织。
            if (null != condition && condition.getIsStop() == 1){
                //更新该组织的启用日期 设为启用
                condition.setStartOrgOperateDate(new Date());
                this.baseMapper.updateById(condition);

                record.setOperateDesc("启用组织");
                record.setEffectTime(DateUtils.parseDate(condition.getStartDate(),"yyyy-MM-dd HH:mm:ss"));
                return R.ok("组织的启用成功");
            }

            //如果是停用操作
            if (null != condition && condition.getIsStop() == 0){
                record.setOperateDesc("停用组织");
                 record.setEffectTime(DateUtils.parseDate(condition.getStopDate(),"yyyy-MM-dd HH:mm:ss"));

                List<Map<String, Long>> orgIds = new ArrayList<>();
                //递归获取组织架构parentId
                getRecursionParentIds(condition.getId(), orgIds,"stopOrg");

                if (!orgIds.isEmpty()) {
                    List<Long> orgIdList = new ArrayList<>();
                    //操作标志
                    boolean operFlag = false;
                    for (Map<String, Long> maps : orgIds) {
                        for (Long orgId : maps.values()) {
                            orgIdList.add(orgId);
                        }
                    }
                    Userpost userpost = new Userpost();
                    userpost.setOrgDeptIds(orgIdList);
                    //查询该组织及其所有下层组织中任一个是否有挂靠的在职员工
                    List<Userpost> userPostList = userpostService.findUserPost(userpost);
                    if (!userPostList.isEmpty()) {
                        log.error("停用组织失败,该组织及其下属组织有挂靠员工！");
                        R.failed("停用组织失败,该组织及其下属组织有挂靠员工！");
                        return R.ok("停用组织失败,该组织及其下属组织有挂靠员工！");
                    }else{
                        operFlag = true;
                    }

                    //如果该组织及其所有下层组织中任一个有挂靠的在职员工 则不能停用 更不能删除
                    if (operFlag) {
                        try {
                            //遍历循环组织架构id
                            for (Map<String, Long> maps : orgIds) {
                                //停用组织及其下层组织
                                for (Long orgId : maps.values()) {
                                    //更新该组织的停用日期 设置为停用状态
                                    Organization updateCondition=new Organization();
                                    updateCondition.setId(orgId);
                                    updateCondition.setIsStop(0);
                                    updateCondition.setStartOrgOperateDate(new Date());
                                    if(null != condition.getStopDate()){
                                        updateCondition.setStopDate(condition.getStopDate());
                                    }

                                    this.baseMapper.updateById(updateCondition);
                                }
                            }

                            //更新组织变更记录表
                            orgModifyRecordService.update();

                        }catch (Exception ex){
                            log.error("组织的停用失败");
                            return R.failed("组织的停用失败");
                        }

                        return R.ok("组织的停用成功");
                    }
                }
            }

            //更新组织变更记录表
            orgModifyRecordService.save(record);
        }catch (Exception ex){
            log.error("组织启用/停用失败",ex);
            return R.failed("组织启用/停用失败");
        }

        return R.ok("组织的停用成功");
    }


    @SysLog("递归获取组织架构parentId")
    public void getRecursionParentIds(Long id, List<Map<String, Long>> orgIds,String operate) {
        Organization condition = new Organization();
        condition.setId(id);
        List<Organization> list=new ArrayList<>();
        if (null != operate && operate=="stopOrg"){
            list = this.baseMapper.findAllOrg(condition);
        }else{
            list = this.baseMapper.findAllOrganizations(condition);
        }

        if (!list.isEmpty()) {
            for (Organization entity : list) {
                if (null != entity) {
                    Map<String, Long> map = new HashMap<>();
                    map.put(entity.getOrgName(), entity.getId());
                    orgIds.add(map);

                    if (null !=entity.getChildren() && !entity.getChildren().isEmpty()) {
                        getChildren(entity, orgIds);
                    }
                }

            }
        }
    }

    @SysLog("递归获取组织架构parentId")
    public void getChildren(Organization entity, List<Map<String, Long>> orgIds) {
        List<Organization> children = entity.getChildren();
        if (!children.isEmpty()) {
            for (Organization child : children) {
                if (null != child) {
                    Map<String, Long> map = new HashMap<>();
                    map.put(child.getOrgName(), child.getId());
                    orgIds.add(map);
                    getChildren(child, orgIds);
                }

            }
        }
    }

    @SysLog("点击展开组织架构树（默认两级）")
    @Override
    public R getRecursionOrgByLevel(@RequestBody Organization condition) {
        List<Organization> allOrgList = new ArrayList<>();
        List<Organization> rootOrgList = this.baseMapper.findOrganizationByCondition(condition);

        if (!rootOrgList.isEmpty()) {
            allOrgList.addAll(rootOrgList);
            List<Organization> childrenTemp = new ArrayList<>();
            for (Organization rootOrgan : rootOrgList) {
                Organization childrenCondition = new Organization();
                childrenCondition.setParentId(rootOrgan.getId());
                if (null == condition.getOrgTreeLevel()) {
                    //默认展示两级
                    queryOrgByParentId(childrenTemp, childrenCondition, 2L);
                } else {
                    //传参自定义展示层级数
                    queryOrgByParentId(childrenTemp, childrenCondition, condition.getOrgTreeLevel());
                }

            }
            allOrgList.addAll(childrenTemp);
        }

        //查询分页结果
        List<Long> ids = new ArrayList<>();
        for (Organization organization : allOrgList) {
            ids.add(organization.getId());
        }
        QueryWrapper<Organization> wrapper = Wrappers.query();
        wrapper.in("id", ids);

        List<Organization> list = super.list(wrapper);

        return R.ok(list);
    }

    @SysLog("点击展开组织架构树（默认两级）")
    private void queryOrgByParentId(List<Organization> childrenTemp, Organization condition, Long orgLevel) {
        List<Organization> children = this.baseMapper.findOrganizationByCondition(condition);
        if (!children.isEmpty()) {
            childrenTemp.addAll(children);
            for (Organization child : children) {
                Organization entity = new Organization();
                entity.setParentId(child.getId());
                //控制层级
                if (null != child.getOrgLevel()) {
                    if (Integer.parseInt(child.getOrgLevel()) < orgLevel) {
                        queryOrgByParentId(childrenTemp, entity, orgLevel);
                    }
                }
            }
        }
    }

    /**
     * 删除组织
     * @param condition
     * @return R
     */
    @Override
    public R removeOrg(@RequestBody Organization condition) {
        try {
            List<Map<String, Long>> orgIds = new ArrayList<>();
            //递归获取组织架构parentId
            getRecursionParentIds(condition.getId(), orgIds,"removeOrg");

            if (!orgIds.isEmpty()) {
                List<Long> orgIdList = new ArrayList<>();
                //删除标志
                boolean delFlag = false;
                for (Map<String, Long> maps : orgIds) {
                    for (Long orgId : maps.values()) {
                        orgIdList.add(orgId);
                    }
                }
                Userpost userpost = new Userpost();
                userpost.setOrgDeptIds(orgIdList);
                //查询该组织及其所有下层组织中任一个是否有挂靠的在职员工
                List<Userpost> userPostList = userpostService.findUserPost(userpost);
                if (!userPostList.isEmpty()) {
                    delFlag = true;
                    log.error("删除组织失败,该组织及其下属组织有挂靠员工！");
                    R.failed("删除组织失败,该组织及其下属组织有挂靠员工！");
                }

                //如果该组织及其所有下层组织中任一个有挂靠的在职员工 则不能停用 更不能删除
                if (delFlag == true) {
                    //遍历循环组织架构id
                    for (Map<String, Long> maps : orgIds) {
                        //停用组织及其下层组织
                        for (Long orgId : maps.values()) {
                            this.baseMapper.stopOrganById(orgId);
                        }

                        //组织需停用后才能删除 停用选中组织的所有下层组织
                        for (Long orgId : maps.values()) {
                            super.removeById(orgId);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error("删除组织失败,事务回滚！");
            R.failed("删除组织失败,事务回滚！");
        }

        return R.ok("删除组织成功");
    }

     /**
     * 默认加载展示2级组织架构
     * @param condition
     * @return R
     */
    @Override
    public  R findOrganization2Level(@RequestBody Organization condition) {
        if (null != condition) {
            //默认加载启用状态的组织架构(0 停用 ，1启用，3全部)
            condition.setIsStop(1);
        }
        List<Organization> rootOrgans = this.baseMapper.findRootOrganizations(condition);

        if (!rootOrgans.isEmpty()) {
            for (Organization rootOrgan : rootOrgans) {
                Organization childrenCondition = new Organization();
                childrenCondition.setParentId(rootOrgan.getId());
                //默认加载启用状态的组织架构(0 停用 ，1启用，3全部)
                childrenCondition.setIsStop(1);

                List<Organization> childrenOrgans = this.baseMapper.findOrganizationByCondition(childrenCondition);
                rootOrgan.setChildren(childrenOrgans);
            }

        }

        return R.ok(rootOrgans);
    }

    @Override
    public Page<Organization> findOrgPage(Page<Organization> page, String orgCode,Long treeLevel) {
        if (treeLevel != null){
            treeLevel++;
        }else{
            treeLevel=1L;
        }
        Page<Organization> result = this.baseMapper.findOrgPage(page, orgCode,treeLevel);
        return result;
    }


    /**
     * 编辑更新组织
     * @param organization
     * @return R
     */
    @SysLog("编辑更新组织")
    @Override
    @Transactional
    public R updateOrg(@RequestBody Organization organization) {
        try {
            Organization oldOrg = super.getById(organization.getId());

            OrgModifyRecord record=new OrgModifyRecord();
            boolean operateFlag = false;

            //现组织名称 原组织名称
            if (null != oldOrg.getOrgName()&& !oldOrg.getOrgName().equals(organization.getOrgName())){
                record.setCurOrgName(organization.getOrgName());
                record.setOldOrgName(oldOrg.getOrgName());
                operateFlag = true;
            }

            //现组织编码 原组织名称
            if (null != oldOrg.getOrgCode()&& !oldOrg.getOrgCode().equals(organization.getOrgCode())){
                record.setCurOrgCode(organization.getOrgCode());
                record.setOldOrgName(oldOrg.getOrgCode());
                operateFlag = true;
            }

            if (null != oldOrg.getParentId() && !oldOrg.getParentId().equals(organization.getParentId())){
                Organization oldParenOrg = super.getById(oldOrg.getParentId());
                Organization curParenOrg = super.getById(organization.getParentId());

                record.setCurOrgParentId(organization.getParentId());
                //现上级组织id
                if (null != oldParenOrg){
                    record.setOldOrgParentId(oldParenOrg.getId());
                    record.setOldOrgParentName(oldParenOrg.getOrgName());

                    //现组织层级 原组织层级
                    if (null != oldOrg.getOrgTreeLevel()&& !oldOrg.getOrgCode().equals(organization.getOrgTreeLevel())){
                        record.setCurOrgTreeLevel(organization.getOrgTreeLevel());
                        record.setOldOrgTreeLevel(oldOrg.getOrgTreeLevel());
                        operateFlag = true;
                    }
                }

                //现上级组织名称
                if (null != curParenOrg){
                    record.setCurOrgName(curParenOrg.getOrgName());
                }

                operateFlag = true;
            }

            //生效时间
            record.setEffectTime(new Date());
            //操作时间
            record.setOperatorTime(new Date());

            UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
            if (null != userInfo){
                //操作人ID
                record.setOperatorId(userInfo.getSysUser().getUserId().toString());
                //操作人名称
                record.setOperatorName(userInfo.getSysUser().getUsername());
            }

            //新增变更记录
            if (operateFlag){
                record.setOperateDesc("修改组织架构");
                orgModifyRecordService.save(record);
            }

            super.updateById(organization);
        }catch (Exception ex){
            log.error("编辑更新组织组织失败",ex);
            return R.failed("编辑更新组织组织失败");
        }

        return R.ok(organization);
    }

    @SysLog("新增组织架构")
    @Override
    public R saveOrg(@RequestBody Organization organization) {
        if (null != organization) {
            //查询岗位负责人姓名 mp_post表
            Post post = postService.getById(organization.getChargeOrg());
            if (null != post) {
                organization.setPostName(post.getPostName());
            }

            //查询组织负责人
            User user = userService.getById(organization.getOrgChargeWorkNo());
            if (null != user) {
                organization.setUserName(user.getUserName());
            }

            QueryWrapper<Organization> wrapper = Wrappers.query();
            wrapper.eq("id",organization.getParentId());
            //获取父组织
            Organization parentOrg = super.getOne(wrapper);
            if (null != parentOrg){
                if (null != parentOrg.getOrgTreeLevel()){
                    //当前新增组织的组织树层级数+1
                    organization.setOrgTreeLevel(parentOrg.getOrgTreeLevel()+1);
                }

                //生成组织编码orgCode
                createOrgCode(organization, parentOrg);
            }
        }
        super.save(organization);

        return R.ok(organization);
    }



    /**
     * 生成组织编码 orgCode
     */
    private void createOrgCode(Organization org, Organization parentOrg) {
        Map<String,Object> childrenParams =new HashMap<>();
        childrenParams.put("parentId",parentOrg.getId());
        List<Organization> childOrgList = getOrgList(childrenParams);
        //挂靠父组织 父组织下有多个子组织 拿子组织中最大的组织编码
        if (childOrgList !=null && !childOrgList.isEmpty()){
            String orgCodeTemp ="";
            Long maxOrgCode=null;
            for (Organization organization : childOrgList) {
                if (null == maxOrgCode){
                    maxOrgCode = Long.parseLong(organization.getOrgCode());
                    orgCodeTemp = organization.getOrgCode();
                }
                if (Long.parseLong(organization.getOrgCode())>maxOrgCode){
                    maxOrgCode = Long.parseLong(organization.getOrgCode());
                    orgCodeTemp = organization.getOrgCode();
                }

                org.setOrgCode(orgCodeTemp);
            }

            //组装组织编码
            if (orgCodeTemp !=null && !orgCodeTemp.isEmpty()){
                int orgLength = orgCodeTemp.length();
                Long temp= Long.parseLong(orgCodeTemp)+1;
                String newOrgCode= String.format("%0"+orgLength+"d", temp);
                org.setOrgCode(newOrgCode);
            }
        }else { // 挂靠父组织 父组织下没有子组织 则父组织是最大的组织编码
            String newOrgCode=org.getParentIdStr()+"001";
            org.setOrgCode(newOrgCode);
        }
    }

    /**
     * 获取组织集合
     */
    private List<Organization> getOrgList(Map<String,Object> params){
        QueryWrapper<Organization> wrapper = Wrappers.query();
        if ( null!=params && !params.isEmpty()){
            if (params.get("parentId")!=null){
                wrapper.eq("parent_id",params.get("parentId"));
            }
        }

        List<Organization> list = super.list(wrapper);
        return list;
    }
}
