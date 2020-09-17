package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.mpclient.entity.Organization;
import net.herdao.hdp.common.core.util.R;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 查询子组织架构
     * @param parentId
     * @return
     */
     List<Organization> selectOrganizationListByParentOid(String parentId);

    /**
     * 查询根组织架构
     * @return
     */
    List<Organization> findAllOrganizations(Organization condition);

    /**
     * 高级查询根组织架构
     * @param condition
     * @return
     */
    List<Organization> findOrganizationByCondition(Organization condition);

    /**
     * 关键字查询根组织架构
     * @param keyword
     * @return
     */
    List<Organization> findOrganizationByKeyWords(String keyword);

    /**
     * 关键字查询根组织架构
     * @param id
     * @return
     */
    List<Organization> selectOrganByCurrentOrgOid(String id);

    /**
     * 组织架构分页
     * @param condition
     * @return
     */
    R getRecursionOrgByLevel(@RequestBody Organization condition);

    /**
     * 删除组织
     * @param condition
     * @return R
     */
     R removeOrg(@RequestBody Organization condition);

    /**
     * 组织启用/停用
     * @param condition
     * @return R
     */
     R startOrStopOrg(@RequestBody Organization condition);

    /**
     * 默认加载展示2级组织架构
     * @param condition
     * @return R
     */
    R findOrganization2Level(@RequestBody Organization condition);

    /**
     * 分页查询组织架构
     * @param page 分页对象
     * @param organization
     * @return
     */
    Page findOrgPage(Page page, Organization organization);


    /**
     * 编辑更新组织
     * @param organization
     * @return R
     */
    @SysLog("编辑更新组织")
    R updateOrg(@RequestBody Organization organization);
}
