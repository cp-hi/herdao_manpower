package net.herdao.hdp.manpower.mpclient.vo.post;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

import java.io.Serializable;

/**
 * @ClassName PostVO
 * @Description PostVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/15 20:06
 * @Version 1.0
 */
@Data
public class PostVO  extends Object {

    private Long id;

    private String postName;

    private String postCode;

    @DtoField(objField = "postSeq.postSeqName")
    private String postSeqName;

    @DtoField(objField = "group.groupName")
    private String groupName;

    @DtoField(objField = "section.sectionName")
    private String sectionName;

    @DtoField(objField = "pipeline.pipelineName")
    private String pipelineName;

    @DtoField(objField = "jobGrade.jobGradeName")
    private String jobGradeName;

    @DtoField(objField = "jobLevel1.jobLevelName")
    private String jobLevelName;

    @ApiModelProperty(value = "在职员工数")
    private Integer postAuthorized;

    @ApiModelProperty(value = "岗位编制")
    private Integer onJobStaffs;

}
