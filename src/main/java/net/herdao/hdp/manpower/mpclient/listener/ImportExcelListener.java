package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @param <E> excel类型
 * @ClassName NewImportExcelListener
 * @Description NewImportExcelListener
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 20:57
 * @Version 1.0
 */


public class ImportExcelListener<E> extends AnalysisEventListener<E> {

    Class entityClass;

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
    public ImportExcelListener(EntityService service, Integer importType) {
        this(service, 50, null == importType ? 0 : importType);
    }

    /**
     * @param service
     * @param batchCount 批量导入条数
     * @param importType
     */
    public ImportExcelListener(EntityService service, Integer batchCount, Integer importType) {
        this.entityClass = service.getEntityClass();
        this.dataList = new ArrayList<>();
        this.excelList = new ArrayList<>();
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
        this.importType = importType;
        this.hasError = false;
    }

    //excel表头
    List<String> headExcel = new ArrayList<>();

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (headMap.values().size() > 0 && context.readRowHolder().getRowIndex() > 0) {
            headExcel.addAll(headMap.values());
        }
    }

    @SneakyThrows
    @Override
    public void invoke(E excel, AnalysisContext context) {
        //导入类的表头
        List<String> headClass = AnnotationUtils.getExcelPropertyNames(excel, "errMsg");
        List<String> nonexistentHeads = new ArrayList<>();
        headExcel.forEach(h -> {
            if (-1 == headClass.indexOf(h))
                nonexistentHeads.add(h);
        });
        if (nonexistentHeads.size() > 0)
            throw new RuntimeException("导入模板表头不存在：" + StringUtils.join(nonexistentHeads));

        Object t = null;
        try {
            t = entityClass.newInstance();
            BeanUtils.copyProperties(excel, t);
            ((ExcelMsg) excel).setErrMsg("");
            entityService.importVerify(t, excel, importType);
        } catch (Exception ex) {
            this.hasError = true;
            String errMsg = ex.getMessage();
            if (null != errMsg && errMsg.startsWith("；"))
                errMsg = errMsg.replaceFirst("；", "");
            ((ExcelMsg) excel).setErrMsg(errMsg);
        }
        excelList.add(excel);
        dataList.add(t);
    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (hasError)
            throw new Exception("导入出现错误，请查看导错误原因");

        if (0 == importType) {
            //TODO 添加生成编码的逻辑
//            Integer currCode = Integer.valueOf(entityService.getCurrEntityCode());
//            for (int i = 0; i < dataList.size(); i++) {
//                String entityCode = String.format("%06d", ++currCode);
//                entityService.setEntityCode(dataList.get(i), entityCode);
//            }
        }
        this.entityService.saveList(dataList, BATCH_COUNT);
    }
}
