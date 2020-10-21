package net.herdao.hdp.manpower.mpclient.dto.section.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelVO;

/**
 * @ClassName SectionBatchUpdateDTO
 * @Description SectionBatchUpdateDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 12:14
 * @Version 1.0
 */
//版块名称、所属集团、启用状态、排序、备注
@Data
public class SectionBatchUpdateDTO extends SectionBatchAddDTO implements ExcelVO {

    @ExcelProperty("板块名称")
    private String sectionName;
    @ExcelProperty("所属集团")
    private String groupName;
    @ExcelProperty("排序")
    private Integer sortNo;

    @ExcelProperty("是否停用")
    private String stop;

    @ExcelProperty("错误信息")
    private String errMsg;
}