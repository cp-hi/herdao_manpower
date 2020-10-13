package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import lombok.Getter;
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

    @Getter
    List<T> dataList = null;

    Integer BATCH_COUNT = 0;

    boolean hasError = false;

    @Setter
    EntityService<T> entityService;

    Integer importType = 0;

    protected ImportExcelListener() {
    }

    public ImportExcelListener(EntityService<T> service) {
        this(service, 50, 0);
    }

    public ImportExcelListener(EntityService<T> service, Integer importType) {
        this(service, 50, importType);
    }

    public ImportExcelListener(EntityService<T> service, Integer batchCount, Integer importType) {
        this.dataList = new ArrayList<>();
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
        this.importType = importType;
        this.hasError = false;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        try {
            entityService.importVerify(t, importType);
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
        //TODO 增加导出字段动态排序
        if (hasError) {
            throw new Exception("导入出现错误，请查看导错误原因");
        }

        //新增
        if (importType!=null && importType==0){
            this.entityService.saveList(dataList, BATCH_COUNT);
        }

        //编辑
        if (importType!=null && importType==1){
            this.entityService.updateList(dataList, BATCH_COUNT);
        }

    }
}
