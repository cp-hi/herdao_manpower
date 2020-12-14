package net.herdao.hdp.manpower.mpclient.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.common.data.datascope.EhrDataScope;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.*;
import net.herdao.hdp.manpower.mpclient.entity.StaffEntrypostApprove;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.StaffCodePrefixVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 录用审批表
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@Mapper
public interface StaffEntrypostApproveMapper extends BaseMapper<StaffEntrypostApprove> {

    /**
     * 录用审批-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @param status 状态：1 填报中，2 审批中，3 已审批
     * @param ehrDataScope 数据权限过滤
     * @return
     */
    Page<EntryApproveDTO> findApprovePage(Page<EntryApproveDTO> page, @Param("orgId") String orgId, @Param("searchText") String searchText,@Param("status") String status);

    /**
     * 录用审批-流程审批-详情
     * @param id id
     * @return
     */
    EntryApproveFormDTO findApproveDetails(@Param("id") Long id);

    /**
     * 查询录用审批表的工号
     * @param idnumber 身份证号码
     * @return
     */
    List<StaffEntrypostApprove> getStaffCode(@Param("idnumber") String idnumber);

    /**
     * 获取员工工号前缀
     * @param groupCode 集团code
     * @param deptCode 部门code
     * @return
     */
    StaffCodePrefixVO getStaffCodePrefix(@Param("groupCode") String groupCode,@Param("deptCode") String deptCode);

    /**
     * 获取最大员工工号+1
     * @param staffCodeHead
     * @return
     */
    StaffCodePrefixVO getMaxStaffCodeAddOne(@Param("staffCodeHead") String staffCodeHead);

    /**
     * 入职管理-待入职-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @param ehrDataScope 数据过滤
     * @return
     */
    Page<EntryDTO> findEntryPage(Page<EntryDTO> page, @Param("orgId") String orgId, @Param("searchText") String searchText);

    /**
     * 入职管理-已入职-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @return
     */
    Page<EntryDTO> findInJobPage(Page<EntryDTO> page, @Param("orgId") String orgId, @Param("searchText") String searchText);

    /**
     * 入职管理-邀请入职登记-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @return
     */
    Page<EntryDTO> findEntryInvitePage(Page<EntryDTO> page, @Param("orgId") String orgId, @Param("searchText") String searchText);

    /**
     * 入职登记记录-未提交 已提交 已确认-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param entryCheckStatus 入职登记状态 (1:已提交，2：已提交，3：已确认）
     * @param searchText 关键字
     * @return EntryRegisterDTO
     */
    Page<EntryRegisterDTO> findEntryRegisterPage(Page<EntryRegisterDTO> page, @Param("orgId") String orgId,@Param("entryCheckStatus") String entryCheckStatus, @Param("searchText") String searchText);

    /**
     * 入职管理-办理入职-个人信息
     * @param recruitmentId 人才ID
     * @return EntryPersonInfoDTO
     */
    EntryPersonInfoDTO findEntryPersonInfo(@Param("recruitmentId") String recruitmentId);

    /**
     * 入职管理-办理入职-入职信息
     * @param id 主键ID
     * @return EntryJobDTO
     */
    EntryJobDTO findEntryJobInfo(@Param("id") String id);

    /**
     * 录用审批-填报中-修改-通过主键ID获取详情
     * @param id id
     * @return EntryJobDTO
     */
    EntryApproveUpdateDTO findEntryJobEditInfoById(@Param("id") Long id);

    /**
     * 录用审批-导出excel
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @param status 状态：1 填报中，2 审批中，3 已审批
     * @return
     */
    List<EntryApproveDTO> exportApprove(@Param("orgId") String orgId, @Param("searchText") String searchText,@Param("status") String status);

}
