package net.herdao.hdp.manpower.mpclient.vo.post;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName PostStaffDTO
 * @Description PostStaffDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/26 18:34
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位员工信息")
public class PostStaffVO {

    //任职岗位、员工姓名、工号、手机号码、所在部门

    @ExcelProperty("任职岗位")
    private String postName;
    @ExcelProperty("员工姓名")
    private String staffName;
    @ExcelProperty("工号")
    private String staffCode;
    @ExcelProperty("手机号码")
    private String mobile;
    @ExcelProperty("所在部门")
    private String orgName;
}
