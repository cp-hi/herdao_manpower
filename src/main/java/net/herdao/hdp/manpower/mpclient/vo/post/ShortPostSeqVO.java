package net.herdao.hdp.manpower.mpclient.vo.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName ShortPostSeqDTO
 * @Description ShortPostSeqDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 11:22
 * @Version 1.0
 */
@Data
@Builder
@ApiModel(value = "简短岗位序列/岗位")
public class ShortPostSeqVO {
    @ApiModelProperty("岗位序列")
    @DtoField(objField = "")
    private String postSeqName;
    @ApiModelProperty("岗位")
    private String postName;
}
