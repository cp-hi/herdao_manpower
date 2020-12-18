package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 手机端-个人信息VO
 * @author Andy
 */
@Data
@ApiModel(value = "手机端-个人信息VO")
public class RecruitmentMobileVO {

    /**
     * 家庭成员姓名
     */
    @ApiModelProperty(value="家庭成员姓名")
    private String name;

    /**
     * 关系
     */
    @ApiModelProperty(value="关系")
    private String relations;

    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private String age;

    /**
     * 职业
     */
    @ApiModelProperty(value="职业")
    private String professional;

    /**
     * 工作单位/就读学校
     */
    @ApiModelProperty(value="工作单位/就读学校")
    private String workUnit;

    /**
     * 所在地址
     */
    @ApiModelProperty(value="所在地址")
    private String address;


}
