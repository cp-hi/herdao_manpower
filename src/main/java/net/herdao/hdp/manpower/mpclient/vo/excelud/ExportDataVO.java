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
	
	/**
	 * 导入类型，值： 0  批量新增； 值 1 批量编辑
	 */
	@ApiModelProperty(value = "导入类型，值： 0  批量新增； 值 1 批量编辑")
	Integer importType;

}
