package net.herdao.hdp.manpower.mpclient.service;

import java.util.List;

import net.herdao.hdp.manpower.mpclient.dto.OrgChartFormDTO;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffOrgDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationVO;

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
     * 
     * @param page
     * @param orgCode
     * @param stop
     * @param searchText
     * @return
     */
    Page<Organization> findOrgPage(Page<Organization> page, String orgCode, Integer stop, String searchText);

    /**
     * @description 新增、修改组织信息
     * @author      shuling
     * @date        2020-10-18 12:37:26
     * @param organization 组织信息
     * @return
     */
    R<Organization> saveOrUpdateOrganization(OrganizationVO organizationVO);

    /**
     * 查询组织架构详情
     * @param condition
     * @return
     */
    Organization findOrgDetails(Organization condition);
    
    /**
 	 * 查询部门/组织信息
 	 * 
 	 * @return
 	 */
    R<?> selectOrganizations();

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
}
