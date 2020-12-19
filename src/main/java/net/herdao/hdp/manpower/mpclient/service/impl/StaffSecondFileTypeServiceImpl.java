
package net.herdao.hdp.manpower.mpclient.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.dto.attachFile.StaffSecondFileTypeDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffFileTypeDTO;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import net.herdao.hdp.manpower.mpclient.mapper.StaffSecondFileTypeMapper;
import net.herdao.hdp.manpower.mpclient.service.AttachFileService;
import net.herdao.hdp.manpower.mpclient.service.StaffSecondFileTypeService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;

/**
 * 员工附件二级分类
 *
 * @author andy
 * @date 2020-09-29 11:17:03
 */
@Service
public class StaffSecondFileTypeServiceImpl extends ServiceImpl<StaffSecondFileTypeMapper, StaffSecondFileType> implements StaffSecondFileTypeService {

	@Autowired
	private SysDictItemService sysDictItemService;
	@Autowired
	private AttachFileService attachFileService;
	
    @Override
    public Page<StaffFileTypeDTO> findStaffFileTypePage(Page<StaffFileTypeDTO> page, StaffFileTypeDTO entity) {
        Page<StaffFileTypeDTO> pageResult = this.baseMapper.findStaffFileTypePage(page, entity);
        return pageResult;
    }

    @Override
    @OperationEntity(operation = "保存或修改", clazz = StaffSecondFileType.class)
    public boolean saveOrUpdate(StaffSecondFileType entity) {
        boolean status= super.saveOrUpdate(entity);
        return status;
    }

    /**
     * 	修改或新增 二级 字典
     */
    @Override
    public boolean saveOrModify(StaffSecondFileTypeDTO dto) {
    	
    	boolean add = false;
    	Integer sort = 1;
    	String value = "1";
    	if( null == dto.getId()) {
    		add = true;
    		QueryWrapper<SysDictItem> query = new QueryWrapper<>();
        	query.eq("type", dto.getType());
        	query.orderByDesc("value");
        	List<SysDictItem> list = sysDictItemService.list(query);
        	if(!CollectionUtils.isEmpty(list)) {
        		sort = list.get(0).getSort()+1;
        		value = String.valueOf( Integer.valueOf( list.get(0).getValue() ) + 1 );
        	}
    	}
    	SysDictItem item = new SysDictItem();
    	item.setId(dto.getId());
    	item.setDictId(dto.getDictId());
    	item.setLabel(dto.getLabel());
    	item.setType(dto.getType());
    	if(add) {
    		item.setSort(sort);
        	item.setValue(value);
    	}
    	
    	item.setDescription(dto.getLabel());
    	Boolean success = sysDictItemService.saveOrUpdate(item);
        
        return success;
    }
    
    @Override
    public boolean deleteStaffSecondFileType(Long id) {
    	return sysDictItemService.removeById(id);
    }
    
    @Override
    @OperationEntity(operation = "删除", clazz = StaffSecondFileType.class)
    public boolean delEntity(StaffSecondFileType entity) {
        boolean status= super.removeById(entity);
        return status;
    }

    @Override
    public List<StaffFileTypeDTO> findStaffFileType(String type,Long bizId) {
    	
    	
    	List<SysDictItem> list = sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, type));
    	//查询所有文件
    	List<AttachFile> fileList = attachFileService.getAttachFileByBizId(bizId);
    	
    	List<StaffFileTypeDTO> dtoList = new ArrayList<>();
    	for (SysDictItem sysDictItem : list) {
    		String dType = sysDictItem.getType();
    		String dValue = sysDictItem.getValue();
    		StaffFileTypeDTO dto = new StaffFileTypeDTO();
    		dto.setId(sysDictItem.getId());
    		dto.setDictId(sysDictItem.getDictId());
    		dto.setLabel(sysDictItem.getLabel());
    		dto.setType(dType);
    		dto.setValue(dValue);
    		List<String> fileIdList = new ArrayList<>();
    		for (AttachFile attachFile : fileList) {
    			String aType = attachFile.getModuleType();
    			String aValue = attachFile.getModuleValue();
    			if(aType.equals(dType) && aValue.equals(dValue)) {
    				fileIdList.add(attachFile.getFileId());
    			}
    		}
    		String fileIds = StringUtils.join(fileIdList.toArray(), ",");
    		dto.setFileCount(fileIdList.size());
    		dto.setFileIds(fileIds);
    		fileIdList = null;
    		dtoList.add(dto);
		}
    	return dtoList;
    }




}
 