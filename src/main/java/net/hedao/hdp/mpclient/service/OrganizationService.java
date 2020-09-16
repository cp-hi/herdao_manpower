package net.hedao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.hedao.hdp.mpclient.entity.Organization;

import java.util.List;

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
     * 停用根组织架构且（进行下级递归停用）
     * @param id
     * @return
     */
     void stopOrganById(Long id);

    /**
     * 启用当前组织架构
     * @param id
     * @return
     */
    void startOrganById(Long id);


    /**
     * 组织架构分页
     * @param condition
     * @return
     */
    List<Organization> findOrgPage(Organization condition);
}
