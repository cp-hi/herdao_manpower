package net.herdao.hdp.manpower.mpclient.dto.post.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.OKJobLevelDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;

import java.util.List;

/**
 * @ClassName OKPostSeqDTO
 * @Description OKPostSeqDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 11:18
 * @Version 1.0
 */
@Data
@ApiModel(value = "一键创建岗位序列")
public class OKPostSeqDTO extends PostSeq {
    private List<OKPostDTO > okPostDTOList;

}
