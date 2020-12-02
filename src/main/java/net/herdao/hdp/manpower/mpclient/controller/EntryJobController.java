package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveFormDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * 入职管理
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/entryJob" )
@Api(value = "entryJob", tags = "入职管理")
public class EntryJobController {

    private final StaffEntrypostApproveService staffEntrypostApproveService;




}
