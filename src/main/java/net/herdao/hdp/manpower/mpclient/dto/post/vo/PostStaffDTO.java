package net.herdao.hdp.manpower.mpclient.dto.post.vo;

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
public class PostStaffDTO {

    //任职岗位、员工姓名、工号、手机号码、所在部门

    @ExcelProperty(value = "任职岗位")
    private String postName;
    @ExcelProperty(value = "员工姓名")
    private String staffName;
    @ExcelProperty(value = "工号")
    private String staffCode;
    @ExcelProperty(value = "手机号码")
    private String mobile;
    @ExcelProperty(value = "所在部门")
    private String orgName;
}
