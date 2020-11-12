package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.OKJobLevleSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.OKJobLevleSys;

/**
 * @ClassName OKJobLevleSysService
 * @Description OKJobLevleSysService
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 9:25
 * @Version 1.0
 */

public interface OKJobLevleSysService extends IService<OKJobLevleSys> {
    OKJobLevleSysDTO findDetail(Long id);

    void okCreateJobLevel(Long okJobLevleSysId,Long groupId) throws IllegalAccessException;

}
