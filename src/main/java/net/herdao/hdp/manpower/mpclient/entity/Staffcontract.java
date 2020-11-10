package net.herdao.hdp.manpower.mpclient.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工合同签订
 * @author liang
 * @date 2020-09-27 09:15:28
 */
@Data
@TableName("mp_staffcontract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工合同签订")
public class Staffcontract extends BaseModel<Staffcontract> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
//
//    /**
//     * 员工合同管理OID(旧平台)
//     */
//    @ApiModelProperty(value="员工合同管理OID(旧平台)")
//    private String oid;

    /**
     * 合同开始日期
     */
    @ApiModelProperty(value="合同开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    /**
     * 合同结束日期
     */
    @ApiModelProperty(value="合同结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    /**
     * 劳动合同签订主体id
     */
    @ApiModelProperty(value="劳动合同签订主体id")
    private String companyId;

    /**
     * 合同编号(旧平台)
     */
    @ApiModelProperty(value="合同编号(旧平台)")
    private String contractOid;

    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号")
    private String contractId;

    /**
     * 试用期月数
     */
    @ApiModelProperty(value="试用期月数")
    private Long probationMonth;

    /**
     * 是否当前生效合同 （合同是否生效）,0生效 ，1失效
     */
    @ApiModelProperty(value="是否当前生效合同 （合同是否生效）,0生效，1失效")
    private Boolean newest;

    /**
     * 合同期限类型
     */
    @ApiModelProperty(value="合同期限类型")
    private String contractType;

    /**
     * 合同期限(月)
     */
    @ApiModelProperty(value="合同期限(月)")
    private Long contractPeriod;

    /**
     * 违约金
     */
    @ApiModelProperty(value="违约金")
    private BigDecimal liquidatedDamages;

    /**
     * 经济补偿金
     */
    @ApiModelProperty(value="经济补偿金")
    private BigDecimal economicCompensation;

    /**
     * 解除合同原因
     */
    @ApiModelProperty(value="解除合同原因")
    private String removeContract;
    /**
     * 解除劳动合同证明编号(旧平台)
     */
    @ApiModelProperty(value="解除劳动合同证明编号(旧平台)")
    private String removeOid;
//    /**
//     * 员工外键(旧平台)
//     */
//    @ApiModelProperty(value="员工外键(旧平台)")
//    private String staffOid;

    /**
     * 解除劳动合同证明编号
     */
    @ApiModelProperty(value="解除劳动合同证明编号")
    private Long removeId;

    /**
     * 员工外键
     */
    @ApiModelProperty(value="员工外键")
    private Long staffId;

    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorCode;

    /**
     * 新建时间

     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime createdTime;

    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "修改ID" ,hidden = true)
    private Long modifierId;

    @ApiModelProperty(value = "修改人名称" ,hidden = true)
    private String modifierName;

    @ApiModelProperty(value = "创建人ID" ,hidden = true)
    private Long creatorId;

    @ApiModelProperty(value = "创建人名称" ,hidden = true)
    private String creatorName;
}
