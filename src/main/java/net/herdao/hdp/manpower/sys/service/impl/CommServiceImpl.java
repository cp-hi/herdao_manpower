package net.herdao.hdp.manpower.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.dto.comm.UserMsgDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffCallOutService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.sys.service.CommService;

@Service
@AllArgsConstructor
public class CommServiceImpl implements CommService{

	private UserService userService; //员工信息
	
	
}
