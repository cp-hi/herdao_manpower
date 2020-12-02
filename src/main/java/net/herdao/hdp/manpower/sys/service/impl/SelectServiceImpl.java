package net.herdao.hdp.manpower.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectDTO;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectIntDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.entity.WelfareStandards;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import net.herdao.hdp.manpower.mpclient.service.PostSeqService;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import net.herdao.hdp.manpower.mpclient.service.WelfareStandardsService;
import net.herdao.hdp.manpower.sys.mapper.SelectMapper;
import net.herdao.hdp.manpower.sys.service.SelectService;

@Service
@AllArgsConstructor
public class SelectServiceImpl extends ServiceImpl<SelectMapper, SelectDTO> implements SelectService{

	private final GroupService groupService;
	
	private final SectionService sectionService;
	
	private final PipelineService pipelineService;
	
	private final PostSeqService postSeqService;
	
	private final JobLevelService jobLevelService;
	
	private final JobGradeService jobGradeService;
	
	private final WelfareStandardsService welfareStandardsService;
		
	@Override
	public List<SelectIntDTO> getGroup() {
		List<Group> groupList = groupService.list(new QueryWrapper<Group>()
				.orderByAsc("SORT_NO")
			); //集团应该按数据权限过滤，目前暂时不过滤
			List<SelectIntDTO> selectDTOList = new ArrayList<>();
			SelectIntDTO selectDTO;
			for(int i=0;i<groupList.size();i++){
				selectDTO = new SelectIntDTO();
				selectDTO.setValue(groupList.get(i).getId());
				selectDTO.setLabel(groupList.get(i).getGroupName());
				selectDTOList.add(selectDTO);
			}		
		return selectDTOList;
	}

	@Override
	public List<SelectIntDTO> getSection(String groupid) {
		//板块应该按集团过滤，当前如果没传全量返回
		QueryWrapper<Section> queryWrapper = new QueryWrapper<Section>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.eq("IS_STOP", 0);
		queryWrapper.orderByAsc("SORT_NO");
		
		List<Section> sectionList = sectionService.list(queryWrapper);
		List<SelectIntDTO> selectDTOList = new ArrayList<>();
		SelectIntDTO selectDTO;
		for(int i=0;i<sectionList.size();i++){
			selectDTO = new SelectIntDTO();
			selectDTO.setValue(sectionList.get(i).getId());
			selectDTO.setLabel(sectionList.get(i).getSectionName());
			selectDTOList.add(selectDTO);
		}		
		return selectDTOList;
	}

	@Override
	public List<SelectIntDTO> getPipeline(String groupid) {
		//板块应该按集团过滤，当前如果没传全量返回
		QueryWrapper<Pipeline> queryWrapper = new QueryWrapper<Pipeline>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.eq("IS_STOP", 0);
		queryWrapper.orderByAsc("SORT_NO");
		
		List<Pipeline> PipelineList = pipelineService.list(queryWrapper);
		List<SelectIntDTO> selectDTOList = new ArrayList<>();
		SelectIntDTO selectDTO;
		for(int i=0;i<PipelineList.size();i++){
			selectDTO = new SelectIntDTO();
			selectDTO.setValue(PipelineList.get(i).getId());
			selectDTO.setLabel(PipelineList.get(i).getPipelineName());
			selectDTOList.add(selectDTO);
		}		
		return selectDTOList;
	}

	@Override
	public List<SelectIntDTO> getPostSeq(String groupid) {
		//岗位序列应该按集团过滤，当前如果没传全量返回
		QueryWrapper<PostSeq> queryWrapper = new QueryWrapper<PostSeq>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("POST_SEQ_CODE");
		
		List<PostSeq> postSeqList = postSeqService.list(queryWrapper);
		List<SelectIntDTO> selectDTOList = new ArrayList<>();
		SelectIntDTO selectDTO;
		for(int i=0;i<postSeqList.size();i++){
			selectDTO = new SelectIntDTO();
			selectDTO.setValue(postSeqList.get(i).getId());
			selectDTO.setLabel(postSeqList.get(i).getPostSeqName());
			selectDTOList.add(selectDTO);
		}		
		return selectDTOList;
	}

