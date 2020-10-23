package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName OKPostSeq
 * @Description OK = one key 一键创建岗位序列
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 10:50
 * @Version 1.0
 */

@Data
@TableName("mp_ok_post_seq")
@ApiModel("一键创建岗位序列")
public class OKPostSeq extends PostSeq {
    private Long okPostSeqSysId;
}
