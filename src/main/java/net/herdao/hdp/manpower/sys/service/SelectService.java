package net.herdao.hdp.manpower.sys.service;
import java.util.List;

import net.herdao.hdp.manpower.mpclient.dto.comm.SelectDTO;

public interface SelectService {
	/**
	 * 获取集团下拉数据
	 * @return 集团数据
	 */
	List<SelectDTO> getGroup();
	

	/**
	 * 获取板块下拉数据
	 * @return 板块数据
	 */
	List<SelectDTO> getSection(String groupid);
	

	/**
	 * 获取管线下拉数据
	 * @return 管线数据
	 */
	List<SelectDTO> getPipeline(String groupid);
	

	/**
	 * 获取岗位序列下拉数据
	 * @return 岗位序列数据
	 */
	List<SelectDTO> getPostSeq(String groupid);
	

	/**
	 * 获取职级下拉数据
	 * @return 职级数据
	 */
	List<SelectDTO> getJobLevel(String groupid);


	/**
	 * 获取职等下拉数据
	 * @return 职等数据
	 */
	List<SelectDTO> getJobGrade(String groupid);
}
