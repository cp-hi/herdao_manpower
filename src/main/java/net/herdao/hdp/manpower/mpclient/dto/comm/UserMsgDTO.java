package net.herdao.hdp.manpower.mpclient.dto.comm;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  
@AllArgsConstructor
@ApiModel(value = "UserMsgDTO")
public class UserMsgDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户id")
    private long id;

	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value="工号")
    private String staffCode;
	
	@ApiModelProperty(value="组织id")
    private long orgId;

	@ApiModelProperty(value="组织名称")
    private String orgName;
	
	@ApiModelProperty(value="岗位id")
    private long postId;

	@ApiModelProperty(value="岗位组织id")
    private long postOrgId;

	/**
	 * 该字段为 mp_post_org（业务岗位） 表中的 post_name
	 * 而不是 mp_post（标准岗位） 表中的 post_name
	 */
	@ApiModelProperty(value="岗位名称")
    private String postName;
	
	@ApiModelProperty(value="岗位薪酬级别")
    private String postLevel;
	
	@ApiModelProperty(value="职级id")
    private long jobLevelId;

	@ApiModelProperty(value="职级名称")
	private String jobLevelName;
	
	@ApiModelProperty(value="人员归属范围")
    private String staffScope;

	@ApiModelProperty(value="任职类型")
    private String jobType;

	@ApiModelProperty(value="入职时间")
    private long entryTime;
	
	@ApiModelProperty(value="性别")
    private String sex;
	
	@ApiModelProperty(value="年龄")
    private int age;
	
	@ApiModelProperty(value="籍贯")
    private String birthplace;
}
