package net.herdao.hdp.manpower.mpclient.vo.post;

import lombok.Data;

/**
 * @ClassName PostVO
 * @Description PostVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/15 20:06
 * @Version 1.0
 */
@Data
public class PostVO {
    private Long id;

    private String postName;

    private String postCode;

    private String postSeq;

    private String groupName;

    private String sectionName;

    private String pipelineName;

    private String jobGradeName;

    private String jobLevel;


}
