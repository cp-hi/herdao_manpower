package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.vo.post.*;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
public class PostController extends NewBaseController<Post, PostListVO, PostFormVO, PostBatchUpdateVO> {

    @Override
    protected Class getBatchAddClass() {
        return PostBatchAddVO.class;
    }

    @Autowired
    private PostService postService;

    @Autowired
    public void setEntityService(PostService postService) {
        super.entityService = postService;
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postName", value = "字符串搜索"),
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
            @ApiImplicitParam(name = "jobLevelId1", value = "职级ID"),
            @ApiImplicitParam(name = "sectionId", value = "板块ID"),
            @ApiImplicitParam(name = "pipelineId", value = "管线ID"),
            @ApiImplicitParam(name = "stop", value = "是否停用，0启用：1停用，不填查所有"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<PostListVO>> page(HttpServletResponse response, @ApiIgnore Page page, Post post, Integer type) throws Exception {
        return super.page(response, page, post, type);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "新增/修改")
    public R<PostFormVO> save(@RequestBody PostFormVO f) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String[] jobLevelIds = new String[0];
        if (StringUtils.isNotBlank(f.getJobLevelId()))
            jobLevelIds = f.getJobLevelId().split(",");

        if (jobLevelIds.length >= 1)
            f.setJobLevelId1(Long.valueOf(jobLevelIds[0]));
        else if (jobLevelIds.length >= 2)
            f.setJobLevelId2(Long.valueOf(jobLevelIds[1]));

        f.setSingleJobLevle(1 == jobLevelIds.length);
        return super.save(f);
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
    })
    public R<IPage<PostShortVO>> list(Long groupId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Post post = new Post();
        post.setGroupId(groupId);
        IPage p = postService.page(new Page(), post);
        List<PostShortVO> records = DtoConverter.dto2vo(p.getRecords(), PostShortVO.class);
        p.setRecords(records);
        return R.ok(p);
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
    public R getPostDetails(HttpServletResponse response, Long postId, String operation, String size) {
        List<PostDetailVO> data = postService.getPostDetails(postId, operation, size);
        if (!"download".equals(operation)) {
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

//    @GetMapping("/generateEntityCode")
//    public R generateEntityCode() throws IllegalAccessException {
//        String data = postService.generateEntityCode();
//        return R.ok(data);
//    }


}
