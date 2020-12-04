package net.herdao.hdp.manpower.sys.service;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectDTO;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectIntDTO;

public interface SelectService {
	/**
	 * 获取集团下拉数据
	 * @return 集团数据
	 */
	List<SelectIntDTO> getGroup();
	

	/**
	 * 获取板块下拉数据
	 * @return 板块数据
	 */
	List<SelectIntDTO> getSection(String groupid);
	

	/**
	 * 获取管线下拉数据
	 * @return 管线数据
	 */
	List<SelectIntDTO> getPipeline(String groupid);
	

	/**
	 * 获取岗位序列下拉数据
	 * @return 岗位序列数据
	 */
	List<SelectIntDTO> getPostSeq(String groupid);
	

	/**
	 * 获取职级下拉数据
	 * @return 职级数据
	 */
	List<SelectIntDTO> getJobLevel(String groupid);


	/**
	 * 获取职等下拉数据
	 * @return 职等数据
	 */
	List<SelectIntDTO> getJobGrade(String groupid);
	

	/**
	 * 获取省份下拉数据
	 * @return 省份数据
	 */
	List<SelectDTO> getProvince();

	/**
	 * 获取市下拉数据
	 * @return 市数据
	 */
	List<SelectDTO> getCity(String provinceCode);

	/**
	 * 获取市配置下拉数据
	 * @return 市配置数据
	 */
	List<SelectIntDTO> getCitySet();
	
	/**
	 * 获取福利类型
	 * 
	 * @return
	 */
	List<SelectDTO> getWelfareStandard();
}
