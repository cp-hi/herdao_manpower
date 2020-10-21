

package net.herdao.hdp.manpower.mpclient.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.herdao.hdp.manpower.mpclient.dto.OrgChartDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffOrgDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.vo.OrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationFormVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationTreeVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationVO;

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
     * 递归查询组织树
     * @return
     */
    List<OrganizationTreeVO> selectOrganizationTree();
    
    /**
     * 模糊查询组织树
     * 
     * @param orgCode
     * @param searchText
     * @return
     */
    List<OrganizationTreeVO> organizationTreeList(@Param("orgCode") String orgCode, @Param("searchText") String searchText);
    
    

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

    /**
     * 组织列表分页查询
     * 
     * @param page
     * @param orgCode
     * @param stop
     * @param searchText
     * @return
     */
	Page<OrganizationVO> findOrgPage(Page<OrganizationVO> page, @Param("orgCode") String orgCode, @Param("stop") Integer stop, @Param("searchText") String searchText);

    /**
     * 查询部门结构树
     * @return
     */
    List<Organization> fetchDeptTree(Organization condition);

    /**
     * 查询组织架构详情
     * 
     * @return
     */
    OrganizationFormVO findOrgDetails(@Param("id") Long id);
     
     /**
 	 * 查询部门/组织信息
 	 * 
 	 * @param searchText
 	 * @return
 	 */
     List<OrganizationComponentVO> selectOrganizations(@Param("searchText") String searchText);

    /**
     * 查询组织下所有人员
     *
     * @param map
     * @return
     */
     List<StaffOrgDTO> selectOrgStaffAll(Map<String, Object> map);

    /**
     * 查询组织架构图根节点
     *
     * @param id
     * @return
     */
    OrgChartDTO selectOrgChartRoot(Long id);

    /**
     * 查询组织架构图子节点
     *
     * @param id
     * @return
     */
    List<OrgChartDTO> selectOrgChartChild(Long id);
    
    /**
     * @description 获取组织编码
     * 
     * @author      shuling
     * @date        2020-10-18 10:37:22
     * @param 		parentId
     * @return
     */
    String getMaxOrgCode(@Param("parentId") Long parentId);
    
    /**
     * @description 更新组织编码
     * 
     * @author      shuling
     * @date        2020-10-18 12:31:28
     * @param 		orgCode
     * @return
     */
    List<Organization> selectOrganizationByOrgCode(@Param("orgCode") String orgCode);
}
