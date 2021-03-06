package net.herdao.hdp.manpower.mpclient.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.post.PostBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostDetailVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostFormVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostListVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostShortVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostStaffVO;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import net.herdao.hdp.manpower.sys.utils.ExceptionUtils;


/**
 * @ClassName PostController
 * @Description PostController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/client/post")
@Api(tags = "岗位管理")
public class PostController extends BaseController<Post, PostListVO, PostFormVO>
        implements ExcelImportController<PostBatchAddVO, PostBatchUpdateVO> {

    @Autowired
    private PostService postService;

    @Override
    public EntityService getEntityService() {
        return postService;
    }

    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postName", value = "字符串搜索"),
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
            @ApiImplicitParam(name = "jobLevelId1", value = "职级ID"),
            @ApiImplicitParam(name = "sectionId", value = "板块ID"),
            @ApiImplicitParam(name = "pipelineId", value = "管线ID"),
            @ApiImplicitParam(name = "stop", value = "是否停用，0启用：1停用，不填查所有"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数")
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<PostListVO>> page(Page page, Post post){
    	IPage p = postService.page(page, post);
        List<PostListVO> vos = DtoConverter.dto2vo(p.getRecords(), PostListVO.class);
        p.setRecords(vos);
        return R.ok(p);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "新增/修改")
    public R<PostFormVO> save(@RequestBody PostFormVO f) {
        try {
            String[] jobLevelIds = new String[0];
            if (StringUtils.isNotBlank(f.getJobLevelId()))
                jobLevelIds = f.getJobLevelId().split(",");

            if (jobLevelIds.length >= 1)
                f.setJobLevelId1(Long.valueOf(jobLevelIds[0]));
            if (jobLevelIds.length >= 2)
                f.setJobLevelId2(Long.valueOf(jobLevelIds[1]));

            f.setSingleJobLevle(1 == jobLevelIds.length);
            return super.save(f);
        } catch (Exception ex) {
            return R.failed(ExceptionUtils.getRealMessage(ex));
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
    })
    public R<List<PostShortVO>> list(Long groupId) throws InstantiationException, IllegalAccessException {
        List<PostDTO> list = postService.postList(groupId);
        List<PostShortVO> vos = DtoConverter.dto2vo(list, PostShortVO.class);
        return R.ok(vos);
    }

    @ApiOperation(value = "岗位概况")
    @SysLog("岗位概况")
    @GetMapping("/postStaff/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位ID"),
    })
    public R getPostStaffInfo(@PathVariable Long id) {
        return R.ok(postService.getPostStaffInfo(id));
    }

    @ApiOperation(value = "岗位报表-获取岗位信息明细")
    @GetMapping("/postDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位ID，必填"),
            @ApiImplicitParam(name = "operation", value = "操作，不填写则直接获取数据，填 download 则下载excel"),
            @ApiImplicitParam(name = "size", value = "数据条数，不填则返回10条，download则返回所有"),
    })
    public R getPostDetails(HttpServletResponse response, Long postId, String operation, String size)  {
        List<PostDTO> list = postService.getPostDetails(postId, operation, size);
        List<PostDetailVO> data = DtoConverter.dto2vo(list, PostDetailVO.class);
        if ("download".equals(operation)) {
            try {
                ExcelUtils.export2Web(response, "岗位信息明细表", "岗位信息明细表", PostDetailVO.class, data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
        return R.ok(data);
    }

    @ApiOperation(value = "岗位报表-获取岗位员工信息")
    @GetMapping("/postStaffs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位ID，必填"),
            @ApiImplicitParam(name = "operation", value = "操作，不填写则直接获取数据，填 download 则下载excel"),
            @ApiImplicitParam(name = "size", value = "数据条数，不填则返回10条，download则返回所有"),
    })
    public R getPostStaffs(HttpServletResponse response, Long postId, String operation, String size) {
        List<PostStaffVO> data = postService.getPostStaffs(postId, operation, size);
        if ("download".equals(operation)) {
            try {
                ExcelUtils.export2Web(response, "岗位员工信息细表", "岗位员工信息细表", PostStaffVO.class, data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
        return R.ok(data);
    }
}
