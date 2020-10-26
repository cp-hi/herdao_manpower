package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqBatchVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqFormVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqListVO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName PostSeqController
 * @Description 岗位序列
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/client/postSeq")
@Api(tags = "岗位序列管理")
public class PostSeqController extends NewBaseController<PostSeq, PostSeqListVO, PostSeqFormVO, PostSeqBatchVO> {

    @Autowired
    PostSeqService postSeqService;

    @Autowired
    public void setEntityService(PostSeqService postSeqService) {
        super.entityService = postSeqService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(postSeqService.postSeqList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postSeqName", value = "字符串搜索"),
            @ApiImplicitParam(name = "parentId", value = "父级ID，这是查找下级用的，所以查顶级不用传，查下级传自身ID"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<PostSeqListVO>> page(HttpServletResponse response, Page page, PostSeq seq, Integer type)
            throws Exception {
        return super.page(response, page, seq, type);
    }
}
