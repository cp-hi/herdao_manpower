package com.hedao.hdp.mpclient.post.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hedao.hdp.mpclient.annotation.ReturnResult;
import com.hedao.hdp.mpclient.post.entity.Post;
import com.hedao.hdp.mpclient.post.service.PostService;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
public class PostController {

    @Autowired
    PostService postService;

    //    @ReturnResult
    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(Page page, Post post) {
        return R.ok(postService.page(page, Wrappers.query(post)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable("id") Integer id) {
        try {
            return R.ok(postService.getById(id));
        } catch (Exception ex) {
            return R.failed(ex.getMessage());
        }
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable String id) {
        Post post = new Post();
        return R.ok(postService.removeById(id));
    }

}
