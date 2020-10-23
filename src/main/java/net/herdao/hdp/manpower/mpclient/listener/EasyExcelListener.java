package net.herdao.hdp.manpower.mpclient.listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckResultDTO;
import net.herdao.hdp.manpower.mpclient.service.EasyExcelService;
import net.herdao.hdp.manpower.mpclient.validator.EasyExcelValidator;

/**
 * @description: easyExcel监听器
 * @author hdp
 * @date 2020-10-20 11:37:27
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EasyExcelListener <entity, excelCls>  extends AnalysisEventListener<excelCls> {

    //处理逻辑service
    private EasyExcelService<entity, excelCls> easyExcelService;

    private List<excelCls> list = new ArrayList<>();

    //excel对象的反射类
    private Class<excelCls> clazz;
    
    // 每隔500条存储数据库，然后清理list ，方便内存回收
    private int batchCount = 500;
    
    //成功结果集
    private List<excelCls> successList = new ArrayList<>();

    //失败结果集
    private List<ExcelCheckErrDTO<excelCls>> errList = new ArrayList<>();


    public EasyExcelListener(){}

    public EasyExcelListener(EasyExcelService<entity, excelCls> easyExcelService, Class<excelCls> clazz){
        this.easyExcelService = easyExcelService;
        this.clazz = clazz;
    }
    
    public EasyExcelListener(EasyExcelService<entity, excelCls> easyExcelService, Class<excelCls> clazz, int batchCount){
        this.easyExcelService = easyExcelService;
        this.clazz = clazz;
        this.batchCount = batchCount;
    }

    @Override
    public void invoke(excelCls t, AnalysisContext analysisContext) {
        String errMsg;
        try {
            //根据excel数据实体中的javax.validation + 正则表达式来校验excel数据
            errMsg = EasyExcelValidator.validateEntity(t);
        } catch (NoSuchFieldException e) {
            errMsg = "解析数据出错";
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(errMsg)){
            ExcelCheckErrDTO excelCheckErrDto = new ExcelCheckErrDTO(t, errMsg);
            errList.add(excelCheckErrDto);
        }else{
            list.add(t);
        }
        // 每batchCount 条处理一次
        if (list.size() > batchCount){
            //校验
            ExcelCheckResultDTO result = easyExcelService.checkImportExcel(list);
            successList.addAll(result.getSuccessDtos());
            errList.addAll(result.getErrDtos());
            list.clear();
        }
    }

    // 所有数据解析完成了 都会来调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ExcelCheckResultDTO result = easyExcelService.checkImportExcel(list);

        successList.addAll(result.getSuccessDtos());
        errList.addAll(result.getErrDtos());
        list.clear();
    }


    /**
      * @description: 校验excel头部格式，必须完全匹配
      * 
      * @param headMap 传入excel的头部（第一行数据）数据的index,name
      * @param context
      */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (clazz != null){
            try {
                Map<Integer, String> indexNameMap = getIndexNameMap(clazz);
                Set<Integer> keySet = indexNameMap.keySet();
                for (Integer key : keySet) {
                    if (StringUtils.isEmpty(headMap.get(key))){
                        throw new ExcelAnalysisException("解析excel出错，请传入正确格式的excel");
                    }
                    if (!headMap.get(key).equals(indexNameMap.get(key))){
                        throw new ExcelAnalysisException("解析excel出错，请传入正确格式的excel");
                    }
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    /**
      * @description: 获取注解里ExcelProperty的value，用作校验excel
      * 
      * @param clazz 
      */
    @SuppressWarnings("rawtypes")
    public Map<Integer,String> getIndexNameMap(Class clazz) throws NoSuchFieldException {
        Map<Integer,String> result = new HashMap<>();
        Field field;
        Field[] fields=clazz.getDeclaredFields();
        for (int i = 0; i <fields.length ; i++) {
            field=clazz.getDeclaredField(fields[i].getName());
            field.setAccessible(true);
            ExcelProperty excelProperty=field.getAnnotation(ExcelProperty.class);
            if(excelProperty!=null){
                int index = excelProperty.index();
                String[] values = excelProperty.value();
                StringBuilder value = new StringBuilder();
                for (String v : values) {
                    value.append(v);
                }
                result.put(index,value.toString());
            }
        }
        return result;
    }
}
