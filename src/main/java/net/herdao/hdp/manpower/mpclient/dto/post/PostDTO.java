package net.herdao.hdp.manpower.mpclient.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.*;

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
//@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "岗位列表DTO")
public class PostDTO extends Post {

//    @ApiModelProperty(value = "岗位名称", required = true)
//    private String postName;
//
//    @ApiModelProperty(value = "岗位编码", required = true)
//    private String postCode;
//
//    @ApiModelProperty(value = "岗位序列", required = true)
//    private String postSeqName;
//
//    @ApiModelProperty(value = "所属集团")
//    private String groupName;
//
//    @ApiModelProperty(value = "板块")
//    private String sectionName;
//
//    @ApiModelProperty(value = "管线")
//    private String pipelineName;
//
//    @ApiModelProperty(value = "职等名称")
//    private String jobGradeName;
//
//    @ApiModelProperty(value = "职级名称")
//    private String jobLevelName;



    @ApiModelProperty(value = "板块")
    private Section section;

    @ApiModelProperty(value = "管线")
    private Pipeline pipeline;

    @ApiModelProperty(value = "职等")
    private JobGrade jobGrade;

    @ApiModelProperty(value = "所属集团")
    private Group group;


    @ApiModelProperty(value = "职级1")
    private JobLevel jobLevel1;

    @ApiModelProperty(value = "职级2")
    private JobLevel jobLevel2;

    @ApiModelProperty(value = "在职员工数")
    private Integer postAuthorized;

    @ApiModelProperty(value = "岗位编制")
    private Integer onJobStaffs;

    @ApiModelProperty(value = "岗位序列")
    private  PostSeq  postSeq ;
}
