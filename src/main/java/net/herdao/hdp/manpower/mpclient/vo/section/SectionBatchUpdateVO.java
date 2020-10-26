package net.herdao.hdp.manpower.mpclient.vo.section;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;

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
@ExcelIgnoreUnannotated
public class SectionBatchUpdateVO extends SectionBatchAddVO implements ExcelErrMsg {

    @ExcelProperty("板块名称")
    private String sectionName;

    @ExcelProperty("所属集团")
    private String groupName;

    @ExcelProperty("是否停用")
    private String stop;

    @ExcelProperty("排序")
    private Integer sortNo;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("错误信息")
    private String errMsg;
}
