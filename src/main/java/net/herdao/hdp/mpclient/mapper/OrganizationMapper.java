

package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.mpclient.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author liang
 * @date 2020-09-09 15:31:20
 */
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {
    /**
     * 查询子组织架构
     * @param parentOid
     * @return
     */
    List<Organization> selectOrganizationListByParentOid(String parentOid);

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
     * 点击查看组织架构详情
     * @param keyword
     * @return
     */
    List<Organization> findOrganizationByKeyWords(String keyword);

    /**
     * 点击查看组织架构详情
     * @param keyword
     * @return
     */
    List<Organization> selectOrganByCurrentOrgOid(String keyword);

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
     * 查询无限级组织架构
     * @param condition
     * @return
     */
    List<Organization> findAllOrg(Organization condition);
}
