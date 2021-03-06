package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.post.*;
import net.herdao.hdp.manpower.mpclient.entity.OKPostSeqSys;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.service.OKPostSeqSysService;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqBatchVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqFormVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostSeqListVO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.vo.post.ShortPostSeqVO;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import net.herdao.hdp.manpower.sys.utils.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PostSeqController
 * @Description 岗位序列
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/client/postSeq")
@Api(tags = "岗位序列管理")
public class PostSeqController extends BaseController<PostSeq, PostSeqListVO, PostSeqFormVO>
        implements ExcelImportController<PostSeqBatchVO, PostSeqBatchVO> {

    @Autowired
    private PostSeqService postSeqService;

    @Autowired
    private OKPostSeqSysService okPostSeqSysService;

    @Override
    public EntityService getEntityService() {
        return postSeqService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(postSeqService.postSeqList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postSeqName", value = "字符串搜索"),
            @ApiImplicitParam(name = "parentId", value = "父级ID，这是查找下级用的，所以查顶级不用传，查下级传自身ID"),
            @ApiImplicitParam(name = "stop", value = "是否停用，0启用：1停用，不填查所有"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载，下载时把上一个返回的total当成size传递"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<PostSeqListVO>> page(HttpServletResponse response, @ApiIgnore Page page, PostSeq seq, Integer type)
            throws Exception {
        return super.page(response, page, seq, type);
    }

    @GetMapping("/getTree")
    @ApiOperation(value = "岗位序列树查询", notes = "岗位序列树查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "groupId",value = "集团id",required = true),
            @ApiImplicitParam(name = "parentId",value = "上级id"),
            @ApiImplicitParam(name = "lazy",value = "是否懒加载")})
    public R<List<PostSeqTree>> getTree(Long groupId, Long parentId, Boolean lazy){
        return R.ok(postSeqService.getTree(groupId,parentId,lazy));
    }



    @GetMapping("/okpage")
    @ApiOperation(value = "一键岗位序列体系列表", notes = "一键岗位序列体系列表")
    public R<List<OKPostSeqSysDTO>> okpage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<OKPostSeqSys> jobLevleSys = okPostSeqSysService.list();
        List<OKPostSeqSysDTO> data = DtoConverter.dto2vo(jobLevleSys, OKPostSeqSysDTO.class);
        return R.ok(data);
    }

    @GetMapping("/okPostSeqDetail")
    @ApiOperation(value = "获取岗位序列体系详情", notes = "获取岗位序列体系详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位序列体系ID"),
    })
    public R<OKPostSeqSysDetailDTO> okPostSeqDetail(Long id) {
        OKPostSeqSysDTO okPostSeqSysDTO = okPostSeqSysService.findDetail(id);
        OKPostSeqSysDetailDTO detailDTO = new OKPostSeqSysDetailDTO();
        BeanUtils.copyProperties(okPostSeqSysDTO, detailDTO);

        for (OKPostSeqDTO okPostSeqDTO : okPostSeqSysDTO.getOkPostSeqDTOList()) {
            String postSeqName = okPostSeqDTO.getPostSeqName();
            List<String> postName = new ArrayList<>();
            for (OKPostDTO okPostDTO : okPostSeqDTO.getOkPostDTOList())
                postName.add(okPostDTO.getPostName());

            detailDTO.getShortPostSeqDTOList().add(ShortPostSeqVO.builder()
                    .postSeqName(postSeqName).postName(StringUtils.join(postName, "、")).build());
        }
        return R.ok(detailDTO);
    }


    @GetMapping("/okCreatePostSeq")
    @ApiOperation(value = "一键创建职级系统详情", notes = "一键创建职级系统详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "职级体系ID",required = true),
            @ApiImplicitParam(name = "groupId", value = "集团ID",required = true),
    })
    public R okCreatePostSeq(Long id,Long groupId) {
        try {
            okPostSeqSysService.okCreatePostSeq(id,  groupId);
        } catch (Exception ex) {
            return R.failed(ExceptionUtils.getRealMessage(ex));
        }
        return R.ok();
    }
}
