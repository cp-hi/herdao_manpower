package net.herdao.hdp.manpower.mpclient.vo.post;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

import java.time.LocalDateTime;


@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "岗位组织管理-列表，用于列表展示")
public class PostOrgListVO {
    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 岗位编码
     */
    @ApiModelProperty(value="岗位编码")
    private String postCode;
    /**
     * 岗位名称
     */
    @ApiModelProperty(value="岗位名称")
    private String postName;
    /**
     * 集团d
     */
    @ApiModelProperty(value="集团id")
    private Long groupId;
    /**
     * 标准岗位id
     */
    @ApiModelProperty(value="标准岗位id")
    private Long postId;
    /**
     * 所属组织id
     */
    @ApiModelProperty(value="所属组织id")
    private Long orgId;
    /**
     * 是否一把手0不是1是
     */
    @ApiModelProperty(value="是否一把手0不是1是")
    private String isLeader;
    /**
     * 是否干部岗0不是1是
     */
    @ApiModelProperty(value="是否干部岗0不是1是")
    private String isCadre;
    /**
     * 板块id
     */
    @ApiModelProperty(value="板块id")
    private Long sectionId;
    /**
     * 管线id
     */
    @ApiModelProperty(value="管线id")
    private Long pipelineId;
    /**
     * 职级id
     */
    @ApiModelProperty(value="职级id")
    private Long jobLevelId;
    /**
     * 岗位编制
     */
    @ApiModelProperty(value="岗位编制")
    private Integer jobStaffs;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Long sortNo;
    /**
     * 是否停用
     */
    @ApiModelProperty(value="是否停用")
    private Integer isStop;
    /**
     * 新建用户ID
     */
    @ApiModelProperty(value="新建用户工号")
    private String creatorCode;
    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorName;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime creatorTime;
    /**
     * 修改用户ID
     */
    @ApiModelProperty(value="修改用户工号")
    private Long modifierCode;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierName;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifierTime;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String field1;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String field2;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String field3;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String field4;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String field5;
    /**
     *
     */
    @ApiModelProperty(value="")
    private Boolean delFlag;
    /**
     * 停用日期
     */
    @ApiModelProperty(value="停用日期")
    private LocalDateTime stopDate;
    /**
     * 启用日期
     */
    @ApiModelProperty(value="启用日期")
    private LocalDateTime startDate;
    /**
     * 主岗名称
     */
    @ApiModelProperty(value="主岗名称")
    private String mainPostName ;
    /**
     * 组织名称
     */
    @ApiModelProperty(value="组织名称")
    private String  orgName;
    /**
     * 职等名称
     */
    @ApiModelProperty(value="职等名称")
    private String  jobGradeName;
    /**
     * 职级名称
     */
    @ApiModelProperty(value="职级名称")
    private String  jobLevelName;
    /**
     * 板块名称
     */
    @ApiModelProperty(value="板块名称")
    private String  sectionName;
    /**
     * 管线名称
     */
    @ApiModelProperty(value="管线名称")
    private String  pipelineName;
    /**
     * 岗位序列名称
     */
    @ApiModelProperty(value="岗位序列名称")
    private String  postSeqName;
    /**
     * 在职员工数
     */
    @ApiModelProperty(value="在职员工数")
    private Integer  staffCount;

}
