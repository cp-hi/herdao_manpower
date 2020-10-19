package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.post.PostSeqDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostSeqFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostSeqListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class PostSeqController extends BaseController<PostSeq> {

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

    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postSeqName", value = "postSeqName"),
    })
    public R<IPage<PostSeqListDTO>> page(Page  page, PostSeq seq)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchFieldException {
        IPage p = postSeqService.page(page, seq);
        List<PostSeqListDTO> vos = DtoConverter.dto2vo(p.getRecords(), PostSeqListDTO.class);
        p.setRecords(vos);
        return R.ok(p);
    }


    @GetMapping("/formInfo/{id}")
    @ApiOperation(value = "表单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public R<PostSeqFormDTO> getFormInfo(@PathVariable Long id)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, NoSuchFieldException {
        IPage p = postSeqService.page(new Page(), new PostSeq(id));
        PostSeqFormDTO data = null;
        if (p.getRecords().size() > 0)
            data = DtoConverter.dto2vo(p.getRecords().get(0), PostSeqFormDTO.class);
        return R.ok(data);
    }

}
