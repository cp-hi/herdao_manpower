package net.herdao.hdp.manpower.mpclient.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationAddDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.service.ExcelOperateRecordService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;


/**
 * 组织excel导入监听处理
 * 
 * @author    Andy
 * @modify    shuling
 * @date      2020-09-17 11:03:56
 */
@Slf4j
public class OrgExcelListener extends AnalysisEventListener<OrganizationAddDTO> {
	
	private UserService userService;

    private PostService postService;
    
    private SysDictItemService sysDictItemService;

    private OrganizationService organizationService;

    private ExcelOperateRecordService excelOperateRecordService;

	public OrgExcelListener( OrganizationService organizationService, 
							 SysDictItemService sysDictItemService, 
							 UserService userService, PostService postService,
							 ExcelOperateRecordService excelOperateRecordService) {
		
		this.userService = userService;
		this.postService = postService;
		this.sysDictItemService = sysDictItemService;
		this.organizationService = organizationService;
		this.excelOperateRecordService = excelOperateRecordService;
	}
	
	// 组织类型
	List<SysDictItem> orgTypeList = sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, "ZZLX"));
	
	// 用户信息
	List<User> userList = userService.list();
	
	// 岗位信息
	List<Post> postList = postService.list();
    
	// 导入组织信息集合
    List<OrganizationAddDTO> orgList = new ArrayList<OrganizationAddDTO>();

    @Override
    public void invoke(OrganizationAddDTO data, AnalysisContext context) {
        
        orgList.add(data);
        
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 保存数据
        saveData();
        log.info("所有数据解析完成！");
    }

   
	private void saveData() {

		log.info("{}条数据，开始存储数据库！", orgList.size());

		if (!CollectionUtils.isEmpty(orgList)) {

		}
		log.info("存储数据库成功！");
	}

    /**
     * 获取组织集合 key = orgName + parentOrgCode 作为唯一标识
     * @return
     */
    Map<String, OrganizationAddDTO> getOrganizationMap(){
    	
       Map<String, OrganizationAddDTO> renderMap = new HashMap<String, OrganizationAddDTO>();
       List<OrganizationAddDTO> orgList = this.organizationService.selectAllOrganization();
       if(ObjectUtil.isNotEmpty(orgList)) {
    	   orgList.forEach(org ->{
    		   // 唯一标识，用于数据导入校验
    		   String key = org.getOrgName() + ManpowerContants.SEPARATOR + org.getParentOrgCode();
    		   renderMap.put(key, org);
    	   });
       }
       return renderMap;
    }
    
    /**
     * 组织集合 orgCode 作为唯一标识
     * 
     * @param orgList
     * @return
     */
    Map<String, OrganizationAddDTO> getParentOrganizationMap(List<OrganizationAddDTO> orgList){
    	return orgList.stream().collect(Collectors.toMap(OrganizationAddDTO::getOrgCode, (p) -> p)); 
    }
    
    /**
     * 数据校验
     * 
     * @param orgList 导入组织信息集合
     * @param orgMap 系统已存在的组织信息集合
     * @param importType 导入类型，值： 0  批量新增； 值 1 批量修改
     */
	@SuppressWarnings("unused")
	public LinkedHashMap<Integer, String> validate(List<OrganizationAddDTO> orgList, Map<String, OrganizationAddDTO> orgMap, Integer importType) {
		
		LinkedHashMap<Integer, String> renderMap = new LinkedHashMap<Integer, String>();
		// 用户信息
		Map<String, User> userMap = getUserMap();
		
		if(importType == 0) {
			// 导入校验
			for (int i = 0; i < orgList.size(); i++) {

				OrganizationAddDTO org = orgList.get(i);

				StringBuffer stringBuffer = null;
				
				// 父组织信息
				OrganizationAddDTO orgp = orgMap.get(org.getParentOrgCode());
				
				// 校验父组织信息
				if (orgp == null) {
					appendStringBuffer(stringBuffer, "组织编码：" + org.getParentOrgCode() + "不存在");
				}else {
					org.setParentId(orgp.getParentId());
				}
				// 校验组织类型
				String orgType = getDictItem(orgTypeList, org.getOrgType());
				if (StrUtil.isNotBlank(orgType)) {
					appendStringBuffer(stringBuffer, "组织类型：" + org.getOrgType() + "不存在");
				} else {
					// 转字典value
					org.setOrgType(orgType);
				}
				// 校验用户信息
				User user = userMap.get(org.getOrgChargeWorkNo());
				if(user == null) {
					appendStringBuffer(stringBuffer, "组织负责人工号：" + org.getOrgChargeWorkNo() + "不存在");
				}else {
					org.setOrgChargeId(StrUtil.toString(user.getId()));
					org.setOrgChargeName(user.getUserName());
					org.setOrgChargeWorkNo(user.getLoginCode());
				}
				// 记录异常信息
				if (stringBuffer != null) {
					stringBuffer.insert(0, "第：" + i + "行 ");
					renderMap.put(i, stringBuffer.toString());
				}
			}
		}

		return renderMap;
	}
    
    StringBuffer getStringBuffer(StringBuffer bf) {
    	if(bf == null) {
			bf = new StringBuffer();
		}
    	return bf;
    }
    
    /**
     * 拼接组织异常信息
     * 
     * @param stringBuffer
     * @param errorMsg
     */
	 void appendStringBuffer(StringBuffer stringBuffer, String errorMsg) {

		if (stringBuffer == null) {
			stringBuffer = new StringBuffer();
		}

		stringBuffer.append(stringBuffer.length() > 0 ? errorMsg + ManpowerContants.CH_SEMICOLON : errorMsg);
	}
	 
	/**
	 * 获取字典value值
	 * 
	 * @param sysDictItemList
	 * @param label
	 * @return
	 */
	public String getDictItem(List<SysDictItem> sysDictItemList, String label) {
		if (ObjectUtil.isNotEmpty(sysDictItemList)){
			for(SysDictItem dict : sysDictItemList) {
				if(dict.getLabel().equals(label)) {
					return dict.getValue();
				}
			}
        }
		return null;
	}
	
	/**
	 * 用户信息
	 * 
	 * @return
	 */
	public Map<String, User> getUserMap(){
		Map<String, User> renderMap = new HashMap<String, User>();
		if(ObjectUtil.isNotEmpty(userList)) {
			userList.forEach(us ->{
				renderMap.put(us.getLoginCode(), us);
			});
		}
		return renderMap;
	}
	
	/**
	 * 岗位信息
	 * 
	 * @return
	 */
	public Map<String, Post> getPostMap(){
		Map<String, Post> renderMap = new HashMap<String, Post>();
		if(ObjectUtil.isNotEmpty(postList)) {
			postList.forEach(ps ->{
				renderMap.put(ps.getPostCode(), ps);
			});
		}
		return renderMap;
	}
}
