package net.herdao.hdp.manpower.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.mapper.OperationLogMapper;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OperationLogServiceImpl
 * @Description OperationLogServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:28
 * @Version 1.0
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
    @Override
    public List<OperationLog> findByEntity(Long objId, String entityClass) {
        return baseMapper.findByEntity(objId, entityClass);
    }

    @Override
    public IPage page(IPage page, Long objId, String entityClass) {
        QueryWrapper<OperationLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("OBJ_ID", objId)
                .eq("ENTITY_CLASS", entityClass)
                .orderBy(true, false, "operated_time");
        return this.page(page, queryWrapper);
    }

    @Override
    public Page<OperationLog> findOperationLog(Page page, OperationLog log, String searchText) {
        QueryWrapper<OperationLog> wrapper = Wrappers.query(log);
        if (StringUtils.isNotBlank(log.getModule())) {
            wrapper.eq("module", log.getModule());
        }
        if (StringUtils.isNotBlank(log.getExtraKey())) {
            wrapper.eq("extra_key", log.getExtraKey());
        }
        if (StringUtils.isNotBlank(searchText)) {
            wrapper.like("CONCAT(operated_time,operation,operator,content)", searchText);
        }
        Page<OperationLog> pageResult = super.page(page, wrapper);
        return pageResult;
    }

    @Override
    public List<OperationLog> findOperationLog(OperationLog log, String searchText) {
        QueryWrapper<OperationLog> wrapper = Wrappers.query(log);
        if (StringUtils.isNotBlank(log.getModule())) {
            wrapper.eq("module", log.getModule());
        }
        if (StringUtils.isNotBlank(log.getExtraKey())) {
            wrapper.eq("extra_key", log.getExtraKey());
        }
        if (StringUtils.isNotBlank(searchText)) {
            wrapper.like("CONCAT(operated_time,operation,operator,content)", searchText);
        }
        List<OperationLog> list = super.list(wrapper);
        return list;
    }

}
