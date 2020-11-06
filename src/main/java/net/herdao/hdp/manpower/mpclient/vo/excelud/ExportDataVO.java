package net.herdao.hdp.manpower.mpclient.vo.excelud;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 模板下载
 * 
 * @author  shuling
 * @date    2020-10-28 10:47:32
 */
@Data
public class ExportDataVO {
	
	@ApiModelProperty(value = "下载导入类型模板，值： 0  批量新增模板； 值 1 批量编辑模板")
	Integer importType;

	@ApiModelProperty(value = "查询条件，json 格式字符串，例如：{\"orgName\" : \"广州\"}")
	String searchParams;
}
