package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.*;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.OKJobLevleSys;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.service.OKJobLevleSysService;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelBatchVO;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelFormVO;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelListVO;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.ShortJobLevelVO;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName JobLevelController
 * @Description JobLevelController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 19:06
 * @Version 1.0
 */


@RestController
@RequestMapping("/client/jobLevel")
@Api(tags = "职级管理")
public class JobLevelController extends BaseController<JobLevel, JobLevelListVO, JobLevelFormVO, JobLevelBatchVO> {

    @Autowired
    private JobLevelService jobLevelService;


    @Autowired
    public void setEntityService(JobLevelService jobLevelService) {
        super.entityService = jobLevelService;
    }

    @Override
    protected Class getBatchUpdateClass() {
        return JobLevelBatchVO.class;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(jobLevelService.jobLevelList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobLevelName", value = "搜索字符串"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载，下载时把上一个返回的total当成size传递"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<JobLevelListVO>> page(HttpServletResponse response, @ApiIgnore Page page, JobLevel jobLevel, Integer type)
            throws Exception {
        return super.page(response, page, jobLevel, type);
    }

    @Autowired
    private OKJobLevleSysService okJobLevleSysService;

    @GetMapping("/okpage")
    @ApiOperation(value = "一键职级系统列表", notes = "一键职级系统列表")
    public R<List<OKJobLevleSysDTO>> okpage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<OKJobLevleSys> jobLevleSys = okJobLevleSysService.list();
        List<OKJobLevleSysDTO> data = DtoConverter.dto2vo(jobLevleSys, OKJobLevleSysDTO.class);
        return R.ok(data);
    }

    @GetMapping("/okJobLevelDetail")
    @ApiOperation(value = "获取职级系统详情", notes = "获取职级系统详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "职级体系ID"),
    })
    public R<OKJobLevleSysDetailDTO> okJobLevelDetail(Long id) {
        OKJobLevleSysDTO okJobLevleSysDTO = okJobLevleSysService.findDetail(id);
        OKJobLevleSysDetailDTO detailDTO = new OKJobLevleSysDetailDTO();
        BeanUtils.copyProperties(okJobLevleSysDTO, detailDTO);
        for (OKJobGradeDTO jobGradeDTO : okJobLevleSysDTO.getOkJobGradeDTOList()) {
            String jobGradeName = jobGradeDTO.getJobGradeName();
            String jobLevelName = "";
            for (OKJobLevelDTO jobLevelDTO : jobGradeDTO.getOkJobLevelDTOList())
                jobLevelName += "、" + jobLevelDTO.getJobLevelName();
            jobLevelName = jobLevelName.replaceFirst("、", "");
            detailDTO.getShortJobLevelVOList().add(ShortJobLevelVO.builder()
                    .jobGradeName(jobGradeName).jobLevelName(jobLevelName).build());
        }
        return R.ok(detailDTO);
    }

    @GetMapping("/okCreateJobLevel")
    @ApiOperation(value = "一键创建职级系统", notes = "一键创建职级系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "职级体系ID"),
    })
    public R okCreateJobLevel( Long id) {
        try {
            okJobLevleSysService.okCreateJobLevel(id);
        } catch (Exception ex) {
            return R.failed(ex.getMessage());
        }
        return R.ok();
    }
}
