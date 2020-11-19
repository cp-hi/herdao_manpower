package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import net.herdao.hdp.manpower.sys.vo.OperationLogVO;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <T> 实体类Entity类型
 * @param <D> 分页类VO，用于列表展示及列表导出
 * @param <F> 表单页Form类型，用于新增修改
 * @ClassName NewBaseController
 * @Description NewBaseController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 18:10
 * @Version 1.0
 */
@Slf4j
public class BaseController<T, D, F> {

    protected EntityService getEntityService() {
        throw new NotImplementedException("此方法未实现，请在各自的Controller中实现此方法");
    }

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
    protected Class<D> getListVOClass() {
        Class<D> clazz = (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }

    /**
     * 表单编辑用的FormDTO
     *
     * @return
     */
    protected Class<F> getFormVOClass() {
        Class<F> clazz = (Class<F>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[2];
        return clazz;
    }

    //endregion

    @ApiOperation(value = "获取操作记录")
    @GetMapping("/operationLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objId", value = "所需要查询记录实体的ID"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载，下载时把上一个返回的total当成size传递"),
    })
    public R<IPage<OperationLogVO>> getOperationLogs(HttpServletResponse response, @ApiIgnore Page page, Long objId, Integer type) throws Exception {
        IPage p = getEntityService().getOperationLogs(page, objId);
        List<OperationLogVO> operationLogVOList = DtoConverter.dto2vo(p.getRecords(), OperationLogVO.class);
        p.setRecords(operationLogVOList);
        if (null != type && Integer.valueOf(1).equals(type)) {
            String excelName = this.getEntityService().getEntityName() + "操作记录列表下载";
            ExcelUtils.export2Web(response, excelName, excelName, OperationLog.class, p.getRecords());
        }
        return R.ok(p);
    }

    protected R<IPage<D>> page(HttpServletResponse response, Page page, T t, Integer type)
            throws Exception {
        IPage p = getEntityService().page(page, t);
        List<D> vos = DtoConverter.dto2vo(p.getRecords(), getListVOClass());
        p.setRecords(vos);
        if (null != type && Integer.valueOf(1).equals(type)) {
            String excelName = this.getEntityService().getEntityName() + "列表下载";
            ExcelUtils.export2Web(response, excelName, excelName, getListVOClass(), vos);
        }
        return R.ok(p);
    }

    @SneakyThrows
    @GetMapping("/form/{id}")
    @ApiOperation(value = "获取表单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public R<F> getFormInfo(@PathVariable Long id) {
        try {
            Object from = getEntityService().form(id);
            if (null == from) throw new Exception("对象不存在，或已被删除");
            F f = DtoConverter.dto2vo(from, getFormVOClass());
            return R.ok(f);
        }catch (Exception ex){
            log.error("获取表单异常",ex);
            return R.failed(ex.getCause().getMessage());
        }
    }

    @ApiOperation(value = "通过id删除")
    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(getEntityService().delEntity(id));
    }

    @ApiOperation(value = "启用/停用")
    @PostMapping("/stop/{id}/{stop}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键"),
            @ApiImplicitParam(name = "stop", value = "0：启用；1：停用"),
    })
    public R<Boolean> stop(@PathVariable Long id, @PathVariable boolean stop) {
        return R.ok(getEntityService().stopEntity(id, stop));
    }

    @ApiOperation(value = "查看是否停用，返回true是停用，false启用")
    @PostMapping("/status/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键"),
    })
    public R<Boolean> getStatus(@PathVariable Long id) {
        return R.ok(getEntityService().getStatus(id));
    }

    @PostMapping
    @ApiOperation(value = "新增/修改")
    public R<F> save(@RequestBody F f) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            Object t = getEntityClass().newInstance();
            BeanUtils.copyProperties(f, (T) t);
            getEntityService().saveVerify((T) t);
            getEntityService().saveEntity((T) t);
            BeanUtils.copyProperties((T) t, f);
            return R.ok(f);
        } catch (Exception ex) {
            log.error("save异常",ex);
            return R.failed(ex.getCause().getMessage());
        }
    }
}
