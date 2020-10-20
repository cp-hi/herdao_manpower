package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostSeqFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostSeqListDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
public class PostSeqController extends NewBaseController<PostSeq,PostSeqListDTO,PostSeqFormDTO,Class> {

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postSeqName", value = "字符串搜索"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载"),
    })
    public R page(HttpServletResponse response, Page  page, PostSeq seq, Integer type)
            throws Exception {
        return super.page(response,page,seq,type);
    }
}
