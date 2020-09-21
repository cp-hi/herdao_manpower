package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.mpclient.entity.Pipeline;
import net.herdao.hdp.mpclient.entity.PostSeq;
import net.herdao.hdp.mpclient.service.PipelineService;
import net.herdao.hdp.mpclient.service.PostSeqService;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PostSeqController
 * @Description 岗位序列
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:20
 * @Version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client/postSeq")
public class PostSeqController {

    private final PostSeqService postSeqService;

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list() {
        return R.ok(postSeqService.postSeqList());
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(Page<PostSeq> page, String searchTxt) {
        return R.ok(postSeqService.page(page,searchTxt));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable Long id) {
        return R.ok(postSeqService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody PostSeq postSeq) throws Exception {
        postSeqService.saveOrUpdate(postSeq);
        return R.ok(postSeq);
    }

    @ApiOperation(value = "通过id删除岗位序列", notes = "通过id删除")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        PostSeq postSeq= postSeqService.getById(id);
        return R.ok(postSeq.logicDel(true));
    }
}
