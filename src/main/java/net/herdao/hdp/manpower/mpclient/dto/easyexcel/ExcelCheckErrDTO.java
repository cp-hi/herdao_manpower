package net.herdao.hdp.manpower.mpclient.dto.easyexcel;

import lombok.Data;

/**
 * @author hdp
 * @description: excel单条数据导入结果
 * @date 2020-10-20 11:37:27
 */
@Data
public class ExcelCheckErrDTO<T> {

    private T t;

    private String errMsg;

    public ExcelCheckErrDTO(){}

    public ExcelCheckErrDTO(T t, String errMsg){
        this.t = t;
        this.errMsg = errMsg;
    }
}
