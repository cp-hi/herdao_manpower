package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.excelud.ExportDataVO;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

/**
 * @param <A> 批量新增类
 * @param <U> 批量导入类
 * @ClassName ExcelImportController
 * @Description ExcelImportController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/10 18:26
 * @Version 1.0
 */
public interface ExcelImportController<A, U> {

    EntityService getEntityService();

    /**
     * 批量新增的类
     *
     * @return
     */
    default Class getBatchAddClass() {
        Class<A> clazz = (Class<A>) ((ParameterizedType) getClass()
                .getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return clazz;
    }

    /**
     * 批量导入、修改所用的类
     *
     * @return
     * @Author ljan
     */
    default Class getBatchUpdateClass() {
        Class<U> clazz = (Class<U>) ((ParameterizedType) getClass()
                .getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return clazz;
    }

    @SneakyThrows
    @ApiOperation("批量新增/编辑")
    @PostMapping("/import")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "importType", value = "操作类型，0:批量新增 1:批量修改"),
            @ApiImplicitParam(name = "downloadErrMsg", value = "下载错误信息，0或空不下载 1:下载"),
    })
    default R importData(HttpServletResponse response,
                         @RequestParam(value = "file") MultipartFile file,
                         Integer importType, Integer downloadErrMsg) {
        InputStream inputStream = null;
        ImportExcelListener listener = null;
        Class clazz = null;
        try {
            inputStream = file.getInputStream();
            if (!Integer.valueOf(1).equals(importType)) {
                clazz = getBatchAddClass();
                listener = new ImportExcelListener<A>(getEntityService(), importType);
            } else {
                clazz = getBatchUpdateClass();
                listener = new ImportExcelListener<U>(getEntityService(), importType);
            }
            EasyExcel.read(inputStream, clazz, listener).sheet().headRowNumber(2).doRead();
        } catch (Exception ex) {
            if (Integer.valueOf(1).equals(downloadErrMsg) && listener.getExcelList().size() > 0)
                ExcelUtils.export2Web(response, "导入错误信息", clazz, listener.getExcelList());
            return R.failed(listener.getErrType(), ex.getCause().getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return R.ok(" easyexcel读取上传文件成功，上传了" + listener.getExcelList().size() + "条数据");
    }

    @ApiOperation("下载批量新增/编辑的模板")
    @PostMapping("/downloadTempl")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importType", value = "模板类型，0:批量新增模板 1:批量编辑模板"),
    })
    default R getDownloadTempl(HttpServletResponse response,
                               @RequestBody ExportDataVO exportDataVO) {
        try {
            Class templClass = getBatchAddClass();
            ApiModel apiModel = (ApiModel) templClass.getAnnotation(ApiModel.class);
            String excelName = apiModel.value();
            if (Integer.valueOf(1).equals(exportDataVO.getImportType())) {
                templClass = getBatchUpdateClass();
                excelName = apiModel.value().replaceFirst("批量新增", "批量编辑");
            }
            ExcelUtils.downloadTempl(response, templClass, excelName);
        } catch (Exception ex) {
            return R.failed(ex.getCause().getMessage());
        }
        return R.ok();
    }
}
