
package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 员工工号前缀VO
 * @author Andy
 * @date 2020-11-25 14:40:54
 */
@Data
@ApiModel(value = "员工工号前缀VO")
public class StaffCodePrefixVO extends Model<StaffCodePrefixVO> {

    private static final long serialVersionUID = 1L;

    /**
     * 组织code
     */
    @ApiModelProperty(value="集团code")
    private String groupCode;

    /**
     * 集团名称
     */
    @ApiModelProperty(value="集团名称")
    private String groupName;

    /**
     * 部门code
     */
    @ApiModelProperty(value="部门code")
    private String deptCode;

    /**
     * 员工工号前缀
     */
    @ApiModelProperty(value="员工工号前缀")
    private String staffCodeHead;


    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    private String staffCode;

}
