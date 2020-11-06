package net.herdao.hdp.manpower.mpclient.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartDTO;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationImportDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffOrgDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.vo.OrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationFormVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationTreeVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationVO;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
public interface OrganizationService extends HdpService<Organization>{

    /**
     * 查询子组织架构
     * @param parentId
     * @return
     */
     List<Organization> selectOrganizationListByParentOid(String parentId);

    /**
     * 递归查询组织树
     * 
     * @param stop 
     * @return
     */
    List<OrganizationTreeVO> selectOrganizationTree(Integer stop);
    
    /**
     * 模糊查询组织树
     * 
     * @param orgCode
     * @param stop
     * @param searchText
     * @return
     */
    List<OrganizationTreeVO> organizationTreeList(String orgCode, Integer stop, String searchText);

    /**
     * 查询部门结构树
     * @return
     */
    List<Organization> fetchDeptTree(Organization condition);


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
     * 
     * @param ids
     * @return
     */
    R removeOrg(String ids);

     
     /**
      * 预停用组织
      * 
      * @param id 主键
      * @param stopDateStr 停用日期 例如： 2020-10-19
      * @return
      */
     R expectedDisable(Long id, String stopDateStr);
     
     /**
      * 停用组织
      * 
      * @return
      */
     R disable();
     
     /**
      * 预停用组织
      * 
      * @param id 主键
      * @param startDateStr 停用日期 例如： 2020-10-19
      * @return
      */
     R expectedEnable(Long id, String startDateStr);
     
     /**
      * 停用组织
      * 
      * @return
      */
     R enable();
     
    /**
     * 默认加载展示2级组织架构
     * @param condition
     * @return R
     */
    R findOrganization2Level(@RequestBody Organization condition);

    /**
     * 组织列表分页查询
     * 
     * @param page
     * @param orgCode
     * @param stop
     * @param searchText
     * @return
     */
    Page<OrganizationVO> findOrgPage(Page<OrganizationVO> page, String orgCode, Integer stop, String searchText);

    /**
     * @description 新增、修改组织信息
     * @author      shuling
     * @date        2020-10-18 12:37:26
     * @param organization 组织信息
     * @return
     */
    R<Organization> saveOrUpdate(OrganizationVO organizationVO);

    /**
     * 查询组织架构详情
     * 
     * @param id
     * @return
     */
    OrganizationFormVO findOrgDetails(Long id);
    
    /**
     * 查询组织信息（组件）
     * 
     * @param orgCode
     * @param searchText
     * @return
     */
    R<List<OrganizationComponentVO>> selectOrganizations(String orgCode, String searchText);
    
    /**
     * 查询子组织信息（组件）
     * 
     * @param orgCode
     * @param searchText
     * @return
     */
    R<List<OrganizationComponentVO>> selectOrganizationComponentList(@Param("orgCode") String orgCode, @Param("searchText") String searchText);

    /**
     * 查询组织下所有人员
     * @author yangrr
     * @param page
     * @param orgCode
     * @return
     */
    List<StaffOrgDTO> selectOrgStaffAll(Page page, String orgCode);

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
     * 新增组织架构图
     *
     * @param form
     * @return boolean
     */
    boolean saveOrgChart(OrgChartFormDTO form);

    /**
     * 修改组织架构图
     *
     * @param form
     * @return boolean
     */
    boolean editOrgChart(OrgChartFormDTO form);
    
    /**
     * @description 查询所有组织信息
     * 
     * @author      shuling
     * @date        2020-10-22 13:37:14
     * @return
     */
    List<OrganizationImportDTO> selectAllOrganization();
    
}
