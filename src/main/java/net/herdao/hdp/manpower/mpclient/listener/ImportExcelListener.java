package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import lombok.Setter;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.dto.ExcelDTO;
import net.herdao.hdp.manpower.mpclient.dto.JobLevelDTO;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.ion.Decimal;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ImportExcelListener
 * @Description ImportExcelListener
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/27 19:29
 * @Version 1.0
 */
public class ImportExcelListener<T> extends AnalysisEventListener<T> {
    public List<T> dataList = null;

    Integer BATCH_COUNT = 0;

    boolean hasError = false;

    @Setter
    EntityService<T> entityService;

    @Setter
    HttpServletResponse servletResponse;

    protected ImportExcelListener() {
    }

    public ImportExcelListener(HttpServletResponse response, EntityService<T> service) {
        this(response, service, 50);
    }

    public ImportExcelListener(HttpServletResponse response, EntityService<T> service, Integer batchCount) {
        this.dataList = new ArrayList<>();
        this.servletResponse = response;
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
        this.hasError = false;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        try {
            entityService.importVerify(t);
        } catch (Exception ex) {
            this.hasError = true;
            ((ExcelDTO) t).setErrMsg(ex.getMessage());
        }
        dataList.add(t);
    }

    /**
     * 分析完数据后整体保存
     *
     * @param analysisContext
     * @Author ljan
     */
    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        if (this.hasError) {
//            //TODO 完善错误说明
//            for (T t : dataList) {
//                Field[] fields = AnnotationUtils.getAllAnnotationFields(t, ExcelProperty.class);
//                ExcelProperty fieldErrMsg = AnnotationUtils.getAnnotationByFieldName(fields, "errMsg", ExcelProperty.class);
//                AnnotationUtils.setAnnotationInfo(fieldErrMsg, "index", fields.length);
//                System.out.println(fieldErrMsg.index());
//            }
//            throw new Exception("导入出现错误，请查看导错误原因");
//        }

        if (hasError) throw new Exception("导入出现错误，请查看导错误原因");
        this.entityService.saveList(dataList, BATCH_COUNT);
    }
}
