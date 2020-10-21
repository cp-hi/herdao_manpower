package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.*;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.listener.NewImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
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
public class PostController extends NewBaseController<Post, PostListDTO, PostFormDTO, PostBatchUpdateDTO> {

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
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(HttpServletResponse response, @ApiIgnore Page page, Post post, Integer type) throws Exception {
        return super.page(response, page, post, type);
    }

    @GetMapping("/baseInfo/{id}")
    @ApiOperation(value = "基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键"),
    })
    public R<PostBaseDTO> getBaseInfo(@PathVariable Long id)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, NoSuchFieldException {
        IPage p = postService.page(new Page(), new Post(id));
        PostBaseDTO data = null;
        if (p.getRecords().size() > 0)
            data = DtoConverter.dto2vo(p.getRecords().get(0), PostBaseDTO.class);
        return R.ok(data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
    })
    public R<IPage<PostShortDTO>> list(Long groupId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Post post = new Post();
        post.setGroupId(groupId);
        IPage p = postService.page(new Page(), post);
        List<PostShortDTO> records = DtoConverter.dto2vo(p.getRecords(), PostShortDTO.class);
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

    @ApiOperation(value = "岗位报表-获取岗位员工信息")
    @GetMapping("/postStaffs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位ID，必填"),
            @ApiImplicitParam(name = "operation", value = "操作，不填写则直接获取数据，填 download 则下载excel"),
            @ApiImplicitParam(name = "size", value = "数据条数，不填则返回10条，download则返回所有"),
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
