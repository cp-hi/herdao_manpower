package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.utils.DtoUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入工具类
 *
 * @author yangrr
 * @date 2020-10-20 18:10:29
 */
public class StaffExcelListener<T, V> extends AnalysisEventListener<T> {
    /**
     * 每隔500条存储数据库，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 500;
    private IService<V> service;
    private Class cls;
    List<V> dataList = new ArrayList<>();


    public StaffExcelListener(IService<V> service, Class cls){
        this.service = service;
        this.cls = cls;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        V entity = DtoUtils.transferObject(data, cls);
        dataList.add(entity);
        if (dataList.size() >= BATCH_COUNT) {
            saveData();
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        service.saveBatch(dataList);
    }
}
