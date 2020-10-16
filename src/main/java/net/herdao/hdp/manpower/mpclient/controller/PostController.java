package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostStaffDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.post.PostVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
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
@RequestMapping("/client/post")
@Api(tags = "岗位管理")
public class PostController extends BaseController<Post, Post> {

    @Autowired
    private PostService entityService;

    @Autowired
    public void setEntityService(PostService postService) {
        super.entityService = postService;
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
    public R page(Page<PostDTO> page, @RequestParam Map<String, String> params) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        IPage<PostDTO> p = entityService.page(page, "");
        List<PostVO> vos = DtoConverter.convert(p.getRecords(), PostVO.class);
//        p.setRecords();
        return R.ok(p);
//        PostVO postVO = DtoConverter.convert(p.getRecords().get(0), PostVO.class);
//        return R.ok(postService.page(page, params));
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
    })
    public R list(Long groupId) {
        return R.ok(entityService.postList(groupId));
    }


    @ApiOperation(value = "岗位概况")
    @SysLog("岗位概况")
    @GetMapping("/postStaff/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位ID"),
    })
    public R getPostStaffInfo(@PathVariable Long id) {
        return R.ok(entityService.getPostStaffInfo(id));
    }

    @ApiOperation(value = "获取岗位信息明细")
    @GetMapping("/postDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位ID，必填"),
            @ApiImplicitParam(name = "operation", value = "操作，不填写则直接获取数据，填 download 则下载excel"),
            @ApiImplicitParam(name = "size", value = "数据条数，不填则返回10条"),
    })
    public R getPostDetails(HttpServletResponse response, Long postId, String operation, String size) {
        List<PostDetailDTO> data = entityService.getPostDetails(postId, operation, size);
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
    @GetMapping("/postStaffs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位ID，必填"),
            @ApiImplicitParam(name = "operation", value = "操作，不填写则直接获取数据，填 download 则下载excel"),
            @ApiImplicitParam(name = "size", value = "数据条数，不填则返回10条"),
    })
    public R getPostStaffs(HttpServletResponse response, Long postId, String operation, String size) {
        List<PostStaffDTO> data = entityService.getPostStaffs(postId, operation, size);
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
