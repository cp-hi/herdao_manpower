package net.herdao.hdp.manpower.mpclient.dto.easyexcel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;

/**
 * @description: excel数据导入结果
 * 
 * @author hdp
 * @date 2020-10-20 11:37:27
 */
@Data
public class ExcelCheckResultDTO<T> {
	
    private List<T> successDtos;

    private List<ExcelCheckErrDTO<T>> errDtos;

    public ExcelCheckResultDTO(List<T> successDtos, List<ExcelCheckErrDTO<T>> errDtos){
        this.successDtos =successDtos;
        this.errDtos = errDtos;
    }

    public ExcelCheckResultDTO(List<ExcelCheckErrDTO<T>> errDtos){
        this.successDtos =new ArrayList<>();
        this.errDtos = errDtos;
    }
}
