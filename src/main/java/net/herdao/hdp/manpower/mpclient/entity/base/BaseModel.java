package net.herdao.hdp.manpower.mpclient.entity.base;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName BaseModel
 * @Description BaseModel
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/20 16:02
 * @Version 1.0
 */
@Data
@ToString
public class BaseModel<T extends Model<?>> extends Model<T> {
    @ExcelIgnore
    @ApiModelProperty( hidden = true)
    private String field1;

    @ExcelIgnore
    @ApiModelProperty( hidden = true)
    private String field2;

    @ExcelIgnore
    @ApiModelProperty( hidden = true)
    private String field3;

    @ApiModelProperty( hidden = true)
    @ExcelIgnore
    private String field4;

    @ApiModelProperty( hidden = true)
    @ExcelIgnore
    private String field5;

    @ExcelIgnore
    @ApiModelProperty(value = "删除标记" ,hidden = true)
    private Boolean delFlag;
}
