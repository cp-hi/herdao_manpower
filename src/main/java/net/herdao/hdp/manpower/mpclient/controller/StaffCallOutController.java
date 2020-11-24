package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:37 下午
 */

@RestController
@RequestMapping("/staff/call-out" )
@Api(value = "staffCallOut", tags = "员工异动管理-调出管理")
public class StaffCallOutController {
}
