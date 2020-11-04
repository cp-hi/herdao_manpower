package net.herdao.hdp.manpower.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.admin.api.entity.SysDict;
import net.herdao.hdp.manpower.mpclient.dto.comm.SelectDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author hdp
 * @since 2017-11-19
 */
@Mapper
public interface SelectMapper extends BaseMapper<SelectDTO> {

	/**
	 * 获取中国城市下拉数据
	 * @return 城市数据
	 */
	List<SelectDTO> getChinaCity();
	
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
	List<SelectDTO> getCitySet();
}
