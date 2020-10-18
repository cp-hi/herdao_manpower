package net.herdao.hdp.manpower.mpclient.dto.post.vo;

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
public class PostDetailDTO {
    //岗位名称、岗位编码、集团、板块、管线、职级、组织层级、创建时间
    @ExcelProperty(value = "岗位名称")
    private String postName;

    @ExcelProperty(value = "岗位编码")
    private String postCode;

    @ExcelProperty(value = "集团")
    private String groupName;

    @ExcelProperty(value = "板块")
    private String sectionName;

    @ExcelProperty(value = "管线")
    private String pipelineName;

    @ExcelProperty(value = "职级")
    private String jobLevelName;

    @ExcelIgnore
    @ExcelProperty(value = "职等")
    private String jobGradeName;

    @ExcelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat("yyyy-MM-dd")
    private Date createdTime;
}
