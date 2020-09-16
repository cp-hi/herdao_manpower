
package net.hedao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.hedao.hdp.mpclient.entity.Organization;
import net.hedao.hdp.mpclient.entity.Userpost;
import net.hedao.hdp.mpclient.mapper.OrganizationMapper;
import net.hedao.hdp.mpclient.service.OrganizationService;
import net.hedao.hdp.mpclient.service.UserpostService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
@Service
@AllArgsConstructor
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    private final UserpostService userpostService;


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

    @Override
    public List<Organization> findRootOrganizations(Organization condition) {
        List<Organization> list = this.baseMapper.findRootOrganizations(condition);
        return list;
    }

    @Override
    public List<Organization> findOrgPage(Organization condition) {
        List<Organization> list = this.baseMapper.findOrgPage(condition);
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
    public void startOrStopOrg(@RequestBody Organization condition) {
        // 是否停用 ： 0 停用 ，1启用（默认），3全部
        //如果是启用操作，则直接启用当前组织。
        if (null != condition && condition.getIsStop() == 1){
            this.baseMapper.startOrganById(condition.getId());

            //更新该组织的启用日期  start_date
            this.baseMapper.updateById(condition);
        }

        //如果是停用操作
        if (null != condition && condition.getIsStop() == 0){
            List<Map<String, Long>> orgIds = new ArrayList<>();
            //递归获取组织架构parentId
            getRecursionParentIds(condition.getId(), orgIds);

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
                    operFlag = true;
                    log.error("停用组织失败,该组织及其下属组织有挂靠员工！");
                    R.failed("停用组织失败,该组织及其下属组织有挂靠员工！");
                }

                //如果该组织及其所有下层组织中任一个有挂靠的在职员工 则不能停用 更不能删除
                if (operFlag == true) {
                    //遍历循环组织架构id
                    for (Map<String, Long> maps : orgIds) {
                        //停用组织及其下层组织
                        for (Long orgId : maps.values()) {
                            this.baseMapper.stopOrganById(orgId);
                            //更新该组织的停用日期  end_date
                            Organization updateCondition=new Organization();
                            this.baseMapper.updateById(updateCondition);
                        }
                    }
                }
            }
        }
    }


    @SysLog("递归获取组织架构parentId")
    public void getRecursionParentIds(Long id, List<Map<String, Long>> orgIds) {
        Organization condition = new Organization();
        condition.setId(id);
        List<Organization> list = this.baseMapper.findAllOrganizations(condition);
        if (!list.isEmpty()) {
            for (Organization entity : list) {
                if (null != entity) {
                    Map<String, Long> map = new HashMap<>();
                    map.put(entity.getOrgName(), entity.getId());
                    orgIds.add(map);

                    if (!entity.getChildrenClick().isEmpty()) {
                        getChildren(entity, orgIds);
                    }
                }

            }
        }
    }

    @SysLog("递归获取组织架构parentId")
    public void getChildren(Organization entity, List<Map<String, Long>> orgIds) {
        List<Organization> childrens = entity.getChildrenClick();
        if (!childrens.isEmpty()) {
            for (Organization child : childrens) {
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
    public R getRecursionOrgByLevel(Page page, @RequestBody Organization condition) {
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

        Page pageResult = super.page(page, wrapper);

        return R.ok(pageResult);
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
            getRecursionParentIds(condition.getId(), orgIds);

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
    public  R findOrganization2LevelByCondition(@RequestBody Organization condition) {
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


    /**
     * 分页查询组织架构
     * @param page 分页对象
     * @param organization
     * @return
     */
    @Override
    public Page findOrgPage(Page page, Organization organization) {
        Organization childrenCondition=new Organization();
        childrenCondition.setParentId(organization.getId());

        List<Organization> childrenOrgList = this.baseMapper.findOrganizationByCondition(childrenCondition);
        QueryWrapper<Organization> wrapper = Wrappers.query();

        if (!StringUtils.isBlank(organization.getOrgName())){
            wrapper.like("org_name",organization.getOrgName());
        }

        if (null != organization && organization.getId()!=null){
            List<Long> orgIds=new ArrayList<>();
            orgIds.add(organization.getId());
            if (null != childrenOrgList && !childrenOrgList.isEmpty()){
                for (Organization entity : childrenOrgList) {
                    orgIds.add(entity.getId());
                }
            }

            wrapper.in("id", orgIds);
        }

        return super.page(page, wrapper);
    }
}
