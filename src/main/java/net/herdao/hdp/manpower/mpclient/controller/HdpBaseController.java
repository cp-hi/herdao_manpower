package net.herdao.hdp.manpower.mpclient.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.handler.EasyExcelSheetWriteHandler;
import net.herdao.hdp.manpower.mpclient.listener.EasyExcelListener;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.utils.EasyExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.excelud.ExportDataVO;
import net.herdao.hdp.manpower.mpclient.vo.excelud.ImportDataVO;
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
	
	// 导入处理 class
	Class excelCls;
	// 异常处理 class
	Class excelErrCls;
	// 批量新增处理Class
	Class importAddCls;
	// 批量新增处理异常Class
	Class importAddErrCls;
	// 批量修改处理Class
	Class importUpdateCls;
	// 批量修改异常处理Class
	Class importUpdateErrCls;
	// 导入数据开始行号
	Integer excelIndex;
	// 表头行号
	Integer headRowNumber;
	// 批量新增导出数据集
	List downloadUpdateTemplateList;
	// 模板描述
	String excelDescription;
	// 模板名称
	String templateName;
	
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
	 * 批量编辑模板导出数据集合（根据查询条件导出, 该方法需要在子类单独重写）
	 * 
	 * @return
	 */
	public List getDownloadUpdateTemplateList(Map<String, Object> searchParams) {
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
	 * 模板名称
	 * 
	 * @return
	 */
	public String getTemplateName() {
		return "";
	}
	
	public HdpBaseController() {}
	
	/**
	 * 初始化批量新增、编辑参数
	 * 
	 * @param importAddCls               批量新增处理Class
	 * @param importAddErrCls            批量新增处理异常Class
	 * @param importUpdateCls            批量修改处理Class
	 * @param importUpdateErrCls         批量修改异常处理Class
	 * @param excelIndex                 导入数据开始行号
	 * @param headRowNumber              表头行号
	 * @param downloadUpdateTemplateList 批量新增导出数据集
	 * @param templateName               模板名称
	 * @param excelDescription           模板描述
	 */
	public void initEasyExcelArgs(Class importAddCls, Class importAddErrCls, Class importUpdateCls, Class importUpdateErrCls, 
						 Integer excelIndex, Integer headRowNumber, List downloadUpdateTemplateList, String excelDescription, String templateName) {
		this.importAddCls = importAddCls;
		this.importAddErrCls = importAddErrCls;
		this.importUpdateCls = importUpdateCls;
		this.importUpdateErrCls = importUpdateErrCls;
		this.excelIndex = excelIndex;
		this.headRowNumber = headRowNumber;
		this.downloadUpdateTemplateList = downloadUpdateTemplateList;
		this.excelDescription = excelDescription;
		this.templateName = templateName;
	}
	
	/**
	 * 兼容单个参数初始化情况
	 * 
	 * @param importType
	 */
	private void getInitEasyExcelArgs(Integer importType) {
		
		initEasyExcelArgs(importAddCls, importAddErrCls, importUpdateCls, importUpdateErrCls, excelIndex, headRowNumber, 
						  downloadUpdateTemplateList, excelDescription, templateName);
		// 是否批量新增
		boolean isAddImport = (importType.equals(0) ? true : false);
		
		excelCls = getExcelCls(importType);
		if (ObjectUtil.isNull(excelCls)) {
			excelCls = isAddImport ? importAddCls : importUpdateCls;
		}
		excelErrCls = getExcelErrCls(importType);
		if (ObjectUtil.isNull(excelErrCls)) {
			excelErrCls = isAddImport ? importAddErrCls : importUpdateErrCls;
		}
		if (ObjectUtil.isNull(excelIndex)) {
			excelIndex = getExcelIndex();
		}
		if (ObjectUtil.isNull(headRowNumber)) {
			headRowNumber = getHeadRowNumber();
		}
		if (ObjectUtil.isNull(downloadUpdateTemplateList)) {
			downloadUpdateTemplateList = getDownloadUpdateTemplateList();
		}
		if (StrUtil.isBlank(excelDescription)) {
			excelDescription = this.getExcelDescription(importType);
		}
		if (StrUtil.isBlank(templateName)) {
			templateName = getTemplateName();
		}
	}
	
	/**
	 * 
	 * 批量新增、编辑
	 * 
	 * @param response
	 * @param importDataVO
	 * @return
	 */
	@ApiOperation(value = "批量新增、编辑")
    @PostMapping("/importData")
	public Object importData(HttpServletResponse response, ImportDataVO importDataVO) {
		try {
			// 导入类型
			int importType = importDataVO.getImportType();
			// 获取初始化参数
			getInitEasyExcelArgs(importType);
			// 导入Listener
			EasyExcelListener easyExcelListener = new EasyExcelListener(getHdpService(), excelCls, importType, excelIndex);
			// 读取excel
			EasyExcelFactory.read(importDataVO.getFile().getInputStream(), excelCls, easyExcelListener).sheet().headRowNumber(headRowNumber).doRead();
			// 批量标识
			String templateDp = ManpowerContants.ImportTypeEnum.getInstance(importType) + templateName;
			List excelList = easyExcelListener.getExcelList();
			if(ObjectUtil.isEmpty(excelList) && !importDataVO.getDownloadErrMsg().equals(1)) {
				return R.failed("批量" + templateDp + "失败，模板数据为空！");
			}
			// 导入异常信息集合
			List<ExcelCheckErrDTO> errList = easyExcelListener.getErrList();
			// 获取异常信息提示方法（注：异常提示信息请使用 errMsg属性）
			Method errMsgMethod = excelErrCls.getMethod("setErrMsg", String.class);
			// 遍历异常信息
			List excelErrDtos = null;
			// 包含错误信息就导出错误信息
			if (ObjectUtil.isNotEmpty(errList)) {
				// 是否下载异常信息
				Integer downloadErrMsg = importDataVO.getDownloadErrMsg();
				// 下载异常excel
				if(ObjectUtil.isNotNull(downloadErrMsg) && downloadErrMsg.equals(1)) {
					// 遍历异常信息
					excelErrDtos = errList.stream().map(excelCheckErrDto -> {
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

				}else {
					return R.failed("导入异常，是否下载错误文件？");
				}
			}
			// 当前情况是模板为空，这种情况建议后台加一个状态标识，前端不下载异常文件，待沟通 TOTO 
			if(importDataVO.getDownloadErrMsg().equals(1)) {
				if(ObjectUtil.isNull(excelErrDtos)) {
					excelErrDtos = new ArrayList<>();
					// 异常类
					Object errObj = excelErrCls.getDeclaredConstructor().newInstance();
					errMsgMethod.invoke(errObj, "模板数据为空");
					excelErrDtos.add(errObj);
				}
				// 导出异常信息
				EasyExcelUtils.webWriteExcel(response, excelErrDtos, excelErrCls, templateDp + "信息为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed("导入异常：" + e.getMessage());
		}
		return null;
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
	public Object downloadTemplate(HttpServletResponse response, @RequestBody ExportDataVO exportDataVO) {
		// 下载导入类型模板
		int importType = exportDataVO.getImportType();
		// 获取初始化参数
		getInitEasyExcelArgs(importType);
		// 查询条件
		String searchParams = exportDataVO.getSearchParams();
		// 数据集
		List dataList = (importType == 0 ? null : (StrUtil.isBlank(searchParams) ? downloadUpdateTemplateList : getDownloadUpdateTemplateList(JSONObject.parseObject(exportDataVO.getSearchParams(), new TypeReference<Map<String, Object>>(){}))));
		try {
			EasyExcelUtils.webWriteExcel(response, dataList, excelCls, "批量" + ManpowerContants.ImportTypeEnum.getInstance(importType) + templateName,
					                     new EasyExcelSheetWriteHandler(excelCls, excelDescription));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed("下载模板异常：" + e.getMessage());
		}
		return null;
	}
}
