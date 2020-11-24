package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:40 下午
 */

@RestController
@RequestMapping("/staff/level-change" )
@Api(value = "staffLevelChange", tags = "员工异动管理-职级升降")
public class StaffChangeLevelController {
}
