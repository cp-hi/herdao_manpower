package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName OKPost
 * @Description   OK = one key 一键创建岗位
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 10:51
 * @Version 1.0
 */

@Data
@TableName("mp_ok_post")
@ApiModel("一键创建岗位")
public class OKPost extends Post {
    private Long okPostSeqId;
}
