package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    boolean hasError = false;

    EntityService entityService;

    Integer importType = 0;

    //错误类型，1为数据错误，2为模板错误
    @Getter
    Integer errType = 1;

    /**
     * @param service    服务类
     * @param importType 导入类型 0: 新增 1: 保存
     */
    public ImportExcelListener(EntityService service, Integer importType) {
        this.dataList = new ArrayList<>();
        this.excelList = new ArrayList<>();
        this.entityService = service;
        this.importType = importType;
        this.hasError = false;
    }

    /**
     * 来自ExcelFile文件的表头，由于是用户上传行为，
     * 有可能用户会非法上传不符合表头的ExcelFile
     */
    List<String> headExcelFile = new ArrayList<>();

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (context.readRowHolder().getRowIndex() > 0) {
            headExcelFile.addAll(headMap.values());
            //排除掉错误信息字段
            headExcelFile.remove("错误信息");
        }
    }

    @SneakyThrows
    @Override
    public void invoke(E excel, AnalysisContext context) {
        //导入类的表头 E 类来自于ExcelClass的头部,headExcelClass的表头一定是正确的，排除"错误信息"字段
        List<String> headExcelClass = AnnotationUtils.getExcelPropertyNames(excel, "错误信息");
        //来自ExcelClass及ExcelFile应该一样
        //把两者进行比较，如果存在交集以外的字段，则说明模板不对应ExcelClass的头部
        //ExcelFile是用户随意上传的，所以ExcelClass不包含的ExcelFile字段就是非法字段
        List<String> nonexistentHeads = headExcelFile.stream().filter(h ->
                !headExcelClass.contains(h)).collect(Collectors.toList());

        if (nonexistentHeads.size() > 0) {
            errType = 2;
            throw new Exception("模板错误，导入模板表头不存在：" + StringUtils.join(nonexistentHeads));
        }

        Object t = null;
        try {
            t = this.entityService.getEntityClass().newInstance();
            BeanUtils.copyProperties(excel, t);
            entityService.importVerify(t, excel, importType);
        } catch (Exception ex) {
            this.hasError = true;
            ((ExcelMsg) excel).setErrMsg(ex.getCause().getMessage());
        }
        excelList.add(excel);
        dataList.add(t);
    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (hasError && excelList.size() > 0) {
            errType = 1;
            throw new Exception("导入出现错误，请查看导错误原因");
        }
        if (dataList.size() == 0) {
            errType = 2;
            throw new Exception("上传文件中并无数据");
        }
        this.entityService.saveList(dataList, importType);
    }
}
