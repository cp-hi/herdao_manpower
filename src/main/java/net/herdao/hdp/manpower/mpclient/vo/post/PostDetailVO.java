package net.herdao.hdp.manpower.mpclient.vo.post;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName PostDetailDTO
 * @Description 岗位明细报表用
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/26 18:05
 * @Version 1.0
 */

@Data
@ApiModel(value = "岗位明细")
public class PostDetailVO {
    //岗位名称、岗位编码、集团、板块、管线、职级、组织层级、创建时间
    @ExcelProperty("岗位名称")
    private String postName;

    @ExcelProperty("岗位编码")
    private String postCode;

    @ExcelProperty("集团")
    private String groupName;

    @ExcelProperty("板块")
    private String sectionName;

    @ExcelProperty("管线")
    private String pipelineName;

    @ExcelProperty("职级")
    private String jobLevelName;

    @ExcelIgnore
    @ExcelProperty("职等")
    private String jobGradeName;

    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat("yyyy-MM-dd")
    private Date createdTime;
}
