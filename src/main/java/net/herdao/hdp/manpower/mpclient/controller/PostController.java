package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostStaffDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * @ClassName PostController
 * @Description PostController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:10
 * @Version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client/post")
@Api(tags = "岗位管理")
public class PostController {

    private final PostService postService;

    private final OperationLogService operationLogService;

    @ApiOperation(value = "获取岗位操作日志")
    @GetMapping("/operationLog/{objId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objId", value = "岗位ID"),
    })
    public R getOperationLogs(@PathVariable Long objId) {
        return R.ok(operationLogService.findByEntity(objId, Post.class.getName()));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchText", value = "字符串搜索"),
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
            @ApiImplicitParam(name = "jobLevel", value = "职级ID"),
            @ApiImplicitParam(name = "sectionId", value = "板块ID"),
            @ApiImplicitParam(name = "pipelineId", value = "管线ID"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
    })
    public R page(Page page, @RequestParam Map<String, String> params) {
        return R.ok(postService.page(page, params));
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
    })
    public R list(Long groupId) {
        return R.ok(postService.postList(  groupId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
//    @ApiResponses({
//            @ApiResponse()
//    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位ID"),
    })
    public R getById(@PathVariable("id") Long id) {
        return R.ok(postService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody Post post) throws Exception {
        postService.saveOrUpdate(post);
        return R.ok(post);
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/{id}")
    @OperationEntity(operation = "删除岗位", clazz = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位ID"),
    })
    public R removeById(@PathVariable Long id) {
        return R.ok(postService.removeById(id));
    }


    @ApiOperation(value = "获取岗位员工信息")
    @SysLog("获取岗位员工信息")
    @GetMapping("/postStaff/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位ID"),
    })
    public R getPostStaffInfo(@PathVariable Long id) {
        return R.ok(postService.getPostStaffInfo(id));
    }

    @ApiOperation(value = "获取岗位信息明细")
    @GetMapping("/getPostDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位ID，必填"),
            @ApiImplicitParam(name = "operation", value = "操作，不填写则直接获取数据，填 download 则下载excel"),
            @ApiImplicitParam(name = "size", value = "数据条数，不填则返回10条"),
    })
    public R getPostDetails(HttpServletResponse response, Long postId, String operation, String size) {
        List<PostDetailDTO> data = postService.getPostDetails(postId, operation, size);
        if ("download".equals(operation)) {
            try {
                ExcelUtils.export2Web(response, "岗位信息明细表", "岗位信息明细表", PostDetailDTO.class, data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
        return R.ok(data);
    }

    @ApiOperation(value = "获取岗位员工信息")
    @GetMapping("/getPostStaffs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位ID，必填"),
            @ApiImplicitParam(name = "operation", value = "操作，不填写则直接获取数据，填 download 则下载excel"),
            @ApiImplicitParam(name = "size", value = "数据条数，不填则返回10条"),
    })
    public R getPostStaffs(HttpServletResponse response, Long postId, String operation, String size) {
        List<PostStaffDTO> data = postService.getPostStaffs(postId, operation, size);
        if ("download".equals(operation)) {
            try {
                ExcelUtils.export2Web(response, "岗位员工信息细表", "岗位员工信息细表", PostStaffDTO.class, data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
        return R.ok(data);
    }
}
