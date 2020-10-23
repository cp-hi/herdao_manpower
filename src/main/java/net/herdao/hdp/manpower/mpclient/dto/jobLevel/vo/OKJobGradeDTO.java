package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;

import java.util.List;

/**
 * @ClassName OKJobGradeDTO
 * @Description OKJobGradeDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 16:13
 * @Version 1.0
 */
@Data
@ApiModel(value = "一键创建职等")
public class OKJobGradeDTO  extends JobGrade {
    private List<OKJobLevelDTO> okJobLevelDTOList;
}
