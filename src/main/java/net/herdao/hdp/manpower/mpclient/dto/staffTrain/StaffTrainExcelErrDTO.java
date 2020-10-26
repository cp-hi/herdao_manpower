package net.herdao.hdp.manpower.mpclient.dto.staffTrain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationAddDTO;

/**
 * @description 异常处理
 * @author      andy
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StaffTrainExcelErrDTO extends StaffTrainAddDTO{

    @ExcelProperty(index = 0, value = "错误信息")
    @ColumnWidth(75)
    private String errMsg;


}
