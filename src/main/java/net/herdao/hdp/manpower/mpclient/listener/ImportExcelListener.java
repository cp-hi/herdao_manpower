package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;
import net.herdao.hdp.manpower.mpclient.service.EntityService;

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
@Deprecated
public class ImportExcelListener<T> extends AnalysisEventListener<T> {

    @Getter
    List<T> dataList = null;

    Integer BATCH_COUNT = 10;

    boolean hasError = false;

    @Setter
    EntityService<T> entityService;

    Integer importType = 0;

    /**
     * 要保存的实体类型
     *
     * @Author ljan
     */
    protected ImportExcelListener() {
    }

    public ImportExcelListener(EntityService<T> service, Integer importType) {
        this(service, 50, importType);
    }


    /**
     * @param service
     * @param batchCount 批量导入条数
     * @param importType
     */
    public ImportExcelListener(EntityService<T> service, Integer batchCount, Integer importType) {
        this.dataList = new ArrayList<>();
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
        this.importType = importType;
        this.hasError = false;
    }

    @Override
    public void invoke(T excel, AnalysisContext analysisContext) {
        try {
            entityService.importVerify(excel, importType);
        } catch (Exception ex) {
            this.hasError = true;
            ((ExcelErrMsg) excel).setErrMsg(ex.getMessage());
        }
        dataList.add(excel);
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
        if (hasError) {
            throw new Exception("导入出现错误，请查看导错误原因");
        }
        this.entityService.saveList(dataList, BATCH_COUNT);
    }
}
