package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.springframework.web.bind.annotation.*;

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
public class PostController   {

    private final PostService postService;

    private final     OperationLogService operationLogService;


    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchText", value = "字符串搜索"),
            @ApiImplicitParam(name = "groupIds", value = "集团编码"),
            @ApiImplicitParam(name = "jobLevels", value = "职级"),
            @ApiImplicitParam(name = "sectionCodes", value = "板块编码"),
            @ApiImplicitParam(name = "pipelineCodes", value = "管线编码"),
    })
    public R page(Page page, @RequestParam Map<String, String> params) {
        return R.ok(postService.page(page, params));
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list() {
        return R.ok(postService.postList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
//    @ApiResponses({
//            @ApiResponse()
//    })
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
    public R removeById(@PathVariable Long id) {
        return R.ok(postService.removeById(id));
    }


    @ApiOperation(value = "获取岗位员工信息")
    @SysLog("获取岗位员工信息")
    @GetMapping("/postStaff/{postId}")
    public R getPostStaffInfo(@PathVariable Long postId) {
        return R.ok(postService.getPostStaffInfo(postId));
    }

    @ApiOperation(value = "获取岗位操作日志")
    @GetMapping("/operationLog/{objId}")
    public R getOperationLogs(@PathVariable Long objId){
        return R.ok(operationLogService.findByEntity(objId,Post.class.getName()));
    }
}
