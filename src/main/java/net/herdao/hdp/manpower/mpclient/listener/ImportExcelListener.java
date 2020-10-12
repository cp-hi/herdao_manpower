package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import lombok.Setter;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.ion.Decimal;

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
    List<T> dataList = null;

    Integer BATCH_COUNT = 50;

    @Setter
    EntityService<T> entityService;

    public ImportExcelListener(EntityService<T> service) {
        this(service, 50);
    }

    public ImportExcelListener(EntityService<T> service, Integer batchCount) {
        dataList = new ArrayList<>();
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        entityService.importVerify(t);
        dataList.add(t);
    }

    /**
     * 分析完数据后整体保存
     * @Author ljan
     * @param analysisContext
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //分批保存，每批50条
        List<List<T>> batch = Lists.partition(dataList, BATCH_COUNT);
        for (List<T> tmp : batch) entityService.saveBatch(tmp);
        dataList.clear();
    }
}
