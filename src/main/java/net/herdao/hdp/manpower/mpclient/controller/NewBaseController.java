package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.listener.NewImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.NewEntityService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <T> 实体类Entity类型
 * @param <D> 分页类DTO，用于列表展示及列表导出
 * @param <F> 表单页Form类型，用于新增修改
 * @param <E> excel导入类
 * @ClassName NewBaseController
 * @Description NewBaseController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 18:10
 * @Version 1.0
 */
public class NewBaseController<T, D, F, E> {

    NewEntityService newEntityService;

    @Autowired
    OperationLogService operationLogService;

    //region 各种Class

    /**
     * 实体类型
     *
     * @return
     */
    protected Class<T> getEntityClass() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    /**
     * 分页用的DTO类
     *
     * @return
     */
    protected Class<D> getDTOClass() {
        Class<D> clazz = (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }

    /**
     * 表单编辑用的FormDTO
     *
     * @return
     */
    protected Class<F> getFormClass() {
        Class<F> clazz = (Class<F>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[2];
        return clazz;
    }

    /**
     * 批量导入、修改所用的类
     *
     * @return
     * @Author ljan
     */
    protected Class getImportClass() {
        Class<E> clazz = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[3];
        return clazz;
    }
    //endregion

    @ApiOperation(value = "获取操作记录")
    @GetMapping("/operationLog/{objId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objId", value = "所需要查询记录实体的ID"),
    })
    public R<List<OperationLog>> getOperationLogs(@PathVariable Long objId) {
        return R.ok(operationLogService.findByEntity(objId, getEntityClass().getName()));
    }

    public R page(HttpServletResponse response, Page page, T t, Integer type)
            throws Exception {
        IPage p = newEntityService.page(page, t);
        List<D> vos = DtoConverter.dto2vo(p.getRecords(), getDTOClass());
        p.setRecords(vos);
        if (null != type && 1 == type)
            ExcelUtils.export2Web(response, "列表下载", "列表下载", getDTOClass(), vos);
        return R.ok(p);
    }

    @ApiOperation(value = "通过id删除")
    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R delete(@PathVariable Long id) {
        return R.ok(newEntityService.delEntity(id));
    }

    @ApiOperation(value = "启用/停用")
    @PostMapping("/stop/{id}/{stop}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
            @ApiImplicitParam(name = "stop", value = "0：启用；1：停用"),
    })
    public R stop(@PathVariable Long id, @PathVariable boolean stop) throws IllegalAccessException {
        return R.ok(newEntityService.stopEntity(id, stop));
    }

    @ApiOperation(value = "查看是否停用")
    @PostMapping("/status/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R getStatus(@PathVariable Long id) throws IllegalAccessException {
        return R.ok(newEntityService.getStatus(id));
    }

    @PostMapping
    @ApiOperation(value = "新增/修改")
    public R<F> save(@RequestBody F f) throws ClassNotFoundException {
        Class<E> e = (Class<E>) Class.forName(getEntityClass().getName());
        BeanUtils.copyProperties(f, e);
        newEntityService.saveEntity(e);
        return R.ok(f);
    }

    @GetMapping("/form/{id}")
    @ApiOperation(value = "获取表单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public R<F> getFormInfo(@PathVariable Long id)
            throws InstantiationException, IllegalAccessException {
        T t = (T) newEntityService.getById(id);
        F f = getFormClass().newInstance();
        BeanUtils.copyProperties(t, f);
        return R.ok(f);
    }

    @ApiOperation("批量新增/修改")
    @SysLog("批量新增/修改")
    @PostMapping("/import")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "type", value = "操作类型，0:批量新增 1:批量修改"),
    })
    public R importData(HttpServletResponse response,
                        @RequestParam(value = "file") MultipartFile file,
                        Integer importType) throws Exception {
        NewImportExcelListener listener = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            listener = new NewImportExcelListener<T,E>(newEntityService, importType);
            EasyExcel.read(inputStream, getImportClass(), listener).sheet().doRead();
            return R.ok(" easyexcel读取上传文件成功，上传了" + listener.getExcelList().size() + "条数据");
        } catch (Exception ex) {
            ExcelUtils.export2Web(response, "导入错误信息", "导入错误信息", getImportClass(), listener.getExcelList());
            return R.failed(ex.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
