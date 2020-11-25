package net.herdao.hdp.manpower.mpclient.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.admin.api.dto.TreeNode;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;

import java.io.Serializable;

@Data
@ApiModel(value = "岗位序列树")
public class PostSeqTree extends TreeNode<Long> implements Serializable {
    @ApiModelProperty(value = "岗位序列名称")
    private String postSeqName;
    public PostSeqTree(){};
    public PostSeqTree(PostSeq postSeq){
        this.id=postSeq.getId();
        this.postSeqName=postSeq.getPostSeqName();
        this.parentId=postSeq.getParentId()==null?-1l:postSeq.getParentId();
    }
}
