package net.herdao.hdp.mpclient.controller;

import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.sys.service.OperationLogService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName BaseController
 * @Description BaseController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/25 11:30
 * @Version 1.0
 */
@AllArgsConstructor
public class BaseController<T> {
  final   OperationLogService operationLogService;

  @GetMapping("operationLog")
  public R getOperationLogs(Long objId){
      return R.ok();
  }
}
