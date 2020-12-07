package net.herdao.hdp.manpower.mpclient.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.NumberUtils;
import com.alibaba.excel.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<LocalDateTime>{

    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String value = cellData.getStringValue();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(value,df);
        return ldt;

    }

    @Override
    public CellData convertToExcelData(LocalDateTime localDateTime, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (localDateTime != null) {
            String tmp = String.valueOf(localDateTime);
            if(StringUtils.isEmpty(tmp)){
                return new CellData(localDateTime);
            }
            return new CellData(tmp);
        }
        return new CellData("");
    }

    public LocalDateTime convert2LocalDateTime (Long from) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(from), ZoneId.systemDefault());
    }

    public Long convert2Long (LocalDateTime from) {
        return Timestamp.valueOf(from).getTime();
    }
}
