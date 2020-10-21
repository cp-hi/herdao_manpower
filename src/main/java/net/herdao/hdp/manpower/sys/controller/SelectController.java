package net.herdao.hdp.manpower.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;

/**
 * <p>
 * 下拉数据接口
 * </p>
 *
 * @author hdp
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys/select")
@Api(value = "select", tags = "下拉数据接口")
public class SelectController {
	
	private final GroupService groupService;
	
	private final SectionService sectionService;
	
	private final PipelineService pipelineService;
	
	private final PostSeqService postSeqService;
	
	private final JobLevelService jobLevelService;
	
	/**
	 * 获取集团下拉数据
	 * @return 集团数据
	 */
	@GetMapping("/getGroup")
	public R<List<SelectDTO>> getGroup() {
		List<Group> groupList = groupService.list(new QueryWrapper<Group>()
			.orderByAsc("SORT_NO")
		); //集团应该按数据权限过滤，目前暂时不过滤
		List<SelectDTO> selectDTOList = new ArrayList<>();
		SelectDTO selectDTO;
		for(int i=0;i<groupList.size();i++){
			selectDTO = new SelectDTO();
			selectDTO.setValue(groupList.get(i).getId().toString());
			selectDTO.setLabel(groupList.get(i).getGroupName());
			selectDTOList.add(selectDTO);
		}		
		
		return R.ok(selectDTOList);
	}
	

	/**
	 * 获取板块下拉数据
	 * @return 板块数据
	 */
	@GetMapping("/getSection")
	public R<List<SelectDTO>> getSection(String groupid) {
		 //板块应该按集团过滤，当前如果没传全量返回
		QueryWrapper<Section> queryWrapper = new QueryWrapper<Section>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("SORT_NO");
		
		List<Section> sectionList = sectionService.list(queryWrapper);
		List<SelectDTO> selectDTOList = new ArrayList<>();
		SelectDTO selectDTO;
		for(int i=0;i<sectionList.size();i++){
			selectDTO = new SelectDTO();
			selectDTO.setValue(sectionList.get(i).getId().toString());
			selectDTO.setLabel(sectionList.get(i).getSectionName());
			selectDTOList.add(selectDTO);
		}		
		
		return R.ok(selectDTOList);
	}
	

	/**
	 * 获取管线下拉数据
	 * @return 管线数据
	 */
	@GetMapping("/getPipeline")
	public R<List<SelectDTO>> getPipeline(String groupid) {
		 //板块应该按集团过滤，当前如果没传全量返回
		QueryWrapper<Pipeline> queryWrapper = new QueryWrapper<Pipeline>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("SORT_NO");
		
		List<Pipeline> PipelineList = pipelineService.list(queryWrapper);
		List<SelectDTO> selectDTOList = new ArrayList<>();
		SelectDTO selectDTO;
		for(int i=0;i<PipelineList.size();i++){
			selectDTO = new SelectDTO();
			selectDTO.setValue(PipelineList.get(i).getId().toString());
			selectDTO.setLabel(PipelineList.get(i).getPipelineName());
			selectDTOList.add(selectDTO);
		}		
		
		return R.ok(selectDTOList);
	}
	
	/**
	 * 获取岗位序列下拉数据
	 * @return 岗位序列数据
	 */
	@GetMapping("/getPostSeq")
	public R<List<SelectDTO>> getPostSeq(String groupid) {
		 //岗位序列应该按集团过滤，当前如果没传全量返回
		QueryWrapper<PostSeq> queryWrapper = new QueryWrapper<PostSeq>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("POST_SEQ_CODE");
		
		List<PostSeq> postSeqList = postSeqService.list(queryWrapper);
		List<SelectDTO> selectDTOList = new ArrayList<>();
		SelectDTO selectDTO;
		for(int i=0;i<postSeqList.size();i++){
			selectDTO = new SelectDTO();
			selectDTO.setValue(postSeqList.get(i).getId().toString());
			selectDTO.setLabel(postSeqList.get(i).getPostSeqName());
			selectDTOList.add(selectDTO);
		}		
		
		return R.ok(selectDTOList);
	}
	
	/**
	 * 获取职级下拉数据
	 * @return 职级数据
	 */
	@GetMapping("/getJobLevel")
	public R<List<SelectDTO>> getJobLevel(String groupid) {
		 //职级应该按集团过滤，当前如果没传全量返回
		QueryWrapper<JobLevel> queryWrapper = new QueryWrapper<JobLevel>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("SORT_NO");
		
		List<JobLevel> jobLevelList = jobLevelService.list(queryWrapper);
		List<SelectDTO> selectDTOList = new ArrayList<>();
		SelectDTO selectDTO;
		for(int i=0;i<jobLevelList.size();i++){
			selectDTO = new SelectDTO();
			selectDTO.setValue(jobLevelList.get(i).getId().toString());
			selectDTO.setLabel(jobLevelList.get(i).getJobLevelName());
			selectDTOList.add(selectDTO);
		}		
		
		return R.ok(selectDTOList);
	}
}
