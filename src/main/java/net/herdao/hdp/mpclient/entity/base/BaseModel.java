package net.herdao.hdp.mpclient.entity.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    @TableLogic(value = "0",delval = "1")
    private Boolean delFlag;
}
