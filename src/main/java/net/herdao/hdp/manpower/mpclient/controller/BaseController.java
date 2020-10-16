package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.JobLevelDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

/**
 * @ClassName BaseController
 * @Description <p>基础Controller类
 * 实现了 保存 删除 启用/停用 导入/导出 查询操作记录  等基础操作
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/15 12:42
 * @Version 1.0
 */
//TODO 实现导出功能
public class BaseController<T, D extends T> {

    EntityService entityService;

    @Autowired
    OperationLogService operationLogService;

    @ApiOperation(value = "获取实体类操作日志")
    @GetMapping("/operationLog/{objId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objId", value = "ID"),
    })
    public R getOperationLogs(@PathVariable Long objId) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return R.ok(operationLogService.findByEntity(objId, clazz.getName()));
    }


//    @ApiResponses({
//            @ApiResponse()
//    })
    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R getById(@PathVariable("id") Long id) {
        return R.ok(entityService.getById(id));
    }
    @PostMapping
    public R save(@RequestBody T t) {
        entityService.saveEntity(t);
        return R.ok(t);
    }

    @ApiOperation(value = "通过id删除")
    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R delete(@PathVariable Long id) {
        return R.ok(entityService.deleteEntity(id));
    }

    @ApiOperation(value = "启用/停用")
    @PostMapping("/stop/{id}/{stop}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
            @ApiImplicitParam(name = "stop", value = "0：启用；1：停用"),
    })
    public R stop(@PathVariable Long id, @PathVariable boolean stop) {
        return R.ok(entityService.stopEntity(id, stop));
    }

    @ApiOperation("导入")
    @SysLog("导入")
    @PostMapping("/import")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "type", value = "操作类型，0:批量新增 1:批量修改"),
    })
    public R importData(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, Integer importType) throws Exception {
        Class<D> clazz = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        ImportExcelListener listener = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            listener = new ImportExcelListener(entityService, importType);
            EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
            return R.ok(" easyexcel读取上传文件成功");
        } catch (Exception ex) {
            ExcelUtils.export2Web(response, "职级导入错误信息", "职级导入错误信息", clazz, listener.getDataList());
            return R.failed(ex.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
