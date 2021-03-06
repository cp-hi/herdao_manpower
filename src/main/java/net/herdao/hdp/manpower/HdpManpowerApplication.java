package net.herdao.hdp.manpower;

import net.herdao.hdp.common.feign.annotation.EnableHdpFeignClients;
import net.herdao.hdp.common.security.annotation.EnableHdpResourceServer;
import net.herdao.hdp.common.swagger.annotation.EnableHdpSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableHdpSwagger2
@SpringCloudApplication
@EnableHdpFeignClients
@EnableHdpResourceServer
@EnableCaching
public class HdpManpowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdpManpowerApplication.class, args);
    }

}
