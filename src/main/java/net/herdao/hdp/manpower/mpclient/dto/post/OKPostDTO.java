package net.herdao.hdp.manpower.mpclient.dto.post;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.Post;

/**
 * @ClassName OKPostDTO
 * @Description OKPostDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 11:18
 * @Version 1.0
 */
@Data
@ApiModel(value = "一键创建岗位")
public class OKPostDTO extends Post {
}
