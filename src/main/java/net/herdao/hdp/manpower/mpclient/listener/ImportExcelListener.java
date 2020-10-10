package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
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
public class ImportExcelListener<T> extends AnalysisEventListener<T> {
    List<T> dataList = null;

    EntityService<T> entityService;

    public ImportExcelListener(EntityService<T> service) {
        dataList = new ArrayList<>();
        this.entityService = service;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        entityService.importVerify(t);
        dataList.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        entityService.saveBatch(dataList);
        dataList.clear();
    }
}
