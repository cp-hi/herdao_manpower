

package net.herdao.hdp.manpower.mpclient.dto.workExperience;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 人才工作经历表DTO
 * @author Andy
 * @date 2020-11-26 09:18:33
 */
@Data
@ApiModel(value = "人才工作经历表DTO")
public class RecruitmentWorkExperienceDTO {

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 开始日期
     */
    @ApiModelProperty(value="开始日期")
    private Long period;

    /**
     * 结束日期
     */
    @ApiModelProperty(value="结束日期")
    private Long todate;

    /**
     * 开始日期-LocalDateTime
     */
    @ApiModelProperty(value="开始日期-LocalDateTime",hidden = true)
    private LocalDateTime periodLocal;

    /**
     * 结束日期-LocalDateTime
     */
    @ApiModelProperty(value="结束日期-LocalDateTime",hidden = true)
    private LocalDateTime toDateLocal;

    /**
     * 单位名称
     */
    @ApiModelProperty(value="单位名称")
    private String companyName;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value="岗位名称")
    private String post;

    /**
     * 现金收入(单位：万元)
     */
    @ApiModelProperty(value="现金收入(单位：万元)")
    private BigDecimal cashIncome;

    /**
     * 下属人数(单位：人)
     */
    @ApiModelProperty(value="下属人数(单位：人)")
    private BigDecimal subordinate;

    /**
     * 离职原因
     */
    @ApiModelProperty(value="离职原因")
    private String leaveReason;

    /**
     * 联系人
     */
    @ApiModelProperty(value="联系人")
    private String contact;

    /**
     * 联系人电话
     */
    @ApiModelProperty(value="联系人电话")
    private String contactTel;

    /**
     * 主要业绩
     */
    @ApiModelProperty(value="主要业绩")
    private String mainPerformance;

    /**
     * 承担角色
     */
    @ApiModelProperty(value="承担角色")
    private String role;

    /**
     * 人才主键ID
     */
    @ApiModelProperty(value="人才主键ID")
    private Long recruitmentId;

 }
