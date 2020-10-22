package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.entity.OKJobLevel;
import net.herdao.hdp.manpower.mpclient.entity.OKJobLevleSys;

import java.util.List;

/**
 * @ClassName OKJobLevleSysService
 * @Description OKJobLevleSysService
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 9:25
 * @Version 1.0
 */

public interface OKJobLevleSysService extends  EntityService<OKJobLevleSys>  {
    List<OKJobLevleSys> findAll();

}
