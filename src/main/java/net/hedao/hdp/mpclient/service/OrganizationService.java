package net.hedao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import net.hedao.hdp.mpclient.entity.Organization;
import net.hedao.hdp.mpclient.entity.Userpost;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
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
     * 查询根组织架构
     * @param condition
     * @return
     */
    List<Organization> findRootOrganizations(Organization condition);


    /**
     * 组织架构分页
     * @param condition
     * @return
     */
    List<Organization> findOrgPage(Organization condition);


    /**
     * 组织架构分页
     * @param condition
     * @return
     */
    R getRecursionOrgByLevel(Page page, @RequestBody Organization condition);

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
     void startOrStopOrg(@RequestBody Organization condition);

    /**
     * 默认加载展示2级组织架构
     * @param condition
     * @return R
     */
    R findOrganization2LevelByCondition(@RequestBody Organization condition);
}
