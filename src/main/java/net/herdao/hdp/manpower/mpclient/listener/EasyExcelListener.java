package net.herdao.hdp.manpower.mpclient.listener;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.validator.EasyExcelValidator;

/**
 * @description: easyExcel监听器
 * 
 * @author shuling
 * @date 2020-10-20 11:37:27
 */
@Data
@EqualsAndHashCode(callSuper=false)
@SuppressWarnings("all")
public class EasyExcelListener<E>  extends AnalysisEventListener<E> {

    // 失败结果集
    List<ExcelCheckErrDTO> errList = new ArrayList<>();

    // 处理逻辑service
    HdpService easyExcelService;

    // 导入数据集
    List<E> excelList = new ArrayList<>();

    // excel对象的反射类
    Class<E> entityClass;
    
    // 导入类型
    int importType;
    
    // excel 导入类型
    Class<E> clazz;
    
    // 数据开始行号 用于数据校验
    int excelIndex = 1;

	public EasyExcelListener(HdpService easyExcelService, Class<E> clazz, Integer importType) {
		this.importType = importType;
		this.easyExcelService = easyExcelService;
		this.clazz = clazz;
		setEntityClass();
	}
	
	public EasyExcelListener(HdpService easyExcelService, Class<E> clazz, Integer importType, Integer excelIndex) {
		this.importType = importType;
		this.easyExcelService = easyExcelService;
		this.clazz = clazz;
		this.excelIndex = excelIndex;
		setEntityClass();
	}

	@Override
    public void invoke(E excelCls, AnalysisContext analysisContext) {
        String errMsg;
        try {
            // 根据excel数据实体中的javax.validation + 正则表达式来校验excel数据
            errMsg = EasyExcelValidator.validateEntity(excelCls);
        } catch (NoSuchFieldException e) {
            errMsg = "解析数据出错";
            e.printStackTrace();
        }
        // 判断导入的数据格式是否有问题
        if (!StringUtils.isEmpty(errMsg)){
            ExcelCheckErrDTO excelCheckErrDto = new ExcelCheckErrDTO(excelCls, errMsg);
            errList.add(excelCheckErrDto);
        }else{
        	excelList.add(excelCls);
        }
    }

    // 所有数据解析完成了 都会来调用
    @Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

		if (ObjectUtil.isEmpty(errList)) {
			// 业务校验
			List<ExcelCheckErrDTO> excelCheckRs = easyExcelService.checkImportExcel(excelList, importType);
			errList.addAll(excelCheckRs);
		}
	}

    /**
      * @description: 校验excel头部格式，必须完全匹配
      * 
      * @param headMap 传入excel的头部（第一行数据）数据的index, name
      * @param context
      */
    int currExcelIndex = 0;
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if(excelIndex <= currExcelIndex) {
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
        currExcelIndex ++;
    }

    /**
      * @description: 获取注解里ExcelProperty的value，用作校验excel
      * 
      * @param clazz 
      * @throws
      */
	public Map<Integer, String> getIndexNameMap(Class clazz) throws NoSuchFieldException {
		Map<Integer, String> result = new HashMap<>();
		Field field;
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			field = clazz.getDeclaredField(fields[i].getName());
			field.setAccessible(true);
			ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
			if (excelProperty != null) {
				int index = excelProperty.index();
				String[] values = excelProperty.value();
				StringBuilder value = new StringBuilder();
				for (String v : values) {
					value.append(v);
				}
				result.put(index, value.toString());
			}
		}
		return result;
	}
	
	public void setEntityClass() {
		Type genType = easyExcelService.getClass().getSuperclass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}
}
