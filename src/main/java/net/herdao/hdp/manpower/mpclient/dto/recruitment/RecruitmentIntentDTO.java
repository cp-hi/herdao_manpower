
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.util.Date;

/**
 * 人才简历-从业情况与求职意向详情
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-从业情况与求职意向详情DTO")
public class RecruitmentIntentDTO  {

    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    private Long id;

    /**
     * 工作年限
     */
    @ApiModelProperty(value="工作年限")
    private String workAge;


    /**
     * 参加工作日期
     */
    @ApiModelProperty(value="参加工作日期")
    private String workdate;

    /**
     * 意向岗位
     */
    @ApiModelProperty(value="意向岗位")
    private String postName;

    /**
     * 最低薪资（月薪/年薪）
     */
    @ApiModelProperty(value="最低薪资（月薪/年薪）")
    private String minimumLevelincome;

    /**
     * 期望薪资（月薪/年薪）
     */
    @ApiModelProperty(value="期望薪资（月薪/年薪）")
    private String expectedLevelincome;

    /**
     * 可到职日期
     */
    @ApiModelProperty(value="可到职日期")
    private String inductionTime;

    /**
     * 其他要求
     */
    @ApiModelProperty(value="其他要求")
    private String otherRequest;
}
