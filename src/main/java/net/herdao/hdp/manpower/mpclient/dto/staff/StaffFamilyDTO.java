package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "家庭情况")
public class StaffFamilyDTO {

    private Long id;

    @ApiModelProperty(value="人员外键")
    private Long staffId;

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="关系")
    private String relations;


    @ApiModelProperty(value="年龄")
    private Integer age;

    @ApiModelProperty(value="职业")
    private String professional;

    @ApiModelProperty(value="工作单位/就读学校")
    private String workUnit;

    @ApiModelProperty(value="所在地址")
    private String address;

    @ApiModelProperty(value="修改人(操作人姓名)")
    private String modifierName;

    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value="最近更新情况")
    private String updateDesc;
}
