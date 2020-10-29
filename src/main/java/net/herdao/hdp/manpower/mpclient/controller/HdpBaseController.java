package net.herdao.hdp.manpower.mpclient.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationImportDTO;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationUpdateErrDTO;
import net.herdao.hdp.manpower.mpclient.handler.EasyExcelSheetWriteHandler;
import net.herdao.hdp.manpower.mpclient.listener.EasyExcelListener;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.utils.EasyExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.upload.ImportDataVO;
/**
 * @description 处理增、删、改、查、批量新增、批量编辑（待完善）
 *
 * @author      shuling
 * @date        2020-10-28 08:37:22
 */
@SuppressWarnings("all")
public abstract class HdpBaseController {
	
	/**
	 * 业务处理service
	 * @return
	 */
	public abstract HdpService getHdpService();
	
	/**
	 * 批量新增需要重写该方法
	 * @return
	 */
	public Class getImportAddCls() {
		return null;
	}
	
	/**
	 * 批量新增异常处理需要重写该方法
	 * @return
	 */
	public Class getImportAddErrCls() {
		return null;
	}
	
	/**
	 * 批量编辑需要重写该方法
	 * @return
	 */
	public Class getImportUpdateCls() {
		return null;
	}
	
	/**
	 * 批量编辑异常需要重写该方法
	 * @return
	 */
	public Class getImportUpdateErrCls() {
		return null;
	}
	
	/**
	 * 导入数据开始行号，默认：1 需要调整请重写该方法
	 * @return
	 */
	public Integer getExcelIndex() {
		return 1;
	}
	
	/**
	 * 表头行号默认：2 需要调整请重写该方法
	 * @return
	 */
	public Integer getHeadRowNumber() {
		return 2;
	}
	
	/**
	 * 批量编辑模板导出数据集合
	 * 
	 * @return
	 */
	public List getDownloadUpdateTemplateList() {
		return null;
	}
	
	/**
	 * 批量新增、编辑说明信息 需要调整请重写该方法
	 * @return
	 */
	public String getExcelDescription() {
		return "";
	}
	
	/**
	 * 批量新增说明信息 需要调整请重写该方法
	 * @return
	 */
	public String getExcelAddDescription() {
		return "";
	}
	
	/**
	 * 批量新增说明信息 需要调整请重写该方法
	 * @return
	 */
	public String getExcelUpdateDescription() {
		return "";
	}
	
	/**
	 * 批量新增、编辑说明信息
	 * @return
	 */
	public String getExcelDescription(Integer importType) {
		String eds = (importType == 0 ? getExcelAddDescription() : getExcelUpdateDescription());
		if(StrUtil.isBlank(eds)) {
			return getExcelDescription();
		}
		return eds;
	}
	
	/**
	 * 获取批量新增、编辑 class
	 * 
	 * @param importType
	 * @return
	 */
	public Class getExcelCls(Integer importType) {
		return importType == 0 ? getImportAddCls() : getImportUpdateCls();
	}
	
	/**
	 * 获取批量新增、编辑异常 class
	 * 
	 * @param importType
	 * @return
	 */
	public Class getExcelErrCls(Integer importType) {
		return importType == 0 ? getImportAddErrCls() : getImportUpdateErrCls();
	}
	
	/**
	 * 
	 * 批量新增、编辑
	 * 
	 * @param response
	 * @param importDataVO
	 * @return
	 */
	@ApiOperation(value = "批量新增、编辑", notes = "批量新增、编辑（excel导入方式， 07版本）")
    @PostMapping("/importData")
	public R importData(HttpServletResponse response, ImportDataVO importDataVO) {
		try {
			// 导入类型
			int importType = importDataVO.getImportType();
			// 导入处理 class
			Class excelCls = getExcelCls(importType);
			// 导入Listener
			EasyExcelListener easyExcelListener = new EasyExcelListener(getHdpService(), excelCls, importType, getExcelIndex());
			// 读取excel
			EasyExcelFactory.read(importDataVO.getFile().getInputStream(), excelCls, easyExcelListener).sheet().headRowNumber(getHeadRowNumber()).doRead();
			// 导入异常信息集合
			List<ExcelCheckErrDTO> errList = easyExcelListener.getErrList();
			
			// 包含错误信息就导出错误信息
			if (ObjectUtil.isNotEmpty(errList)) {
				// 是否下载异常信息
				Integer downloadErrMsg = importDataVO.getDownloadErrMsg();
				// 下载异常excel
				if(ObjectUtil.isNotNull(downloadErrMsg) && downloadErrMsg.equals(1)) {
					// 获取异常信息class
					Class excelErrCls = getExcelErrCls(importType);
					// 异常类
					Object errObj = excelErrCls.getDeclaredConstructor().newInstance();
					// 获取异常信息提示方法（注：异常提示信息请使用 errMsg属性）
					Method errMsgMethod = excelErrCls.getMethod("setErrMsg", String.class);
					// 遍历异常信息
					List excelErrDtos = errList.stream().map(excelCheckErrDto -> {
						// 设置异常信息
						Object excelErrDto = (Object) JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), excelErrCls);
						try {
							errMsgMethod.invoke(excelErrDto, excelCheckErrDto.getErrMsg());
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
							return R.failed("导入异常：" + e.getMessage());
						}
						return excelErrDto;
					}).collect(Collectors.toList());
					// 导出异常信息
					EasyExcelUtils.webWriteExcel(response, excelErrDtos, excelErrCls, ManpowerContants.ImportTypeEnum.getInstance(importType) + "错误信息");
				}else {
					return R.failed("导入异常，是否下载错误文件？");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed("导入异常：" + e.getMessage());
		}
		return R.ok(null, "导入成功！");
	}
    
	/**
	 * 下载模板
	 * 
	 * @param response
	 * @param importType
	 * @return
	 */
	@ApiOperation(value = "下载批量新增、编辑模板")
	@PostMapping("/downloadTemplate")
    @ApiImplicitParam(name = "importType", value = "下载模板类型，值： 0  批量新增模板； 值 1 批量修改模板")
	public R downloadTemplate(HttpServletResponse response, @RequestParam(value = "importType")Integer importType) {
    	// 导出处理 class
		Class excelCls = getExcelCls(importType);
		// 数据集
		List dataList = (importType == 0 ? new ArrayList<>() : getDownloadUpdateTemplateList());
		try {
			EasyExcelUtils.webWriteExcel(response, dataList, excelCls, "批量" + ManpowerContants.ImportTypeEnum.getInstance(importType) + "模板",
					                     new EasyExcelSheetWriteHandler(excelCls, getExcelDescription(importType)));
		} catch (Exception e) {
			e.printStackTrace();
			R.failed("下载模板异常：" + e.getMessage());
		}
		return R.ok(null, "下载模板成功！");
	}
}
