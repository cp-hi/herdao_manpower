package net.herdao.hdp.manpower.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import net.herdao.hdp.manpower.sys.service.SelectService;

@Service
@AllArgsConstructor
public class SelectServiceImpl implements SelectService{

	private final GroupService groupService;
	
	private final SectionService sectionService;
	
	private final PipelineService pipelineService;
	
	private final PostSeqService postSeqService;
	
	private final JobLevelService jobLevelService;
	
	private final JobGradeService jobGradeService;
	
	@Override
	public List<SelectDTO> getGroup() {
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
		return selectDTOList;
	}

	@Override
	public List<SelectDTO> getSection(String groupid) {
		//板块应该按集团过滤，当前如果没传全量返回
		QueryWrapper<Section> queryWrapper = new QueryWrapper<Section>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.eq("IS_STOP", 0);
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
		return selectDTOList;
	}

	@Override
	public List<SelectDTO> getPipeline(String groupid) {
		//板块应该按集团过滤，当前如果没传全量返回
		QueryWrapper<Pipeline> queryWrapper = new QueryWrapper<Pipeline>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.eq("IS_STOP", 0);
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
		return selectDTOList;
	}

	@Override
	public List<SelectDTO> getPostSeq(String groupid) {
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
		return selectDTOList;
	}

	@Override
	public List<SelectDTO> getJobLevel(String groupid) {
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
		return selectDTOList;
	}
	
	@Override
	public List<SelectDTO> getJobGrade(String groupid){
		//职级应该按集团过滤，当前如果没传全量返回
		QueryWrapper<JobGrade> queryWrapper = new QueryWrapper<JobGrade>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("SORT_NO");
		
		List<JobGrade> jobGradeList = jobGradeService.list(queryWrapper);
		List<SelectDTO> selectDTOList = new ArrayList<>();
		SelectDTO selectDTO;
		for(int i=0;i<jobGradeList.size();i++){
			selectDTO = new SelectDTO();
			selectDTO.setValue(jobGradeList.get(i).getId().toString());
			selectDTO.setLabel(jobGradeList.get(i).getJobGradeName());
			selectDTOList.add(selectDTO);
		}		
		return selectDTOList;
	}

}
