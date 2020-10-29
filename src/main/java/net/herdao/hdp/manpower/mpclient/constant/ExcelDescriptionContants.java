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

	/**
	 * 获取批量工作经历新增说明
	 * @return
	 */
	public static String getWorkAddDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填。")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据。")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。")
				.append(LINE_FEED)
				.append("4、下属人数：正整数。")
				.append(LINE_FEED)
				.append("5、开始日期、结束日期：格式为yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。结束时间必须在开始时间之后。");
		return eds.toString();
	}

	/**
	 * 获取批量工作经历编辑说明
	 * @return
	 */
	public static String getWorkUpdateDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填。")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据。")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。")
				.append(LINE_FEED)
				.append("4、下属人数：正整数。")
				.append(LINE_FEED)
				.append("5、开始日期、结束日期：格式为yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。结束时间必须在开始时间之后。")
				.append(LINE_FEED)
				.append("6、员工姓名+员工工号+开始时间+结束时间+单位名称:数据唯一标识，不允许重复导入记录。");
		return eds.toString();
	}


	/**
	 * 获取批量合同新增说明
	 * @return
	 */
	public static String getContractAddDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填\r\n")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据\r\n")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
				.append(LINE_FEED)
				.append("4、合同期限类型：输入系统中已存在的类型。\r\n")
				.append(LINE_FEED)
				.append("5、培训时长，培训总学时、培训总学分、培训成绩：正整数。\r\n")
				.append(LINE_FEED)
				.append("6、合同编号，合同期限，试用期：正整数。\r\n")
				.append(LINE_FEED)
				.append("7、劳动合同起始日期、劳动合同结束日期：格式为yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。结束时间必须在开始时间之后。")
				.append(LINE_FEED)
				.append("8、合同签订主体：从注册公司表中选取。")
				.append(LINE_FEED)
				.append("9、合同是否生效： 是，否。");

		return eds.toString();
	}

	/**
	 * 获取批量合同编辑说明
	 * @return
	 */
	public static String getContractUpdateDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填\r\n")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据\r\n")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
				.append(LINE_FEED)
				.append("4、合同期限类型：输入系统中已存在的类型。\r\n")
				.append(LINE_FEED)
				.append("5、培训时长，培训总学时、培训总学分、培训成绩：正整数。\r\n")
				.append(LINE_FEED)
				.append("6、合同编号，合同期限，试用期：正整数。\r\n")
				.append(LINE_FEED)
				.append("7、劳动合同起始日期、劳动合同结束日期：格式为yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。结束时间必须在开始时间之后。")
				.append(LINE_FEED)
				.append("8、合同签订主体：从注册公司表中选取。")
				.append(LINE_FEED)
				.append("9、合同是否生效： 是，否。")
				.append(LINE_FEED)
				.append("10、员工姓名+员工工号+劳动合同起始日期+劳动合同结束日期:数据唯一标识，不允许重复导入记录。");
		return eds.toString();
	}

	/**
	 * 获取批量家庭新增说明
	 * @return
	 */
	public static String getFamilyAddDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
		    .append(LINE_FEED)
			.append("1、标红字段为必填\r\n")
		    .append(LINE_FEED)
			.append("2、操作导入前请删除示例数据\r\n")
		    .append(LINE_FEED)
	        .append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
		    .append(LINE_FEED)
		    .append("4、关系：输入系统中已存在的类型。");

		return eds.toString();
	}

	/**
	 * 获取批量家庭编辑说明
	 * @return
	 */
	public static String getFamilyUpdateDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填\r\n")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据\r\n")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
				.append(LINE_FEED)
				.append("4、关系：输入系统中已存在的类型。\r\n")
				.append(LINE_FEED)
				.append("5、工号+家庭成员姓名:数据唯一标识，不允许重复导入记录。");

		return eds.toString();
	}

	/**
	 * 获取批量奖惩新增说明
	 * @return
	 */
	public static String getRpAddDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
			.append(LINE_FEED)
			.append("1、标红字段为必填\r\n")
	        .append(LINE_FEED)
			.append("2、操作导入前请删除示例数据\r\n")
		    .append(LINE_FEED)
			.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
		    .append(LINE_FEED)
			.append("4、奖惩时间的日期格式为：yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。\r\n")
		    .append(LINE_FEED)
			.append("5、奖惩类别：输入系统中已存在的类型 ，如：年度优秀员工奖。\r\n")
		    .append(LINE_FEED)
			.append("6、奖惩金额：正整数。\r\n")
		    .append(LINE_FEED)
			.append("7、奖惩/惩罚 ：奖惩 或 惩罚");

		return eds.toString();
	}

	/**
	 * 获取批量奖惩编辑说明
	 * @return
	 */
	public static String getRpUpdateDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填\r\n")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据\r\n")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
				.append(LINE_FEED)
				.append("4、奖惩时间的日期格式为：yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。\r\n")
				.append(LINE_FEED)
				.append("5、奖惩类别：输入系统中已存在的类型 ，如：年度优秀员工奖。\r\n")
				.append(LINE_FEED)
				.append("6、奖惩金额：正整数。\r\n")
				.append(LINE_FEED)
				.append("7、奖惩/惩罚 ：奖惩 或 惩罚。\r\n")
				.append(LINE_FEED)
				.append("8、员工姓名+员工工号+奖励/惩罚+奖惩时间+奖惩类别:数据唯一标识，不允许重复导入记录。") ;

		return eds.toString();
	}


	/**
	 * 获取批量教育新增说明
	 * @return
	 */
	public static String getEduAddDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填\r\n")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据\r\n")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
				.append(LINE_FEED)
				.append("4、入学日期、毕业日期的日期格式为：yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。毕业日期必须在入学日期之后。\r\n")
				.append(LINE_FEED)
				.append("5、学历、学位、学习形式：输入系统中已存在的类型。");

		return eds.toString();
	}

	/**
	 * 获取批量教育编辑说明
	 * @return
	 */
	public static String getEduUpdateDesc() {
		StringBuffer eds = new StringBuffer();
		eds.append("导入说明：")
				.append(LINE_FEED)
				.append("1、标红字段为必填\r\n")
				.append(LINE_FEED)
				.append("2、操作导入前请删除示例数据\r\n")
				.append(LINE_FEED)
				.append("3、员工工号，员工姓名：请输入系统中已存在的员工工号和员工姓名，员工工号和员工姓名必须匹配一致。\r\n")
				.append(LINE_FEED)
				.append("4、入学日期、毕业日期的日期格式为：yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd。毕业日期必须在入学日期之后。\r\n")
				.append(LINE_FEED)
				.append("5、学历、学位、学习形式：输入系统中已存在的类型。\r\n")
				.append("6、员工姓名+员工工号+入学日期+毕业日期+毕业院校:数据唯一标识，不允许重复导入记录。");

		return eds.toString();
	}
}
