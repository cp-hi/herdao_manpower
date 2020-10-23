package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * @ClassName OKPostSeq
 * @Description   OK = one key 一键创建岗位序列体系
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 10:46
 * @Version 1.0
 */

@Data
@TableName("mp_ok_post_seq_sys")
@ApiModel("一键创建岗位序列体系")
public class OKPostSeqSys  extends BaseEntity<OKPostSeqSys> {
    private Long id;
    private String title;
    private String description;
}


