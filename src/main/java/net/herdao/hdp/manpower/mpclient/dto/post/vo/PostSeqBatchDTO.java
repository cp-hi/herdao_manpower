package net.herdao.hdp.manpower.mpclient.dto.post.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelErrMsg;

/**
 * @ClassName PostSeqBatchDTO
 * @Description PostSeqBatchDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/21 17:22
 * @Version 1.0
 */
//岗位序列名称、上级岗位序列、描述
@Data
public class PostSeqBatchDTO implements ExcelErrMsg {
    @ExcelProperty(value = "岗位序列名称")
    private String postSeqName;
    @ExcelProperty(value = "上级岗位序列")
    private String parentName;
    @ExcelProperty(value = "描述")
    private String description;
    @ExcelProperty(value = "错误信息")
    private String errMsg;
}
