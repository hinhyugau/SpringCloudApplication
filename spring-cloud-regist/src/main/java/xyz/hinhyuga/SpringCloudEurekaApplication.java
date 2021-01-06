package xyz.hinhyuga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: SpringCloudEureka Server
 * @Author bryan
 * @Date 2021/1/6 10:41 上午
 * @Version 1.0
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaApplication.class,args);
    }
}