	@Override
	public List<SelectIntDTO> getJobLevel(String groupid) {
		//职级应该按集团过滤，当前如果没传全量返回
		QueryWrapper<JobLevel> queryWrapper = new QueryWrapper<JobLevel>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("SORT_NO");
		
		List<JobLevel> jobLevelList = jobLevelService.list(queryWrapper);
		List<SelectIntDTO> selectDTOList = new ArrayList<>();
		SelectIntDTO selectDTO;
		for(int i=0;i<jobLevelList.size();i++){
			selectDTO = new SelectIntDTO();
			selectDTO.setValue(jobLevelList.get(i).getId());
			selectDTO.setLabel(jobLevelList.get(i).getJobLevelName());
			selectDTOList.add(selectDTO);
		}		
		return selectDTOList;
	}
	
	@Override
	public List<SelectIntDTO> getJobGrade(String groupid){
		//职级应该按集团过滤，当前如果没传全量返回
		QueryWrapper<JobGrade> queryWrapper = new QueryWrapper<JobGrade>();
		if(groupid!=null && !"".equals(groupid)){
			queryWrapper.eq("group_id",groupid);
		}
		queryWrapper.orderByAsc("SORT_NO");
		
		List<JobGrade> jobGradeList = jobGradeService.list(queryWrapper);
		List<SelectIntDTO> selectDTOList = new ArrayList<>();
		SelectIntDTO selectDTO;
		for(int i=0;i<jobGradeList.size();i++){
			selectDTO = new SelectIntDTO();
			selectDTO.setValue(jobGradeList.get(i).getId());
			selectDTO.setLabel(jobGradeList.get(i).getJobGradeName());
			selectDTOList.add(selectDTO);
		}		
		return selectDTOList;
	}

	@Override
	public List<SelectDTO> getProvince(){
		List<SelectDTO> list = new ArrayList();
		List<SelectDTO> cityList = this.baseMapper.getChinaCity(); //所有城市数据
		
		//取出省数据
		for(int i=0;i<cityList.size();i++){
			String val=cityList.get(i).getValue();
			if(val.length()>4 && val.endsWith("0000")){
				
				//取出下级市
				List<SelectDTO> childrenList = new ArrayList();
				for(int j=0;j<cityList.size();j++){
					String cityVal = cityList.get(j).getValue();
					if(!val.equals(cityVal)){//排查当前省
						if(val.substring(0,2).equals(cityVal.substring(0,2))){//插入前两个编码一样的市数据
							childrenList.add(cityList.get(j));							
						}
					}
				}
				
				cityList.get(i).setChildren(childrenList);
				list.add(cityList.get(i));
			}
		}
		
		return list;
	}

	@Override
	public List<SelectDTO> getCity(String provinceCode){
		List<SelectDTO> list = this.baseMapper.getCity(provinceCode);
		return list;
	}

	@Override
	public List<SelectIntDTO> getCitySet(){
		List<SelectIntDTO> list = this.baseMapper.getCitySet();
		return list;
	}

	@Override
	public List<SelectDTO> getWelfareStandard() {
		
		List<SelectDTO> selectList = new ArrayList<SelectDTO>();
		// 福利类型集合
		List<WelfareStandards> welfareStandardList = this.welfareStandardsService.lambdaQuery()
														 .eq(WelfareStandards::getEnabled, true).list();
		if(ObjectUtil.isNotEmpty(welfareStandardList)) {
			welfareStandardList.forEach(welfareStandard ->{
				selectList.add(SelectDTO.builder()
						       .label(welfareStandard.getVersion())
							   .value(welfareStandard.getWelfarestandardsOid()).build());
			});
		}
		return selectList;
	}
}
 