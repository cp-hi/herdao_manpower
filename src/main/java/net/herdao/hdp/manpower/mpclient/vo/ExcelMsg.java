package net.herdao.hdp.manpower.mpclient.vo;

/**
 * @ClassName ExcelDTO
 * @Description 用于批量excel导入
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/12 15:20
 * @Version 1.0
 */
public interface ExcelMsg {

    void setErrMsg(String errMsg);

    default String getDescription() {
        String line = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("导入说明：").append(line);
        builder.append("1、标红字段为必填").append(line);
        builder.append("2、操作导入前请删除示例数据").append(line);
        builder.append("3、上级组织名称请填写已启用的组织编码").append(line);
        builder.append("4、组织类型请输入系统中已存在的组织类型，如：部门").append(line);
        builder.append("5、组织负责人工号请输入在职员工的工号；负责岗位请输入系统中已启用的岗位").append(line);
        return builder.toString();
    }
}
