package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.vo.ExcelVO;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


/**
 * @param <T> 实体类型Entity
 * @param <E> excel类型
 * @ClassName NewImportExcelListener
 * @Description NewImportExcelListener
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 20:57
 * @Version 1.0
 */


public class NewImportExcelListener<T, E> extends AnalysisEventListener<E> {

    Class<T> entityClass;


    @Getter
    List<E> excelList = null;

    List<T> dataList = null;

    Integer BATCH_COUNT = 0;

    boolean hasError = false;

    @Setter
    EntityService<T> entityService;

    Integer importType = 0;


    /**
     * @param service    服务类
     * @param importType 导入类型 0: 新增 1: 保存
     */
    public NewImportExcelListener(EntityService<T> service, Integer importType){
        this(service, 50, null == importType ? 0 : importType);
    }

    /**
     * @param service
     * @param batchCount 批量导入条数
     * @param importType
     */
    public NewImportExcelListener(EntityService<T> service, Integer batchCount, Integer importType) {
        this.dataList = new ArrayList<>();
        this.excelList = new ArrayList<>();
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
        this.importType = importType;
        this.hasError = false;

//        Class clazz = entityService.getClass();
//        this.entityClass = (Class<T>) ((ParameterizedType) ((Class) clazz.getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments()[1];
        this.entityClass = (Class<T>) ((ParameterizedType) entityService.getClass()
                .getSuperclass().getGenericSuperclass()).getActualTypeArguments()[1];

    }


    @Override
    public void invoke(E excel, AnalysisContext analysisContext) {
        Object t = null;
        try {
            t = Class.forName(entityClass.getName()).newInstance();
            BeanUtils.copyProperties(excel, (T) t);
            entityService.importVerify((T) t, excel, importType);
        } catch (Exception ex) {
            this.hasError = true;
            ((ExcelVO) excel).setErrMsg(ex.getMessage());
        }
        excelList.add(excel);
        dataList.add((T) t);
    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (hasError)
            throw new Exception("导入出现错误，请查看导错误原因");
        this.entityService.saveList(dataList, BATCH_COUNT);
    }
}
