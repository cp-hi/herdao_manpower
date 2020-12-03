package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.*;
import net.herdao.hdp.manpower.mpclient.entity.StaffEntrypostApprove;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.StaffCodePrefixVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 录用审批表
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
public interface StaffEntrypostApproveService extends IService<StaffEntrypostApprove> {

    /**
     * 录用审批-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @param status 状态：1 填报中，2 审批中，3 已审批
     * @return
     */
    Page<EntryApproveDTO> findApprovePage(Page<EntryApproveDTO> page, String orgId,  String searchText,String status);

    /**
     * 录用审批-流程审批-详情
     * @param id id
     * @return
     */
    EntryApproveFormDTO findApproveDetails(Long id);

    /**
     * 录用审批-保存
     * @param approveAddDTO
     * @return
     */
    EntryApproveAddDTO saveApprove(@RequestBody EntryApproveAddDTO approveAddDTO);

    /**
     * 入职管理-待入职-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @return
     */
    Page<EntryDTO> findEntryPage(Page<EntryDTO> page, String orgId, String searchText);

    /**
     * 入职管理-已入职-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @return
     */
    Page<EntryDTO> findInJobPage(Page<EntryDTO> page, String orgId,  String searchText);

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
     * @param entryCheckStatus 入职登记记录 (1:已提交，2：已提交，3：已确认）
     * @param searchText 关键字
     * @return EntryRegisterDTO
     */
    Page<EntryRegisterDTO> findEntryRegisterPage(Page<EntryRegisterDTO> page, String orgId, String entryCheckStatus, String searchText);


}
