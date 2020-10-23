package net.herdao.hdp.manpower.mpclient.dto.organization;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 异常处理
 * @author      shuling
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OrganizationExcelErrDTO  extends OrganizationAddDTO{

    @ExcelProperty(index = 0, value = "错误信息")
    @ColumnWidth(75)
    private String errMsg;


}
