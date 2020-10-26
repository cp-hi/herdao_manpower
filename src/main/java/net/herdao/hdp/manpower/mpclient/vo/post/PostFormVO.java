package net.herdao.hdp.manpower.mpclient.vo.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


/**
 * @ClassName PostFormVO
 * @Description PostFormVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 14:40
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位管理-表单信息，用于新增和编辑")
public class PostFormVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位名称")
    private String postName;

    @ApiModelProperty("岗位序列")
    private Long postSeqId;

    @ApiModelProperty("所属集团")
    private Long groupId;

    @ApiModelProperty("版块")
    private Long sectionId;

    @ApiModelProperty("管线")
    private Long pipelineId;

    @ApiModelProperty("职级1")
    private String jobLevelId1;

    @ApiModelProperty("职级2")
    private String jobLevelId2;

    @ApiModelProperty("是否单职级")
    private Boolean singleJobLevle;

    @ApiModelProperty("岗位组织级别")
    private String orgType;

    @ApiModelProperty("年终奖比例")
    private String yearPayRatio;

    @ApiModelProperty("绩效工资比例")
    private String perforSalaryRatio;

    @ApiModelProperty("岗位薪酬级别")
    private String postLevel;

    @ApiModelProperty("岗位职责")
    private String postDescr;

    @ApiModelProperty("备注")
    private String remark;

}
