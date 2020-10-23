package net.herdao.hdp.manpower.mpclient.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckResultDTO;

/**
 * @description excel导入 service
 * 
 * @author      shuling
 * @date        2020-10-22 20:37:29
 * @param       <entity> 实体类
 * @param       <excelCls> excel导入类
 */
public interface EasyExcelService<entity, excelCls> extends IService<entity>{
	
	/**
     * @description: 校验方法
     * 
     * @param objects
     * @throws
     */
	ExcelCheckResultDTO checkImportExcel(List<excelCls> objects);

}
