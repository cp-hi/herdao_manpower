package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.post.OKPostSeqSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.OKPostSeqSys;

/**
 * @ClassName OKPostSeqSysService
 * @Description OKPostSeqSysService
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 16:29
 * @Version 1.0
 */
public interface OKPostSeqSysService extends IService<OKPostSeqSys> {
    /**
     *
     * @param okPostSeqSysId
     * @return
     */
    OKPostSeqSysDTO findDetail(Long okPostSeqSysId);

    /**
     * 一键创建岗位序列
     * @param okPostSeqSysId
     */
    void okCreatePostSeq(Long okPostSeqSysId);
}
