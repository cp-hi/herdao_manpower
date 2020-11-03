package net.herdao.hdp.manpower.mpclient.dto.post;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.sys.entity.OperationLog;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PostListDTO
 * @Description PostListDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/13 19:44
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位DTO")
public class PostDTO extends Post {


    @ApiModelProperty("板块")
    private String sectionName;

    @ApiModelProperty("管线")
    private String pipelineName;

    @ApiModelProperty("职等")
    private String jobGradeName;

    @ApiModelProperty("所属集团")
    private String groupName;

    @ApiModelProperty("职级1")
    private String jobLevelName1;

    @ApiModelProperty("职级2")
    private String jobLevelName2;

    @ApiModelProperty("在职员工数")
    private Integer postAuthorized;

    @ApiModelProperty("岗位编制")
    private Integer onJobStaffs;

    @ApiModelProperty("岗位序列")
    private PostSeqDTO postSeqDTO;
}
