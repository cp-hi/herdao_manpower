
package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * 人才标签VO
 *
 * @author Andy
 * @date 2020-11-25 14:40:54
 */
@Data
@ApiModel(value = "人才标签VO")
public class RecruitmentLabelVO extends Model<RecruitmentLabelVO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 标签名称
     */
    @ApiModelProperty(value="标签名称")
    @Length(max = 20,message = "最长字数限制：20个字")
    private String name;

    /**
     * 人才ID
     */
    @ApiModelProperty(value="人才ID")
    private Long recruitmentId;

}
