package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.listener.NewImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.io.IOUtils;
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
 * @param <E> excel导入类，注意，批量新增批量修改所用字段有可能不同，
 *            所以可能会有不同的类，一般用大类继承小类，然后把大类填
 *            到这泛型参数上
 * @ClassName NewBaseController
 * @Description NewBaseController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 18:10
 * @Version 1.0
 */
public class BaseController<T, D, F, E> {

    EntityService entityService;

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
    protected Class getBatchUpdateClass() {
        Class<E> clazz = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[3];
        return clazz;
    }

    /**
     * 批量新增的类，默认返回批量编辑的类，
     * 如果字段不一样，可以在子类覆盖此方法
     *
     * @return
     */
    protected Class getBatchAddClass() {
        return getBatchUpdateClass();
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

    protected R<IPage<D>> page(HttpServletResponse response, Page page, T t, Integer type)
            throws Exception {
        IPage p = entityService.page(page, t);
        List<D> vos = DtoConverter.dto2vo(p.getRecords(), getDTOClass());
        p.setRecords(vos);
        if (null != type && Integer.valueOf(1).equals(type)) {
            String excelName = this.entityService.getEntityName() + "列表下载";
            ExcelUtils.export2Web(response, excelName, excelName, getDTOClass(), vos);
        }
        return R.ok(p);
    }

    @ApiOperation(value = "通过id删除")
    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
    })
    public R delete(@PathVariable Long id) {
        return R.ok(entityService.delEntity(id));
    }

    @ApiOperation(value = "启用/停用")
    @PostMapping("/stop/{id}/{stop}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键"),
            @ApiImplicitParam(name = "stop", value = "0：启用；1：停用"),
    })
    public R stop(@PathVariable Long id, @PathVariable boolean stop) throws IllegalAccessException {
        return R.ok(entityService.stopEntity(id, stop));
    }

    @ApiOperation(value = "查看是否停用，返回true是停用，false启用")
    @PostMapping("/status/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键"),
    })
    public R getStatus(@PathVariable Long id) throws IllegalAccessException {
        return R.ok(entityService.getStatus(id));
    }

    @PostMapping
    @ApiOperation(value = "新增/修改")
    public R<F> save(@RequestBody F f) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object t = Class.forName(getEntityClass().getName()).newInstance();
        BeanUtils.copyProperties(f, (T) t);
        entityService.saveVerify((T) t);
        entityService.saveEntity((T) t);
        BeanUtils.copyProperties((T) t, f);
        return R.ok(f);
    }

    @GetMapping("/form/{id}")
    @ApiOperation(value = "获取表单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public R<F> getFormInfo(@PathVariable Long id) throws InstantiationException,
            IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        T t = getEntityClass().newInstance();
        ((BaseEntity) t).setId(id);
        IPage p = entityService.page(new Page(), t);
        if (0 == p.getRecords().size())
            throw new RuntimeException("对象不存在，或已被删除");
        F f = DtoConverter.dto2vo(p.getRecords().get(0), getFormClass());
        return R.ok(f);
    }

    @ApiOperation("批量新增/编辑")
//    @SysLog("批量新增/编辑")
    @PostMapping("/import")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "importType", value = "操作类型，0:批量新增 1:批量修改"),
            @ApiImplicitParam(name = "downloadErrMsg", value = "下载错误信息，0或空不下载 1:下载"),
    })
    public R importData(HttpServletResponse response,
                        @RequestParam(value = "file") MultipartFile file,
                        Integer importType, Integer downloadErrMsg) throws Exception {
        NewImportExcelListener<E> listener = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            listener = new NewImportExcelListener(entityService, importType);
            EasyExcel.read(inputStream, getBatchUpdateClass(), listener).sheet().doRead();

        } catch (Exception ex) {
            if (Integer.valueOf(1).equals(downloadErrMsg)) {
                List data = null;
                Class clazz = null;
                if (Integer.valueOf(1).equals(importType)) {
                    data = listener.getExcelList();
                    clazz = getBatchUpdateClass();
                } else {
                    data = DtoConverter.dto2vo(listener.getExcelList(), getBatchAddClass());
                    clazz = getBatchAddClass();
                }
                ExcelUtils.export2Web(response, "导入错误信息", "导入错误信息", clazz, data);
            } else {
                throw ex;
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return R.ok(" easyexcel读取上传文件成功，上传了" + listener.getExcelList().size() + "条数据");

    }


    @ApiOperation("下载批量新增/编辑的模板")
//    @SysLog("下载批量新增/编辑的模板")
    @PostMapping("/downloadTempl")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importType", value = "模板类型，0:批量新增模板 1:批量编辑模板"),
    })
    public R getDownloadTempl(HttpServletResponse response, Integer importType) {
        try {
//            String title = entityService.getEntityName() + "批量新增模板";
            Class templClass = getBatchAddClass();
            if (Integer.valueOf(1).equals(importType)) {
//                title = entityService.getEntityName() + "批量编辑模板";
                templClass = getBatchUpdateClass();
            }

//            List<LinkedHashMap<String, String>> data = new ArrayList();
//            Field[] fields = templClass.getDeclaredFields();
//            LinkedHashMap<String, String> map = new LinkedHashMap();
//            for (Field field : fields) {
//                if (field.getName().equals("errMsg")) continue;
//                ExcelProperty excel = field.getAnnotation(ExcelProperty.class);
//                map.put(excel.value()[0], "");
//            }
//            data.add(map);
//
//            String desc = "" +
//                    "导入说明：\r\n" +
//                    "1、标红字段为必填\r\n" +
//                    "2、操作导入前请删除示例数据\r\n" +
//                    "3、上级组织名称请填写已启用的组织编码\r\n";

//            ExcelUtils.export2Web(response, title, desc, data);

            ExcelUtils.downloadTempl(response, templClass);

        } catch (Exception ex) {
            return R.failed(ex.getMessage());
        }
        return R.ok();
    }
}
