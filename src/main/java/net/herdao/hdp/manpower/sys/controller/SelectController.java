package net.herdao.hdp.manpower.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.sys.service.SelectService;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectDTO;

/**
 * <p>
 * 下拉数据接口
 * </p>
 *
 * @author lift
 * @since 2020-10-21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys/select")
@Api(value = "select", tags = "下拉数据接口")
public class SelectController {
	
	private final SelectService selectService;
	
	/**
	 * 获取集团下拉数据
	 * @return 集团数据
	 */
	@GetMapping("/getGroup")
	public R<List<SelectDTO>> getGroup() {
		List<SelectDTO> selectDTOList = selectService.getGroup();		
		return R.ok(selectDTOList);
	}
	

	/**
	 * 获取板块下拉数据
	 * @return 板块数据
	 */
	@GetMapping("/getSection")
	public R<List<SelectDTO>> getSection(String groupid) {
		List<SelectDTO> selectDTOList = selectService.getSection(groupid);		
		return R.ok(selectDTOList);
	}
	

	/**
	 * 获取管线下拉数据
	 * @return 管线数据
	 */
	@GetMapping("/getPipeline")
	public R<List<SelectDTO>> getPipeline(String groupid) {
		List<SelectDTO> selectDTOList = selectService.getPipeline(groupid);		
		return R.ok(selectDTOList);
	}
	
	/**
	 * 获取岗位序列下拉数据
	 * @return 岗位序列数据
	 */
	@GetMapping("/getPostSeq")
	public R<List<SelectDTO>> getPostSeq(String groupid) {
		List<SelectDTO> selectDTOList = selectService.getPostSeq(groupid);		
		return R.ok(selectDTOList);
	}
	
	/**
	 * 获取职级下拉数据
	 * @return 职级数据
	 */
	@GetMapping("/getJobLevel")
	public R<List<SelectDTO>> getJobLevel(String groupid) {
		List<SelectDTO> selectDTOList = selectService.getJobLevel(groupid);		
		return R.ok(selectDTOList);
	}

	/**
	 * 获取职等下拉数据
	 * @return 职等数据
	 */
	@GetMapping("/getJobGrade")
	public R<List<SelectDTO>> getJobGrade(String groupid) {
		List<SelectDTO> selectDTOList = selectService.getJobGrade(groupid);		
		return R.ok(selectDTOList);
	}

	/**
	 * 获取省份下拉数据
	 * @return 省份数据
	 */
	@GetMapping("/getProvince")
	public R<List<SelectDTO>> getProvince() {
		List<SelectDTO> selectDTOList = selectService.getProvince();		
		return R.ok(selectDTOList);
	}

	/**
	 * 获取市下拉数据
	 * @return 市数据
	 */
	@GetMapping("/getCity")
	public R<List<SelectDTO>> getCity(String provinceCode) {
		List<SelectDTO> selectDTOList = selectService.getCity(provinceCode);		
		return R.ok(selectDTOList);
	}

	/**
	 * 获取市配置下拉数据
	 * @return 市配置数据
	 */
	@GetMapping("/getCitySet")
	public R<List<SelectDTO>> getCitySet() {
		List<SelectDTO> selectDTOList = selectService.getCitySet();		
		return R.ok(selectDTOList);
	}
	
	/**
	 * 获取福利类型
	 * 
	 * @return
	 */
	@GetMapping("/getWelfareStandard")
	public R<List<SelectDTO>> getWelfareStandard() {
		List<SelectDTO> selectList = selectService.getWelfareStandard();		
		return R.ok(selectList);
	}
}
