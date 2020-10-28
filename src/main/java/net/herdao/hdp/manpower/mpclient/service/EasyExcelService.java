package net.herdao.hdp.manpower.mpclient.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;

/**
 * @description excel导入 service
 * 
 * @author      shuling
 * @date        2020-10-22 20:37:29
 * @param       T 实体对象
 */
public interface EasyExcelService<T> extends IService<T>{
	
	/**
	 * 导入校验
	 * 
	 * @param excelList 导入excel数据集
	 * @param importType 导入类型 0 新增， 1 修改
	 * @return
	 */
	List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType);

}
