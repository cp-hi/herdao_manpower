package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


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


public class NewImportExcelListener<E> extends AnalysisEventListener<E> {

    Class entityClass;

//    Class excelClass;

    @Getter
    List<E> excelList = null;

    List dataList = null;

    Integer BATCH_COUNT = 0;

    boolean hasError = false;

    @Setter
    EntityService entityService;

    Integer importType = 0;

    /**
     * @param service    服务类
     * @param importType 导入类型 0: 新增 1: 保存
     */
    public NewImportExcelListener(EntityService service, Integer importType) {
        this(service, 50, null == importType ? 0 : importType);
    }

    /**
     * @param service
     * @param batchCount 批量导入条数
     * @param importType
     */
    public NewImportExcelListener(EntityService service, Integer batchCount, Integer importType) {
        this.dataList = new ArrayList<>();
        this.excelList = new ArrayList<>();
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
        this.importType = importType;
        this.hasError = false;

        this.entityClass = (Class) ((ParameterizedType) entityService.getClass()
                .getSuperclass().getGenericSuperclass()).getActualTypeArguments()[1];

    }

    List<String> headExcel = new ArrayList<>();

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        headExcel.addAll(headMap.values());
    }

    @Override
    public void invoke(E excel, AnalysisContext analysisContext) {
        List<String> headClass = new ArrayList<>(AnnotationUtils.getExcelPropertyNames(excel));
        List<String> heads = new ArrayList<>();
        headExcel.forEach(h -> {
            if (-1 == headClass.indexOf(h))
                heads.add(h);
        });
        if (heads.size() > 0)
            throw new RuntimeException("导入模板表头不存在：" + StringUtils.join(heads));

        Object t = null;
        try {
            t = Class.forName(entityClass.getName()).newInstance();
            BeanUtils.copyProperties(excel, t);
            entityService.importVerify(t, excel, importType);
        } catch (Exception ex) {
            this.hasError = true;
            String errMsg = ex.getMessage();
            if (errMsg.startsWith("；"))
                errMsg = errMsg.replaceFirst("；", "");
            ((ExcelErrMsg) excel).setErrMsg(errMsg);
        }
        excelList.add(excel);
        dataList.add(t);
    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (hasError)
            throw new Exception("导入出现错误，请查看导错误原因");
        this.entityService.saveList(dataList, BATCH_COUNT);
    }
}
