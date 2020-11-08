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
import java.util.function.Function;
import java.util.stream.Collectors;


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
        this.dataList = new ArrayList<>();
        this.excelList = new ArrayList<>();
        this.entityService = service;
        this.BATCH_COUNT = batchCount;
        this.importType = importType;
        this.hasError = false;
    }

    /**
     * 来自excel文件表头，
     * 字段可能是batch update 和 batch add
     * 字段有可能多有可能少
     */
    List<String> headExcelFile = new ArrayList<>();

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (context.readRowHolder().getRowIndex() > 0) {
            headExcelFile.addAll(headMap.values());
            //排队掉错误信息字段
            headExcelFile.remove("错误信息");
        }
    }

    @SneakyThrows
    @Override
    public void invoke(E excel, AnalysisContext context) {
        //导入类的表头 E 类来自于BatchUpdateVO，此类字段比较多，属于大头，排队掉错误信息字段
        List<String> headExcelClass = AnnotationUtils.getExcelPropertyNames(excel, "错误信息");
        //来自多字段表头如何查找出不包含的少表头字段，则说明模板不对
        // 小头来遍历，大头来包含，headExcelClass是大头 headExcelFile是小头
        List<String> nonexistentHeads = headExcelFile.stream().filter(h ->
                //大表头包含小表头，并找出大表头也包含不了的部分
                !headExcelClass.contains(h)).collect(Collectors.toList());

        if (nonexistentHeads.size() > 0)
            throw new Exception("导入模板表头不存在：" + StringUtils.join(nonexistentHeads));

        Object t = null;
        try {
            t = this.entityService.getEntityClass().newInstance();
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
        this.entityService.saveList(dataList, importType);
    }
}
