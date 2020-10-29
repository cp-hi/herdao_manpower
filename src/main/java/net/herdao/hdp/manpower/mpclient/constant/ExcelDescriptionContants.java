package net.herdao.hdp.manpower.mpclient.constant;
/**
 * @description: 模板导入说明
 * 
 * @author shuling
 * @date 2020-10-28 11:37:27
 */
public class ExcelDescriptionContants {
	
	// 换行符号
	static final String LINE_FEED = System.getProperty("line.separator");
	
	/**
	 * 获取批量新增、编辑模板备注信息
	 * @return
	 */
	public static String getOrganizationExcelDescription() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据")
				.append(LINE_FEED)
				.append("3、上级组织名称请填写已启用的组织编码")
				.append(LINE_FEED)
				.append("4、组织类型请输入系统中已存在的组织类型，如：部门")
				.append(LINE_FEED)
			    .append("5、组织负责人工号请输入在职员工的工号；负责岗位请输入系统中已启用的岗位");
		return eds.toString();
	}
}
