package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

/**
 * @ClassName BaseController
 * @Description BaseController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/15 12:42
 * @Version 1.0
 */

public class BaseController<T> {

    EntityService entityService;

    @Autowired
    OperationLogService operationLogService;

    @ApiOperation(value = "获取实体类操作日志")
    @GetMapping("/operationLog/{objId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objId", value = "ID"),
    })
    public R getOperationLogs(@PathVariable Long objId) {
        Class<T> clzz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return R.ok(operationLogService.findByEntity(objId, clzz.getName()));
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
//    @ApiResponses({
//            @ApiResponse()
//    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R getById(@PathVariable("id") Long id) {
        return R.ok(entityService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody T t)  {
        entityService.saveEntity(t);
        return R.ok(t);
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @DeleteMapping("/{id}")
    @OperationEntity(operation = "逻辑删除", clazz = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R removeById(@PathVariable Long id) {
        return R.ok(entityService.removeById(id));
    }

}
